#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
extern"C"{
#include <ppm.h>
}
#include <sys/time.h>

// Super macro de mesure du temps
// A NE PAS MODIFIER
#define TIME(fun)							\
  do { struct timeval t1, t2;						\
    gettimeofday(&t1, 0);						\
    fun;								\
    gettimeofday(&t2, 0);						\
    double time = (1000000.0*(t2.tv_sec-t1.tv_sec) + t2.tv_usec-t1.tv_usec)/1000000.0; \
    printf("%s, elapsed time : \033[31;01m%g\033[00m\n", #fun , time);			\
  } while (0)

// A NE PAS MODIFIER
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

// A NE PAS MODIFIER
void writeppm(const char* fname, pixel* out, int cols, int rows, pixval maxval, int format){
  FILE* img_out;
  img_out = fopen(fname, "w+");
  int i;
  
  ppm_writeppminit(img_out, cols, rows, maxval, format);


  for (i =0; i < rows; i++){
    ppm_writeppmrow(img_out, out+(i*(cols)), cols, maxval, 1);
  }
}



/********** A partir d'ici c'est à vous de jouer **********/

/* On commence doucement, sans textures!  Vous pouvez vous appuyer sur
   l'exercice du TD précédent (correction sur Celene) */

// Noyau qui floute une image en utilisant un masque de "rayon" paramétrable
__global__ void blur(pixel* img_in, pixel* img_out, int cols, int rows, int ray){
  // récupération des indices globaux dans la grille 2D pour les
  // dimensions X et Y
    int y = blockIdx.y*blockDim.y + threadIdx.y;
    int x = blockIdx.x*blockDim.x + threadIdx.x;

  // on s'assure de ne pas sortir des limites de l'image
  if (x < cols && y < rows){
  int idx, sommeR, sommeG, sommeB;
  int cpt = 0;
  sommeR=0;
  sommeG=0;
  sommeB=0;
  pixel pix;
    for(int xInd = max(0,x-ray); xInd < min(rows, x+ray); xInd++){
    	for(int yInd = max(0, y-ray); yInd < min(cols, y + ray); yInd++){
    	    cpt++;
	    idx = xInd*rows+yInd;
	    pix = img_in[idx];
	    sommeR += pix.r;
	    sommeG += pix.g;
	    sommeB += pix.b;
	}
    }
    idx = x*rows+y;
    img_out[idx].r = sommeR/cpt;
    img_out[idx].g = sommeG/cpt;
    img_out[idx].b = sommeB/cpt;
    // En tant normal, lorsque on s'appuie sur une bibliothèques avec
    // des types abstraits (ici le type pixel), on utilise les
    // fonctions ou macros de la bibliothèques pour manipuler ce type,
    // par exemple PPM_ASSIGN(img_out[y*cols+x], gray, gray, gray);
  }
}

// Fonction CPU qui utilise  le noyau blur
void blur (pixel* ppm_in, pixel* ppm_out, size_t size, int cols, int rows, pixval maxval, int ray) {
  pixel *d_ppm_in, *d_ppm_out = NULL;

  dim3 DimBlock(16, 16,1);
  dim3 DimGrid((rows + DimBlock.x -1)/DimBlock.x, (cols + DimBlock.y -1)/DimBlock.y, 1);
  
  cudaMalloc(&d_ppm_in, size);
  cudaMalloc(&d_ppm_out, size);
  cudaMemcpy(d_ppm_in, ppm_in, size, cudaMemcpyHostToDevice);
  blur<<<DimGrid, DimBlock>>>(d_ppm_in, d_ppm_out, cols, rows, ray);
  cudaMemcpy(ppm_out, d_ppm_out, size, cudaMemcpyDeviceToHost);
  cudaFree(d_ppm_in);
  cudaFree(d_ppm_out);
}


/* Quand la version "classique" fonctionne, vous pouvez passer à
   l'utilisation de textures */


// Texture globale (pas besoin de la passer en paramère d'un kernel)
// 2D contenant des unsigned int
// A NE PAS MODIFIER 
texture<unsigned int, 2, cudaReadModeElementType> tex;


// Même chose qu'avant mais avec une texture.
//
// Vous utiliserez tex2D(texture, x, y)
// pour lire une valeur dans la texture
__global__ void blur_tex(pixel* img_out, int cols, int rows, int ray){
	
  
}



/* Fonction qui lance le noyau blur_tex Pour rappel, pour utiliser une
   texture, il faut :

   + décrire un cudaChennelFormatDesc (ici, on associe notre texture à
   de simples unsigned int, on pourra donc utiliser le code suivant :
   cudaChannelFormatDesc channelDesc = cudaCreateChannelDesc(32,0,0,0,cudaChannelFormatKindUnsigned);   

   + il faut spécifier les propriétés de notre texture, en particulier:
     ++ son mode de gestion des frontières :
         tex.adressMode[0] pour la dimension 1
         tex.adressMode[1] pour la dimension 2
	ici, on utilisera le mode Wrap (voir API CUDA)
     ++ son mode de filtrage, filterMode, ici on restera sur un mode "non" filtré, 
        en utilisant cudaFilterModePoint
     ++ si la texture est normalisée ou non (ici, non)

   + on doit associer la texture à un cudaArray, pour cela il faut : 
     ++ allouer un cudaArray (voir API CUDA)
     ++ copier les données dans le cudaArray sur le GPU (voir API CUDA)
     ++ lier la texture au cudaArray avec cudaBindTextureToArray (voir API CUDA)

 N'oubliez pas de lancer le kernel, allouer la mémoire pour le résultat etc

*/
void blur_tex (pixel* ppm_in, pixel* ppm_out, size_t size, int cols, int rows, pixval maxval, int ray) {
}
 


/***********   FIN DU TRAVAIL POUR VOUS **********/

// A NE PAS MODIFIER
// le programme utilisera lena.ppm par défaut mais
// vous pouvez lui passer une image ppm (sans l'extension) en
// paramètre pour l'essayer sur d'autres images.
// par exemple :
// ./blur mandril 
int main(int argc, char* argv[]){

  char* name = (argc <= 1)?((char*)"lena"):argv[1];
  char *in = (char*)malloc(sizeof(char)); 
  in = strcat(strcat(in,name), ".ppm");

  char *out = (char*)malloc(sizeof(char)); 
  out = strcat(strcat(out,name), "_blur.ppm");
  
  char *out_tex = (char*)malloc(sizeof(char)); 
  out_tex =  strcat(strcat(out_tex,name), "_blur_tex.ppm");

  printf("Will work on %s and generate %s and %s\n", in, out, out_tex);
  

  pixel *ppm_in, *ppm_out = NULL;
  int cols, rows;
  pixval maxval;
  int format;
  
  ppm_in = readppm(in, &cols, &rows, &maxval, &format);

  long size = cols*rows*sizeof(pixel);  
  ppm_out = (pixel*)malloc(size);
  
  int ray = 25;
  
  TIME(blur(ppm_in, ppm_out, size, cols, rows, maxval, ray));	 
  writeppm(out, ppm_out, cols, rows, maxval, 1);

  
  TIME(blur_tex(ppm_in, ppm_out, size, cols, rows, maxval, ray));	  
  writeppm(out_tex, ppm_out, cols, rows, maxval, 1);

 
  
  free(ppm_in);
  free(ppm_out);
  return 0;
}
