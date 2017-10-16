#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#define INDICE 0
#define VALEUR 1

using namespace std;

/*!
 * pour calculer un indice local � partir d'un indice global
 * /param n la taille totale du tableau
 * /param i_global l'indice dans le tableau complet
 * /return i_local l'indice du tableau en local
 */
int global_to_local(int n, int i_global)
{
	int pid, nprocs;  
	MPI_Comm_rank(MPI_COMM_WORLD, &pid );
	MPI_Comm_size (MPI_COMM_WORLD, &nprocs );
	MPI_Status status;

	int surplus = n%nprocs;
	int nlocal = n/nprocs;

	if(pid >= (surplus)){ 
		return (i_global - surplus)%nlocal;
	}else{
		return i_global%(nlocal+1);
	}
}

/*!
 * pour calculer un indice global � partir d'un indice local
 * /param n la taille totale du tableau
 * /param i_local l'indice local initial
 * /return i_global l'indice de l'�l�ment correspondant dans le tableau global
 */
int local_to_global(int n, int i_local)
{
	int pid, nprocs;  
	MPI_Comm_rank(MPI_COMM_WORLD, &pid );
	MPI_Comm_size (MPI_COMM_WORLD, &nprocs );
	MPI_Status status;

	int surplus = n%nprocs;
	int nlocal = n/nprocs;

	if(pid >= (surplus)){ 
		return pid * nlocal + surplus + i_local;
	}else{
		return pid * (nlocal+1) + i_local;
	}
}

/*!
 * pour calculer le num�ro du processeur destinataire � partir d'un indice dans le tableau total
 * /param n la taille totale du tableau
 * /param i_global l'indice dans le tableau global
 * /return pid_dest le num�ro du processeur propri�taire de l'�l�ment
 */
int pid_dest(int n, int i_global)
{
	int pid, nprocs;  
	MPI_Comm_rank(MPI_COMM_WORLD, &pid );
	MPI_Comm_size (MPI_COMM_WORLD, &nprocs );
	MPI_Status status;

	int surplus = n%nprocs;
	int nlocal = n/nprocs;

	if(pid >= (surplus)){ 
		return (i_global-surplus)/nlocal;
	}else{
		return i_global/(nlocal+1);
	}
}

/*! 
 * Petite fonction pour etre s�r de g�n�rer un tableau dont tous les �l�ments sont distincts
 * \param n la taille du tableau total
 * \param tab le tableau � construire
 * \param graine pour initialiser le processus de g�n�ration al�atoire de nombre
 */
void generation(int n, int* tab, int graine)
{  
	srand(time(NULL)+graine);
	//srand(2*graine+10);
	for (int i=0; i<n; i++) {
		bool test = true;
		while (test) {
			test = false;
			tab[i] = rand()%50;
			for (int j=0; j<i; j++) 
	if (tab[i]==tab[j]) 
		test = true;
		}
	}
}

int main ( int argc , char **argv )
{
	int pid, nprocs;  
	MPI_Init (&argc , &argv) ;
	MPI_Comm_rank(MPI_COMM_WORLD, &pid ) ;
	MPI_Comm_size (MPI_COMM_WORLD, &nprocs ) ;
	MPI_Request request;
	MPI_Status status;
	
	int n = atoi(argv[1]);
	int root = atoi(argv[2]);

	int* tab_global = new int[n];
	int* tab_local;
	int* positions;
	int* tab_local_final;
	
	int surplus = n%nprocs;
	int nlocal = n/nprocs;
	int p, idebut, ifin;
	

	if (pid==root)   
		generation(n,tab_global,pid); 

	MPI_Bcast(tab_global, n, MPI_INT, root, MPI_COMM_WORLD);

	// calcul des indices pour la première boucle for
	if (pid >= surplus){
		idebut = (pid * nlocal) + surplus;
	}else{
		nlocal++;
		idebut=pid*(nlocal);
	}

	ifin = idebut + nlocal;

	tab_local_final = new int[nlocal];
	tab_local = new int[nlocal];
	positions = new int[nlocal];

	int x = 0;
	for(int i=idebut; i<ifin; i++){
		tab_local[x] = tab_global[i];
		x++;
	}

	// cout << "tableau \"tab_local\" du pid: " << pid << endl;
	// for(int i = 0; i<nlocal; i++){
	// 	cout << tab_local[i] << " ";
	// }
	// cout << endl;

	// tri par induction parallélisé
	for(int i = 0; i < nlocal; i++){
		p = 0;
		for (int j=0; j < n; j++){
			if(tab_global[j] < tab_local[i]){
				p++;
			}
		}
		positions[i] = p;
	}

	int in[2];
	int out[2];
	int pidest, plocal;
	for (int i=0; i<nlocal; i++){
		pidest = pid_dest(n, positions[i]);
		plocal = global_to_local(n, positions[i]);
		if(pidest == pid){
			tab_local_final[plocal] = tab_local[i];
		}else{
			in[INDICE] = plocal;
			in[VALEUR] = tab_local[i];
			MPI_Bsend(in,2,MPI_INT,pidest,34,MPI_COMM_WORLD);
			MPI_Recv(out,2,MPI_INT,MPI_ANY_SOURCE,34,MPI_COMM_WORLD,&status);
			//cout << "le pid " << pid << " vient d'envoyer la valeur " << in[VALEUR] << " d'indice " << in[INDICE] << " à " << pidest << " et vient de recevoir la valeur " << out[VALEUR] << " d'indice " << out[INDICE] << endl;
			tab_local_final[out[INDICE]] = out[VALEUR];
			
		}
	}


	// affichage du résultat sur chaque processeur

	cout << "tableau \"tab_local_final\" du pid: " << pid << endl;
	for(int i = 0; i<nlocal; i++){
		cout << tab_local_final[i] << " ";
	}
	cout << endl;
	
	MPI_Finalize() ;
	return 0 ;
}
