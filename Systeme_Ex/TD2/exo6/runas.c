#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define OFFSET 3

int runas(char *name, char *program, char *args[], int argc){
	char* myargs[argc+1];
	myargs[0]=name;
	int i;
	
	for(i=0; i< argc+1; i++){
		myargs[i+1] = args[i];
	}
	return execvp(program, myargs);
}

int main(int argc, char *argv[]){
	if (argc<3) {
		fprintf(stderr,"usage: %s name program [args...]\n",argv[0]);
		return 1;
	}
	return runas(argv[1], argv[2], argv + OFFSET, argc - OFFSET);
}
