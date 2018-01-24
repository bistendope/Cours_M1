#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// CUDA kernel. Each thread takes care of one element of c
__global__ void vecAdd(float *a, float *b, float *c, int n)
{

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
  size_t bytes = n*sizeof(float);


  //////////////////////////////////////////
  // Allocate memory for each vector on host

  /////////////////////////////////////////
  // Allocate memory for each vector on GPU

  int i;
  // Initialize vectors on host
  for( i = 0; i < n; i++ ) {
    h_a[i] = sin(i)*sin(i);
    h_b[i] = cos(i)*cos(i);
  }

  /////////////////////////////////////////
  // Copy host vectors to device
  // Use cudaMemcpy...


  int blockSize, gridSize;

  /////////////////////////////////////////
  // Number of threads in each thread block
  blockSize = ??;

  ////////////////////////////////////////
  // Number of thread blocks in grid
  gridSize = ??;


  ///////////////////////////////////////
  // Execute the kernel
  vecAdd<<<??, ??>>>(d_a, d_b, d_c, n);

  ///////////////////////////////////////
  // Copy array back to host
  // Use cudaMemcpy

  // Sum up vector c and print result divided by n, this should equal 1 within error
  float sum = 0;
  for(i=0; i<n; i++)
    sum += h_c[i];
  printf("final result: %f\n", sum/n);

  /////////////////////////////////////////
  // Release device memory

  ////////////////////////////////////////
  // Release host memory

  return 0;
}
