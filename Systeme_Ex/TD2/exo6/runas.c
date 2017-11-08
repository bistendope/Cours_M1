#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define OFFSET 3

int runas(char *name, char *program, char *args[], int argc){
	char *myargs[argc];
	strcpy(myargs[0], name);
	for(int i=0; i< argc; i++){
		printf("%s \n", myargs[i]);
	}
	for(int i=1; i< argc; i++){
		strcpy(myargs[i], args[i-1]);
	}
	return execvp(program, myargs);
}

int main(int argc, char *argv[]){
	return runas(argv[1], argv[2], argv+OFFSET, argc - OFFSET);
}
