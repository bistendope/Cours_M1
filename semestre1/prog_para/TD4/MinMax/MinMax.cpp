#include <random>
#include <iostream>
#include <chrono>
#include <ctime>
#ifdef _OPENMP
#include <omp.h>
#endif

using namespace std;


/* 
   Mesure et affiche le temps d'exécution d'une fonction.
   Pour mesurer le temps de calcul de l'appel f(args) :
   BENCHMARK(f(args));
*/
#define BENCHMARK(fun)							\
  do { chrono::time_point<chrono::system_clock> start, end;		\
    start = chrono::system_clock::now();				\
    fun;								\
    end = chrono::system_clock::now();					\
    chrono::duration<double> elapsed_seconds = end-start;		\
    time_t end_time = chrono::system_clock::to_time_t(end);		\
    cout << #fun 							\
	 << " elapsed time: " << elapsed_seconds.count() << "s\n";	\
  } while (0)


float min(float* v, int size){
  float min;
  int idx;
  if (size > 0){
    min = v[0];
    idx= 0;
    for (int i = 1; i < size; i++)
      //min=(min<v[i])?min:(idx=i,v[i]);
      if (min > v[i]){
	min = v[i];
	idx = i;
      }
  }
  cout << "min : " << min << " at idx : " << idx << endl;
  return min;
  
}

float max(float* v, int size){
  float max;
  int idx;
  if (size > 0){
    max = v[0];
    for (int i = 1; i < size; i++)
      max=(max>v[i])?max:(idx=i, v[i]);
  }
  cout << "max : " << max << " at idx : " << idx << endl;
  return max;
  
}

float omp_min(float* v, int size){

  return 0.;
}

float omp_max(float* v, int size){
  float max;
  int idx;
  if (size > 0){
    max = v[0];
    
    for (int i = 1; i < size; i++)
      max=(max>v[i])?max:(idx=i, v[i]);
  }
  cout << "max_omp : " << max << " at idx : " << idx << endl;
  return max;
}


float random(float max){
  return (static_cast <float> (rand()) / (static_cast <float> (RAND_MAX/max)));
}

int main(int argc, char* argv[]){
  srand (time(0));
  if (argc < 3)
    {
      cout << "Mauvaise utilisation du programme :\n " << 
	"./VecAdd [taille du tableau] [nombre de threads]" << endl;
      return 1;
    }

  int size = atoi(argv[1]);
  int nthreads = atoi(argv[2]);
  
  float* v = new float[size];

  #pragma omp parallel for 
  for (int i = 0; i < size ; i++){
    v[i] = random(size/2);
  }

  
  min(v,size);
  omp_min(v,size);

  max(v,size);
  omp_max(v,size);

  delete [] v;
}
