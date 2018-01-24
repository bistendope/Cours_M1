#include "fonctions.hpp"
#include <iostream>
#include <fstream>
 

void generation_float(int n, int m, float* jeu)
{
  for (int i=0; i<n*m; i++)
    jeu[i] = rand()/100000000.0;
}

void distribution(float* tab, int n, int m, int* nbprocs_dims, float* tab_local, int root, MPI_Comm Comm)
{
  
  int n_loc = n / nbprocs_dims[0];
  int m_loc = m / nbprocs_dims[1];

  MPI_Scatter(tab, n_loc * m_loc, MPI_FLOAT, tab_local, n_loc * m_loc, MPI_FLOAT, root, Comm);
  
}

void rassemblement(float* tab, int n, int m, int* nbprocs_dims, float* tab_local, int root, MPI_Comm Comm)
{

  int n_loc = n / nbprocs_dims[0];
  int m_loc = m / nbprocs_dims[1];
  
  MPI_Gather(tab_local, n_loc * m_loc, MPI_FLOAT, tab, n_loc * m_loc, MPI_FLOAT, root, Comm);
  
}



bool shift_droite(void* send, int count, MPI_Datatype datatype, void* recv, MPI_Comm Comm)
{
  MPI_Status status;
  int nleft, nright, pid;
  MPI_Comm_rank(MPI_COMM_WORLD, &pid ) ;
  MPI_Cart_shift(Comm, 1, -1, &nright, &nleft);
  cout<<"pid: "<< pid<< ", nright=" << nright << ", nleft=" << nleft << endl;
  if(nright != NULL){
  	MPI_Sendrecv(send, count, datatype, nright, 1, recv, count, datatype, pid, 1, Comm, &status);
  	return true;
  }else{
  	return false;
  }
}


bool shift_gauche(void* send, int count, MPI_Datatype datatype, void* recv, MPI_Comm Comm)
{

  MPI_Status status;
  int nleft, nright, pid;
  MPI_Comm_rank(MPI_COMM_WORLD, &pid ) ;
  MPI_Cart_shift(Comm, 1, -1, &nright, &nleft);
  cout<<"pid: "<< pid<< ", nright=" << nright << ", nleft=" << nleft << endl;
  if(nleft != NULL){
  	MPI_Sendrecv(send, count, datatype, nleft, 1, recv, count, datatype, pid, 1, Comm, &status);
  	return true;
  }else{
  	return false;
  }
}

bool shift_haut(void* send, int count, MPI_Datatype datatype, void* recv, MPI_Comm Comm)
{
  MPI_Status status;
  int nup, ndown, pid;
  MPI_Comm_rank(MPI_COMM_WORLD, &pid ) ;
  MPI_Cart_shift(Comm, 0, -1, &nup, &ndown);
  cout<<"pid: "<< pid<< ", nup=" << nup << ", ndown=" << ndown << endl;
  if(nup != 0){
  	MPI_Sendrecv(send, count, datatype, nup, 1, recv, count, datatype, pid, 1, Comm, &status);
  	return true;
  }else{
  	return false;
  }
}

bool shift_bas(void* send, int count, MPI_Datatype datatype, void* recv, MPI_Comm Comm)
{
  MPI_Status status;
  int nup, ndown, pid;
  MPI_Comm_rank(MPI_COMM_WORLD, &pid ) ;
  MPI_Cart_shift(Comm, 0, -1, &nup, &ndown);
  cout<<"pid: "<< pid<< ", nup=" << nup << ", ndown=" << ndown << endl;
  if(ndown != 0){
  	MPI_Sendrecv(send, count, datatype, ndown, 1, recv, count, datatype, pid, 1, Comm, &status);
  	return true;
  }else{
  	return false;
  }
}




