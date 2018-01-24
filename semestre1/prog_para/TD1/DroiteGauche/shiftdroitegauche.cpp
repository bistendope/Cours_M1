#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

void shift_droite_gauche_circulaire(int nlocal, int* in, int* out)
{
	int pid, nprocs;  
	MPI_Status status;
	MPI_Comm_rank(MPI_COMM_WORLD, &pid);
	MPI_Comm_size (MPI_COMM_WORLD, &nprocs);

	int inGauche[nlocal/2];
	int inDroite[nlocal/2];
	for(int i=0;i<nlocal/2; i++){
		inGauche[i]=in[i];
	}
	for(int i=nlocal/2;i<nlocal; i++){
		inDroite[i-nlocal/2]=in[i];
	}
	int outGauche[nlocal/2];
	int outDroite[nlocal/2];

	if(pid % 2 == 0){
		MPI_Send(inDroite,nlocal/2,MPI_INT,(pid + 1)%nprocs,34,MPI_COMM_WORLD);
		MPI_Recv(outDroite,nlocal/2,MPI_INT,(pid + 1)%nprocs,35,MPI_COMM_WORLD,&status);
		MPI_Send(inGauche,nlocal/2,MPI_INT,(pid - 1 + nprocs)%nprocs,35,MPI_COMM_WORLD);
		MPI_Recv(outGauche,nlocal/2,MPI_INT,(pid - 1 + nprocs)%nprocs,34,MPI_COMM_WORLD,&status);
	}else{
		MPI_Recv(outGauche,nlocal/2,MPI_INT,(pid - 1 + nprocs)%nprocs,34,MPI_COMM_WORLD,&status);
		MPI_Send(inGauche,nlocal/2,MPI_INT,(pid - 1 + nprocs)%nprocs,35,MPI_COMM_WORLD);
		MPI_Recv(outDroite,nlocal/2,MPI_INT,(pid + 1)%nprocs,35,MPI_COMM_WORLD,&status);
		MPI_Send(inDroite,nlocal/2,MPI_INT,(pid + 1)%nprocs,34,MPI_COMM_WORLD);
		
	}

	for(int i=0;i<nlocal/2; i++){
		out[i]=outGauche[i];
	}
	for(int i=nlocal/2;i<nlocal; i++){
		out[i]=outDroite[i-nlocal/2];
	}
}

// void shift_droite_gauche(int nlocal, int* in, int* out)
// {
// 	int pid, nprocs;  
// 	MPI_Status status;
// 	MPI_Comm_rank(MPI_COMM_WORLD, &pid);
// 	MPI_Comm_size (MPI_COMM_WORLD, &nprocs);

// 	int inGauche[nlocal/2];
// 	int inDroite[nlocal/2];
// 	for(int i=0;i<nlocal/2; i++){
// 		inGauche[i]=in[i];
// 	}
// 	for(int i=nlocal/2;i<nlocal; i++){
// 		inDroite[i-nlocal/2]=in[i];
// 	}
// 	int outGauche[nlocal/2];
// 	int outDroite[nlocal/2];

// 	if(pid % 2 == 0){
// 		MPI_Send(inDroite,nlocal/2,MPI_INT,(pid + 1)%nprocs,34,MPI_COMM_WORLD);
// 		MPI_Recv(outDroite,nlocal/2,MPI_INT,(pid + 1)%nprocs,35,MPI_COMM_WORLD,&status);
// 		MPI_Send(inGauche,nlocal/2,MPI_INT,(pid - 1 + nprocs)%nprocs,35,MPI_COMM_WORLD);
// 		MPI_Recv(outGauche,nlocal/2,MPI_INT,(pid - 1 + nprocs)%nprocs,34,MPI_COMM_WORLD,&status);
// 	}else{
// 		MPI_Recv(outGauche,nlocal/2,MPI_INT,(pid - 1 + nprocs)%nprocs,34,MPI_COMM_WORLD,&status);
// 		MPI_Send(inGauche,nlocal/2,MPI_INT,(pid - 1 + nprocs)%nprocs,35,MPI_COMM_WORLD);
// 		MPI_Recv(outDroite,nlocal/2,MPI_INT,(pid + 1)%nprocs,35,MPI_COMM_WORLD,&status);
// 		MPI_Send(inDroite,nlocal/2,MPI_INT,(pid + 1)%nprocs,34,MPI_COMM_WORLD);
// 	}

// 	for(int i=0;i<nlocal/2; i++){
// 		out[i]=outGauche[i];
// 	}
// 	for(int i=nlocal/2;i<nlocal; i++){
// 		out[i]=outDroite[i-nlocal/2];
// 	}

// }

int main ( int argc , char **argv )
{
	int pid, nprocs;  
	MPI_Status status;
	MPI_Init (&argc , &argv) ;
	MPI_Comm_rank(MPI_COMM_WORLD, &pid ) ;
	MPI_Comm_size (MPI_COMM_WORLD, &nprocs ) ;
	
	int nlocal = 2*atoi(argv[1]);
	int* tablocal = new int[nlocal];
	int* tabres = new int[nlocal];

	srand(time(NULL)+pid);
	for (int i=0; i<nlocal; i++)
		tablocal[i] = rand()%10;
	
	cout << "je suis " << pid << " et j'ai le tableau initial : ";
	for (int i=0; i<nlocal; i++)
		cout << tablocal[i] << " ";
	cout << endl;

	shift_droite_gauche_circulaire(nlocal,tablocal,tabres);
	
	cout << "je suis " << pid << " et j'ai le tableau final : ";
	for (int i=0; i<nlocal; i++)
		cout << tabres[i] << " ";
	cout << endl;
	
	delete[] tablocal;
	delete[] tabres;
	MPI_Finalize() ;
	return 0 ;
}
