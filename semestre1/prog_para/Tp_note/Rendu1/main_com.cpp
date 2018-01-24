#include "fonctions.hpp"
#include <mpi.h>
#include <assert.h>
int main ( int argc , char **argv )
{
	int pid, nprocs;	
	MPI_Init (&argc , &argv) ;
	MPI_Comm_rank(MPI_COMM_WORLD, &pid ) ;
	MPI_Comm_size (MPI_COMM_WORLD, &nprocs ) ;
	MPI_Comm COMM_CART;
	//	srand(time(NULL)+pid);
	srand(pid);

	int n = atoi(argv[1]); 
	int m = atoi(argv[2]); // les deux arguments pour une matrice n x m
	int root = atoi(argv[3]); // le processeur root des communications collectives etc
	
	int* nbprocs_dims = new int[2];
	nbprocs_dims[0] = 0;
	nbprocs_dims[1] = 0;
	
	int* period = new int[2];
	period[0] = 1;
	period[1] = 1;
	
	MPI_Dims_create(nprocs, 2, nbprocs_dims);
	assert((n%nbprocs_dims[0]==0));
	assert((m%nbprocs_dims[1]==0));

	MPI_Cart_create(MPI_COMM_WORLD, 2, nbprocs_dims, period, false, &COMM_CART);
	
	int* mycoords = new int[2];
	MPI_Cart_coords(COMM_CART, pid, 2, mycoords);
	
	float* jeu = NULL;
	float* jeu_res = NULL;
	if (pid==root) {
		jeu = new float[n*m];
		jeu_res = new float[n*m];
		generation_float(n,m,jeu);
	}
	
	int n_local = n/nbprocs_dims[0];
	int m_local = m/nbprocs_dims[1];
	
	float* jeu_local = new float[n_local*m_local];

	float* jeu_local_shift = new float[n_local*m_local];
	
	distribution(jeu,n,m,nbprocs_dims,jeu_local,root,COMM_CART);
	
	shift_gauche(jeu_local, n_local*m_local, MPI_FLOAT, jeu_local_shift, COMM_CART);
	
	shift_droite(jeu_local_shift, n_local*m_local, MPI_FLOAT, jeu_local, COMM_CART);
	
	rassemblement(jeu_res,n,m,nbprocs_dims,jeu_local,root,COMM_CART);
	
	
	if (pid==root) {
		bool test = true;
		for (int i=0; i<n*m; i++) 
			if (jeu_res[i] != jeu[i]) {
	test = false;
	break;
			}
		cout << "le résultat est : " << test << endl;
	}
	
	
	delete[] nbprocs_dims;
	delete[] period;
	delete[] mycoords;
	if (jeu!=NULL)
		delete[] jeu;
	
	MPI_Finalize();
	
	return 0 ;
}
