/***********************************************
Pour plus d'informations sur les threads c++11
http://en.cppreference.com/w/cpp/thread/thread
et en français mal traduit :
http://fr.cppreference.com/w/cpp/thread/thread 
************************************************/

#include <iostream>
#include <thread>
#ifdef _OPENMP
#include <omp.h>
#endif

using namespace std;



void printHello(){
  #pragma omp parallel
  {
    #ifdef _OPENMP
    int id = omp_get_thread_num();
    #pragma omp single //nowait
    cout << "OMP runtime launched " << omp_get_num_threads() << " threads!" << endl;
    
    #else
    int id = 0;
    #endif
    

    cout << " Hello"<< id;
    cout << " World " << id << "!" << endl;
  }
}


int main(int argc, char* argv[]){
  printHello();  
}
