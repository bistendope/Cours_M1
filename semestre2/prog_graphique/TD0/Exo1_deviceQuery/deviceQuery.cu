#include <stdio.h>

//mdp: 

// Print device properties
void printDevProp(cudaDeviceProp devProp)
{
  printf("Major revision number:         %d\n",  devProp.major);
  printf("Minor revision number:         %d\n",  devProp.minor);
  printf("Name:                          %s\n",  devProp.name);
  printf("Total global memory:           %zu\n",  devProp.totalGlobalMem);
  printf("Total shared memory per block: %zu\n",  devProp.sharedMemPerBlock);
  printf("Total registers per block:     %d\n",  devProp.regsPerBlock);
  printf("Warp size:                     %d\n",  devProp.warpSize);
  printf("Maximum memory pitch:          %zu\n",  devProp.memPitch);
  printf("Maximum threads per block:     %d\n",  devProp.maxThreadsPerBlock);
  for (int i = 0; i < 3; ++i)
    printf("Maximum dimension %d of block:  %d\n", i, devProp.maxThreadsDim[i]);
  for (int i = 0; i < 3; ++i)
    printf("Maximum dimension %d of grid:   %d\n", i, devProp.maxGridSize[i]);
  printf("Clock rate:                    %d\n",  devProp.clockRate);
  printf("Total constant memory:         %zu\n",  devProp.totalConstMem);
  printf("Texture alignment:             %zu\n",  devProp.textureAlignment);
  printf("Concurrent copy and execution: %s\n",  (devProp.deviceOverlap ? "Yes" : "No"));
  printf("Number of multiprocessors:     %d\n",  devProp.multiProcessorCount);
  printf("Kernel execution timeout:      %s\n",  (devProp.kernelExecTimeoutEnabled ? "Yes" : "No"));
  return;
}

int main()
{
  // Number of CUDA devices
  int devCount;
  cudaGetDeviceCount(&devCount);
  printf("CUDA Device Query...\n");
  printf("There are %d CUDA devices.\n", devCount);

  // Iterate through devices
  for (int i = 0; i < devCount; ++i)
    {
      // Get device properties
      printf("\nCUDA Device #%d\n", i);
      cudaDeviceProp devProp;
      cudaGetDeviceProperties(&devProp, i);
      printDevProp(devProp);
    }

  printf("\nPress any key to exit...");
  char c;
  scanf("%c", &c);

  return 0;
}

/*                                                                                                                 
 Quelques questions à se poser :                                                                                   
                                                                                                                   
+ Quelle est la compute capability de la carte graphique ?      
                                                   
+ Quelles sont les  dimensions maximales d'un bloc (en X, en Y, en Z)?

+ Supposons que nous lançons une grille de blocs unidimensionelle (seulement sur X). 
Si la dimension maximale de la grille est de 65525 sur notre matériel et celle d'un 
bloc de 1024, quel est le nombre maxmal de threads que l'on peut lancer sur notre GPU?
	-1024*65525
	
+ Sous quelles conditions un programmeur pourrait choisir de ne pas utiliser ce nombre
maximum de threads?      
	- On n'en a pas forcément besoin d'autant
	- Si ça ne tombe pas juste
+ D'après vous, qu'est-ce qui peut empêcher un programme de lancer le nombre maximal
de threads sur un GPU?
    
+ Le parallélisme dynamique est-il supporté sur votre carte graphique?
	- yes (Titan et Titan X, 970M également)
*/
