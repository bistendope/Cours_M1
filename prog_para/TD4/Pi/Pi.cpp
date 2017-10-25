/***********************************************
Pour plus d'informations sur les threads c++11
http://en.cppreference.com/w/cpp/thread/thread
et en français mal traduit :
http://fr.cppreference.com/w/cpp/thread/thread 
************************************************/

#include <random>
#include <iostream>
#include <chrono>
#include <ctime>
#ifdef _OPENMP
#include <omp.h>
#endif

using namespace std;

double mrandom(){
  return (static_cast <double> (rand()) / (static_cast <double> (RAND_MAX)));
}


int main (int argc, char* argv[]){
  long i;
  long dansDisque = 0;
  double pi, x, y;
  double r = 1.0; // radius of circle. Side of square is 2*r
  srand(time(0));

  if (argc < 3)
    {
      cout << "Mauvaise utilisation du programme :\n " << 
	argv[0] << " [nombre d'essais] [nombre de threads]" << endl;
      return 1;
    }

  long nb_essais = atoi(argv[1]);
  int nthreads = atoi(argv[2]);
  
  #pragma omp parallel for private(x,y) num_threads(nthreads)
  for(i=0;i<nb_essais; i++)
    {
      x = mrandom();
      y = mrandom();
      if (( x*x + y*y) <= r*r)
      	#pragma omp critical
	dansDisque++;
    }
  
  pi = 4.0 * ((double)dansDisque/(double)nb_essais);
  cout << "\n " <<  nb_essais << " trials, pi is " << pi  << endl;
}
