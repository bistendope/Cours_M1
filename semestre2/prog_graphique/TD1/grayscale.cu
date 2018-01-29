#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
extern"C"{
#include <pgm.h>
#include <ppm.h>
}

__global__ void cuda_grayscale(pixel* d_ppm_in, pixel* d_ppm_out, int size){
  // identifiant global du thread dans la grille 1D
  int tid = blockIdx.x * blockDim.x + threadIdx.x;
  // on s'assure de ne pas sortir des limites du tableau
  if (tid < size){
    //on effectue le remplacement des couleurs par un niveau de gris
    int gris = ((int)PPM_GETR(d_ppm_in[tid]) + (int)PPM_GETG(d_ppm_in[tid]) + (int)PPM_GETB(d_ppm_in[tid]))/3;
    PPM_PUTR(d_ppm_out[tid], gris);
    PPM_PUTG(d_ppm_out[tid], gris);
    PPM_PUTB(d_ppm_out[tid], gris);
  }
}


pixel * readppm (const char* fname, int* cols, int* rows, pixval* maxval, int* format){
  FILE* img_in;
  img_in = fopen(fname, "r");
  
  ppm_readppminit(img_in, cols, rows, maxval, format);
  
  pixel* out = (pixel*)malloc ((*cols)*(*rows)*sizeof(pixel));
  int i;
  for (i =0; i < *rows; i++){
    ppm_readppmrow(img_in, out+(i*(*cols)), *cols, *maxval, *format);
  }
  return out;
}

void writeppm(const char* fname, pixel* out, int cols, int rows, pixval maxval, int format){
  FILE* img_out;
  img_out = fopen(fname, "w+");
  int i;
  
  ppm_writeppminit(img_out, cols, rows, maxval, format);


  for (i =0; i < rows; i++){
    ppm_writeppmrow(img_out, out+(i*(cols)), cols, maxval, 1);
  }
}




int main(){
  pixel *ppm_in, *ppm_out; // en mémoire CPU
  pixel *d_ppm_in, *d_ppm_out; // en mémoire GPU

  /////////////////////////////////////////////
  //Lire image dans ppm_in
  int *cols;
  int *rows;
  pixval* maxval;
  int *format;
  
  ppm_in = readppm("lena.ppm", cols, rows, maxval, format);
  
  ////////////////////////////////////////////
  //Allocation mémoire
  int size = (*cols)*(*rows)*sizeof(pixel);
  ppm_out = (pixel*)malloc (size);
  cudaMalloc((void**)&d_ppm_in, size); 
  cudaMalloc((void**)&d_ppm_out, size); 
  
  ////////////////////////////////////////////
  //Copie de ppm_in dans d_ppm_in (en mémoire GPU)
  cudaMemcpy(d_ppm_in, ppm_in, size, cudaMemcpyHostToDevice);

  ////////////////////////////////////////////
  //Déclaration tailles de grilles et blocs + lancement kernel
  int blockSize = 512;
  int gridSize = ((size + blockSize  - 1) / blockSize);

  cuda_grayscale<<<blockSize, gridSize>>>(d_ppm_in, d_ppm_out, size);
  
  ////////////////////////////////////////////
  //Copie du résultat dans ppm_out
  cudaMemcpy(ppm_out, d_ppm_out, size, cudaMemcpyDeviceToHost);
    
  ////////////////////////////////////////////
  //Ecriture du fichier ppm
  writeppm("lenagris.ppm", ppm_out, *cols, *rows, *maxval, *format);

  ////////////////////////////////////////////
  //Libération mémoire
  /////////////////////////////////////////
  cudaFree(d_ppm_in);
  cudaFree(d_ppm_out);

  free(ppm_in);
  free(ppm_out);
  
  return 0;
}

/*
imagemagick
# Transformation d'image en niveaux de gris 

## 1) On utilisera la bibliothèque netpbm pour lire et écrire des
   fichiers ppm (http://netpbm.sourceforge.net/doc/libppm.html).
   Proposez une version CPU (séquentielle) du programme qui transforme
   une image couleur lue dans un fichier ppm en niveaux de gris.

## 2) Proposez une version Cuda du même programme qu'en 1.
En utilisant une grille 1D puis 2D.

## 3) En utilisant des transferts asynchrones.

*/

