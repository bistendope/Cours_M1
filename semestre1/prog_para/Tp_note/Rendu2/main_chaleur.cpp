#include "fonctions.hpp"

int main ( int argc , char **argv )
{
  int pid, nprocs;  
  MPI_Init (&argc , &argv) ;
  MPI_Comm_rank(MPI_COMM_WORLD, &pid ) ;
  MPI_Comm_size (MPI_COMM_WORLD, &nprocs ) ;
  MPI_Comm COMM_CART;

  float* jeu = NULL;
  
  int n;
  int m;
  int dim_mat[2];
  int nbiter = atoi(argv[1]);// le nombre d'itérations
  int root = atoi(argv[2]);// le processeur root
  char* nom_fic_entree=argv[3];// Le fichier d'entrée  (1ère ligne n et m puis la matrice)
  char* nom_fic_sortie=argv[4];// Le fichier pour écrire les résultats
  
  
  if (pid==root) {
    lecture(jeu,n,m,nom_fic_entree);
    cout <<"n="<<n<<" m="<<m<<endl;
    for (int i=0; i<n; i++){
      for (int j=0; j<m;j++)
	cout << jeu[i*m+j] << " ";
      cout << endl;
    }
    dim_mat[0]=n;
    dim_mat[1]=m;
  }
  
  MPI_Bcast(dim_mat, 2, MPI_INT, root, MPI_COMM_WORLD);
  n=dim_mat[0];
  m=dim_mat[1];

  int* nbprocs_dims = new int[2];
  nbprocs_dims[0] = 0;
  nbprocs_dims[1] = 0;
  
  int* period = new int[2];
  period[0] = 1;
  period[1] = 1;
  
  MPI_Dims_create(nprocs, 2, nbprocs_dims);
  
  MPI_Cart_create(MPI_COMM_WORLD, 2, nbprocs_dims, period, false, &COMM_CART);
  
  int* mycoords = new int[2];
  MPI_Cart_coords(COMM_CART, pid, 2, mycoords);
  
  
  int n_local = n/nbprocs_dims[0];
  int m_local = m/nbprocs_dims[1];
  
  float* jeu_local = new float[n_local*m_local];
  
  distribution(jeu,n,m,nbprocs_dims,jeu_local,root,COMM_CART);

  
  float* grille = new float[(n_local+2)*(m_local+2)];
  
  echange_ghost(grille, n, m, COMM_CART);

  delete[] nbprocs_dims;
  delete[] period;
  delete[] mycoords;
  if (jeu!=NULL)
    delete[] jeu;

  MPI_Finalize();

  return 0 ;
}
