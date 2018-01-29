// pour compiler : nvcc vecAdd.cu -o vecAdd
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// CUDA kernel. Each thread takes care of one element of c
__global__ void vecAdd(float *a, float *b, float *c, int n){
  // identifiant global du thread dans la grille 1D
  int tid = blockIdx.x * blockDim.x + threadIdx.x;
  // on s'assure de ne pas sortir des limites des tableaux a,b,c
  if (tid < n){
    //on effectue une addition élémentaire par thread
    c[tid] = a[tid] + b[tid];
  }
}

int main( int argc, char* argv[] )
{
  // Size of vectors
  int n = 100000;

  // Host input vectors
  float *h_a;
  float *h_b;
  //Host output vector
  float *h_c;

  // Device input vectors
  float *d_a;
  float *d_b;
  //Device output vector
  float *d_c;

  // Size, in bytes, of each vector
  size_t size = n*sizeof(float);


  //////////////////////////////////////////
  // Allocate memory for each vector on host
  h_a = (float*) malloc (size);
  h_b = (float*) malloc (size);
  h_c = (float*) malloc (size);
  

  
  /////////////////////////////////////////
  // Allocate memory for each vector on GPU
  cudaMalloc((void**)&d_a, size);
  cudaMalloc((void**)&d_b, size);
  cudaMalloc((void**)&d_c, size);
  
  int i;
  // Initialize vectors on host
  for( i = 0; i < n; i++ ) {
    h_a[i] = sin(i)*sin(i);
    h_b[i] = cos(i)*cos(i);
  }

  /////////////////////////////////////////
  // Copy host vectors to device
  cudaMemcpy(d_a, h_a, size, cudaMemcpyHostToDevice);
  cudaMemcpy(d_b, h_b, size, cudaMemcpyHostToDevice);


  int blockSize, gridSize;

  /////////////////////////////////////////
  // Number of threads in each thread block
  blockSize = 512;

  ////////////////////////////////////////
  // Number of thread blocks in grid
  gridSize = (n + blockSize  - 1) / blockSize;


  ///////////////////////////////////////
  // Launch the kernel
  vecAdd<<<gridSize, blockSize>>>(d_a, d_b, d_c, n);

  ///////////////////////////////////////
  // Copy array back to host
  cudaMemcpy(h_c, d_c, size, cudaMemcpyDeviceToHost);

  // Sum up vector c and print result divided by n, this should equal 1 within error
  float sum = 0;
  for(i=0; i<n; i++)
    sum += h_c[i];
  printf("final result: %f\n", sum/n);

  /////////////////////////////////////////
  cudaFree(d_a);
  cudaFree(d_b);
  cudaFree(d_c);

  ////////////////////////////////////////
  // Release host memory
  free(h_a);
  free(h_b);
  free(h_c);

  return 0;
}
