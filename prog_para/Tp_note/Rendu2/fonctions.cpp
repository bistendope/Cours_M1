#include "fonctions.hpp"
#include <iostream>
#include <fstream>
 

void generation_float(int n, int m, float* jeu)
{
  for (int i=0; i<n*m; i++)
    jeu[i] = rand()/100000000.0; 
}
void lecture(float* & grille, int & n, int & m, const char* nom_file)
{
  std::ifstream file(nom_file, std::ifstream::in);
  file >> n >> m;
  grille = new float[n*m];
  for (int i=0; i<n; i++)
    for (int j=0; j<m; j++)
      file >> grille[i*m+j];
  file.close();
}

void ecriture(float* grille, int n, int m, const char* nom_file)
{
  std::ofstream file(nom_file, std::ofstream::out);
  file << n<< " " << m << std::endl;
  for (int i=0; i<n; i++){
    for (int j=0; j<m; j++)
      file << grille[i*m+j]<< " ";
    file << std::endl;
  }
  file.close();
}




void distribution(float* tab, int n, int m, int* nbprocs_dims, float* tab_local, int root, MPI_Comm Comm)
{
  int pid, nprocs;  
  MPI_Status status;

  MPI_Comm_rank(MPI_COMM_WORLD, &pid);
  MPI_Comm_size (MPI_COMM_WORLD, &nprocs);
  
  int p1 = nbprocs_dims[0];
  int p2 = nbprocs_dims[1];
  int n_local = n/p1;
  int m_local = m/p2;
  
  float* send = NULL;
  if (pid==root) {
    int* coords = new int[2];
    send = new float[n_local*m_local];
    for (int i=0; i<nbprocs_dims[0]; i++) {
      for (int j=0; j<nbprocs_dims[1]; j++) {
	coords[0] = i;
	coords[1] = j;
	int dest;
	MPI_Cart_rank(Comm,coords,&dest);
	int ptr = 0;
	for (int k=0; k<n_local; k++) {
	  for (int l=0; l<m_local; l++) {
	    send[ptr] = tab[i*(m*n_local)+j*m_local+k*m+l];
	    ptr++;
	  }
	}
	if (dest!=root) 
	  MPI_Ssend(send,n_local*m_local,MPI_FLOAT,dest,10,MPI_COMM_WORLD);
	else {
	  for (int k=0; k<n_local; k++) 
	    for (int l=0; l<m_local; l++) 
	      tab_local[k*m_local+l] = tab[i*(m*n_local)+j*m_local+k*m+l];
	}
      }
    }
  }
  else
    MPI_Recv(tab_local,n_local*m_local,MPI_FLOAT,root,10,MPI_COMM_WORLD,&status);
}




void rassemblement(float* tab, int n, int m, int* nbprocs_dims, float* tab_local, int root, MPI_Comm Comm)
{
  int pid, nprocs;  
  MPI_Status status;

  MPI_Comm_rank(MPI_COMM_WORLD, &pid);
  MPI_Comm_size (MPI_COMM_WORLD, &nprocs);

  int p1 = nbprocs_dims[0];
  int p2 = nbprocs_dims[1];
  int n_local = n/p1;
  int m_local = m/p2;

  float* recv = NULL;
  
  if (pid==root) {
    recv = new float[n_local*m_local];
    int* coords = new int[2];
    for (int i=0; i<nbprocs_dims[0]; i++) {
      for (int j=0; j<nbprocs_dims[1]; j++) {
	coords[0] = i;
	coords[1] = j;
	int src;
	MPI_Cart_rank(Comm,coords,&src);
	if (src != root) { 
	  MPI_Recv(recv,n_local*m_local,MPI_FLOAT,src,10,MPI_COMM_WORLD,&status);
	  for (int k=0; k<n_local; k++) 
	    for (int l=0; l<m_local; l++) 
	      tab[i*(m*n_local)+j*m_local+k*m+l] = recv[k*m_local+l];
	}
	else {
	  for (int k=0; k<n_local; k++) 
	    for (int l=0; l<m_local; l++) 
	      tab[i*(m*n_local)+j*m_local+k*m+l] = tab_local[k*m_local+l];
	}
      }
    }
  }
  else
    MPI_Ssend(tab_local,n_local*m_local,MPI_FLOAT,root,10,MPI_COMM_WORLD);
}



bool shift_droite(void* send, int count, MPI_Datatype datatype, void* recv, MPI_Comm Comm)
{
  bool recu = true;
  MPI_Status status;
  
  int agauche;
  int adroite;
  
  MPI_Cart_shift(Comm, 1, 1, &agauche, &adroite);  
  if (agauche>=0 && adroite>=0)  
    MPI_Sendrecv(send,count,datatype,adroite,10,recv,count,datatype,agauche,10,MPI_COMM_WORLD,&status);
  else if (adroite>=0) {
    MPI_Ssend(send,count,datatype,adroite,10,MPI_COMM_WORLD);
    recu = false;
  }
  else 
    MPI_Recv(recv,count,datatype,agauche,10,MPI_COMM_WORLD,&status);  
  return recu;
}


bool shift_gauche(void* send, int count, MPI_Datatype datatype, void* recv, MPI_Comm Comm)
{
  bool recu = true;
  MPI_Status status;
  int agauche;
  int adroite;
  
  MPI_Cart_shift(Comm, 1, 1, &agauche, &adroite);  
  if (agauche>=0 && adroite>=0)  
    MPI_Sendrecv(send,count,datatype,agauche,10,recv,count,datatype,adroite,10,MPI_COMM_WORLD,&status);
  else if (agauche>=0) {
    recu = false;
    MPI_Ssend(send,count,datatype,agauche,10,MPI_COMM_WORLD);
  }
  else 
    MPI_Recv(recv,count,datatype,adroite,10,MPI_COMM_WORLD,&status);  
  return recu;
}

bool shift_haut(void* send, int count, MPI_Datatype datatype, void* recv, MPI_Comm Comm)
{
  bool recu = true;
  MPI_Status status;
  int enhaut;
  int enbas;
  
  MPI_Cart_shift(Comm, 0, 1, &enhaut, &enbas);  
  
  if (enhaut>=0 && enbas>=0)  
    MPI_Sendrecv(send,count,datatype,enhaut,10,recv,count,datatype,enbas,10,MPI_COMM_WORLD,&status);
  else if (enhaut>=0) {
    recu = false;
    MPI_Ssend(send,count,datatype,enhaut,10,MPI_COMM_WORLD);
  }
  else 
    MPI_Recv(recv,count,datatype,enbas,10,MPI_COMM_WORLD,&status);  
  return recu;
}

bool shift_bas(void* send, int count, MPI_Datatype datatype, void* recv, MPI_Comm Comm)
{
  bool recu = true;
  MPI_Status status;  
  int enhaut;
  int enbas;    
  
  MPI_Cart_shift(Comm, 0, 1, &enhaut, &enbas);  
  if (enhaut>=0 && enbas>=0)  
    MPI_Sendrecv(send,count,datatype,enbas,10,recv,count,datatype,enhaut,10,MPI_COMM_WORLD,&status);
  else if (enbas>=0) {
    recu = false;
    MPI_Ssend(send,count,datatype,enbas,10,MPI_COMM_WORLD);
  }
  else 
    MPI_Recv(recv,count,datatype,enhaut,10,MPI_COMM_WORLD,&status);  
  return recu;
}

void echange_ghost(float* grille, int n, int m, MPI_Comm Comm)
{
	/*for(int i=0; i<n; i++){
		for(int j=0;<m; j++){
			if(i==0){
				shift_gauche();
			}
			if(i==n){
				shift_droite();
			}
			if(j==0){
				shift_haut();
			}
			if(j==m){
				shift_bas();
			}
		}
	}*/
	
	shift_gauche(grille, m_local, MPI_FLOAT, grille+n_local, Comm);
	shift_droite(grille+n_local, m_local, MPI_FLOAT, grille, Comm);
	shift_haut(grille, n_local, MPI_FLOAT, grille+m_local, Comm);
	shift_bas(grille+m_local, n_local, MPI_FLOAT, grille, Comm);
	
}

void chaleur(float* grille, int n, int m, float lambda)
{ 
	
}



