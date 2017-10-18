/************************************************
Pour plus d'informations sur les mutex en c++11
http://en.cppreference.com/w/cpp/thread/mutex 
et en français mal traduit : 
http://fr.cppreference.com/w/cpp/thread/mutex
************************************************/
#include <random>
#include <iostream>
#include <chrono>
#include <ctime>
#include <thread>

using namespace std;






// à compléter : tri fusion parallèle
void tri_fusion (float* atrier, int nthreads){
  
}

// à compléter : vérification que le tableau est bien trié
void verif(float* c1, float* c2){

}

int main(int argc, char* argv[]){
  random_device rd;
  mt19937 gen(rd());
  uniform_real_distribution<> dis(0, 256);
  if (argc < 3)
    {
      cout << "Mauvaise utilisation du programme :\n " << 
	"./Tri [taille du tableau] [nombre de threads]" << endl;
      return 1;
    }
  int size = atoi(argv[1]);
  int nthreads = atoi(argv[2]);  
  
  float * Atrier = new float[size];
  
  for (int i = 0; i < size ; i++){
    Atrier[i] = dis(gen);
  }
  
  
  /* à compléter */
  
  
  delete [] Atrier;
  
  return 0;
}
