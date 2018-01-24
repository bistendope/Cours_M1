#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

/*! 
 * Pour tester si une suite est une suite de syracuse (à partir du premier élément du tableau)
 * \param n la taille du tableau (la suite)
 * \param tab la suite à tester
 * \return 0 si ce n'est pas une suite de Syracuse 1 sinon
 */
int test_syracuse(int n, int* tab_local, int nlocal, int root)
{
	int pid, nprocs;  
	MPI_Comm_rank(MPI_COMM_WORLD, &pid ) ;
	MPI_Comm_size (MPI_COMM_WORLD, &nprocs ) ;
	MPI_Status status;
	int res=1;

	

	// on parcourt tout son tableau local pour vérifier si il est conforme à la suite de Syracuse
	for (int i=0; i<nlocal-1; i++){
		if((!(tab_local[i]%2 == 0 && tab_local[i+1] == tab_local[i]/2) && !(tab_local[i]%2 == 1 && tab_local[i+1] == tab_local[i]*3 + 1))){ // si une des deux conditions n'est pas vérifiée, on met res=0 et on sort de la boucle
			res = 0;
			break;
		}
	}

	//on prépare l'échange avec le pid suivant
	int in;
	int out;
	in = tab_local[0];
	if(pid != 0) MPI_Bsend(&in,1,MPI_INT,pid - 1,34,MPI_COMM_WORLD); // on envoie au processeur précédent sa première valeur
	if (pid != nprocs -1) MPI_Recv(&out,1,MPI_INT,pid + 1,34,MPI_COMM_WORLD,&status); // tandis qu'on la reçoit du processeur suivant
	if((!(tab_local[nlocal - 1]%2 == 0 && out == tab_local[nlocal - 1]/2) && !(tab_local[nlocal-1]%2 == 1 && out == tab_local[nlocal-1]*3 + 1))){
		// on compare sa dernière valeur avec la valeur reçue du processeur suivant
		res = 0;
	}


	if(pid != root) MPI_Bsend(&res,1,MPI_INT,root,35,MPI_COMM_WORLD);
	if (pid == root){
		int resfinal;
		if (res == 0) return res;
		for(int i = 0; i< nprocs-1; i++){
			if(i != root) MPI_Recv(&resfinal,1,MPI_INT,i,35,MPI_COMM_WORLD,&status);
			if(resfinal == 0) return resfinal;
		}
		return resfinal;
	}
	return res; // aucun interet
}


/*!
 * génération d'une suite de Syracuse à partir de U0=x
 * \param n la taille voulue
 * \param tab le tableau qui enregistre la suite
 * \param x la valeur initiale
 */
void syracuse(int n, int* tab, int x)
{
	tab[0] = x;
	for (int i=1; i<n; i++) {
		if (tab[i-1]%2==0)
			tab[i] = tab[i-1]/2;
		else
			tab[i] = 3*tab[i-1]+1;
	}
}

/*!
 * génération aléatoire d'une suite quelconque
 * \param n la taille voulue
 * \param tab le tableau qui enregistre la suite
 * \param graine pour le processus de génération aléatoire
 */
void non_syracuse(int n, int* tab, int graine)
{
	srand(time(NULL)+graine);
	//srand(2*graine+10);  
	for (int i=0; i<n; i++) 
		tab[i] = rand()%50;
}

int main ( int argc , char **argv )
{
	int pid, nprocs;  
	MPI_Init (&argc , &argv) ;
	MPI_Comm_rank(MPI_COMM_WORLD, &pid ) ;
	MPI_Comm_size (MPI_COMM_WORLD, &nprocs ) ;
	MPI_Request request;
	MPI_Status status;
	
	int n = atoi(argv[1]); // la taille du tableau global
	int root = atoi(argv[2]); // le processeur root
	int x = atoi(argv[3]); // le x pour générer une suite de syracuse
	int cas = atoi(argv[4]);  // les différentes générations possibles
	
	int* tab_global = new int[n];
	
	if (pid==root) {
		switch(cas) {
		case 0:
			cout << "test génération aléatoire"<< endl;
			non_syracuse(n,tab_global,pid);
			break;
		case 1:
			cout << "test syracuse partiel"<< endl;
			syracuse(n/2,tab_global,x); 
			non_syracuse(n-n/2,(tab_global+n/2),pid);
			break;
		default:
			cout << "test syracuse"<< endl;
			syracuse(n,tab_global,x);
		}
	}
	if (pid==root){
		cout << "je suis :" << pid << " ";
		for (int i=0; i<n; i++)
			cout << tab_global[i] << " ";
		cout << endl;
	}

	/* à compléter */

	/* le résultat n'est disponible que sur le processeur root */

	int surplus = n%nprocs;
	int nlocal = n/nprocs;
	int idebut, ifin;
	int* tab_local;

	MPI_Bcast(tab_global, n, MPI_INT, root, MPI_COMM_WORLD);

	if (pid >= surplus){
		idebut = (pid * nlocal) + surplus;
	}else{
		nlocal++;
		idebut=pid*(nlocal);
	}

	ifin = idebut + nlocal;
	tab_local = new int[nlocal];

	int y = 0;
	//cout << "pour le pid " << pid << ", idebut=" << idebut << " et ifin=" << ifin << endl;
	for(int i=idebut; i<ifin; i++){
		// cout << tab_global[i] << " " << endl;
		tab_local[y] = tab_global[i];
		y++;
	}

	cout << "tableau \"tab_local\" du pid: " << pid << endl;
	for(int i = 0; i<nlocal; i++){
		cout << tab_local[i] << " ";
	}
	cout << endl;
	
	
	int res = test_syracuse(n, tab_local, nlocal, root);

	/* à compléter */
	if (pid==root)
		cout << "test de syracuse : " << res << endl;
	
	MPI_Finalize() ;
	return 0 ;
}
