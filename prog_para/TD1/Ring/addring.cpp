#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

int addition_ring(int a, int root)
{
	int pid, nprocs;  
	MPI_Status status;
	MPI_Comm_rank(MPI_COMM_WORLD, &pid);
	MPI_Comm_size (MPI_COMM_WORLD, &nprocs);
	int in[1];
	int out[1];

	in[0]=a;

	if(pid != (root+1)%nprocs){
		MPI_Recv(out,1,MPI_INT,(pid-1+nprocs)%nprocs,34,MPI_COMM_WORLD,&status);
		in[0]+=out[0];
	}
	if (pid != root){
		MPI_Send(in,1,MPI_INT,(pid + 1)%nprocs,34,MPI_COMM_WORLD);
	}

	if(pid == root) return in[0];


}

int main ( int argc , char **argv )
{
	int pid, nprocs;  
	MPI_Status status;
	MPI_Init (&argc , &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &pid);
	MPI_Comm_size (MPI_COMM_WORLD, &nprocs);
	
	int root = atoi(argv[1]);
	
	srand(time(NULL)+pid);
	int a = rand()%10;

	cout << "je suis " << pid << " et la valeur est " << a << endl;
	
	int somme = addition_ring(a,root);
	if (pid==root)
	cout << "je suis " << pid << " et la somme est " << somme << endl;  
	
	
	MPI_Finalize() ;
	return 0 ;
}
