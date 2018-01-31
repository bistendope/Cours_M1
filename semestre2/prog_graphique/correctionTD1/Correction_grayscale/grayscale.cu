#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
extern"C"{
#include <ppm.h>
}

// kernel CUDA
__global__ void grayscale(pixel* img_in, pixel* img_out, int cols, int rows){
  // récupération des indices globaux dans la grille 2D pour les
  // dimensions X et Y
  int y = blockIdx.y*blockDim.y + threadIdx.y;
  int x = blockIdx.x*blockDim.x + threadIdx.x;

  // on s'assure de ne pas sortir des limites de l'image
  if (x < cols && y < rows){
    int idx = y*cols+x;
    pixel pix = img_in[idx];
    int gray = 
      0.21f * pix.r + 0.71f * pix.g + 0.07f * pix.b;

    img_out[idx].r = gray;
    img_out[idx].g = gray;
    img_out[idx].b = gray;
    // En tant normal, lorsque on s'appuie sur une bibliothèques avec
    // des types abstraits (ici le type pixel), on utilise les
    // fonctions ou macros de la bibliothèques pour manipuler ce type,
    // par exemple PPM_ASSIGN(img_out[y*cols+x], gray, gray, gray);
  }
  
}

// fonction de lecture d'un fichier ppm (voir utilisation dans main)
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

// fonction d'écriture d'un fichier ppm (voir utilisation dans main)
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
  
  pixel *ppm_in, *ppm_out = NULL;
  pixel *d_ppm_in, *d_ppm_out = NULL;

  int cols, rows;
  pixval maxval;
  int format;
  int err;
  
  ppm_in = readppm("lena.ppm", &cols, &rows, &maxval, &format);
  
  long size = cols*rows*sizeof(pixel);  

  ppm_out = (pixel*)malloc(size);
  cudaMalloc(&d_ppm_in, size);
  cudaMalloc(&d_ppm_out, size);
  
  
  err = cudaMemcpy(d_ppm_in, ppm_in, size, cudaMemcpyHostToDevice);
  if (err != 0) printf("Error %d\n", err);
  
  // on limite la taille des blocs pour maximiser le parallélisme,
  // ici, un bloc = 16*16 = 256 threads ce qui perme t déjà d'occuper
  // correctement les SM (sur une carte récente, on pourra utiliser
  // des blocs de 32*32), sur une image de 512*512 pixels, on lancera
  // donc au moins 1024 (512*512/256) blocs
  dim3 DimBlock(16, 16,1);
  dim3 DimGrid((rows + DimBlock.x -1)/DimBlock.x, (cols + DimBlock.y -1)/DimBlock.y, 1);
  
  grayscale<<<DimGrid, DimBlock>>>(d_ppm_in, d_ppm_out, cols, rows);
  
  err = cudaMemcpy(ppm_out, d_ppm_out, size, cudaMemcpyDeviceToHost);
  if (err != 0) {
    printf("Error %d\n", err);
    return err;
  }
  
  writeppm("lena_gray.ppm", ppm_out, cols, rows, maxval, 1);
  
  cudaFree(d_ppm_in);
  cudaFree(d_ppm_out);
  free(ppm_in);
  free(ppm_out);
  return 0;
}
