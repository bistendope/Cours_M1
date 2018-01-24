#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int chdir_cmd(char *argv[]){
	
}

int simple_cmd(char *argv[]){
	pid_t blop,
	int st;

	if(argv[0] == NULL){
		fprintf(stderr, "empty command.. \n");
		return 0;
	}
	
	if(strcmp[argv[0]== "cd")==0){
		return chdir_cmd(argv);
		
	}
	blop=fork();
	if(blop == -1){
		perror("fork");
		return 1;
	}
	if(blop){
		waitpid(blop, &st, 0);
		return WEXITSTATUS(st);
	}else{
		execvp(argv[0], argv);
		perror("execvp");
		exit(1);
	}
}

int simple_cmdancien(char *argv[]){
	char *sep="";
	
	while (*argv){
		printf("%s%s", sep, *argv++);
		sep=", ";
	}
	printf(".\n");
	return 0;
}

char *strip(char *s){
	char *cur;
	
	if((cur=strpbrk(s, "#"))) *cur='\0' ;
	cur=s+strlen(s)-1;
	
	if(*cur=='\n') cur--;
	while((cur>=s) && (*cur==' ')){ 
		cur--;
	}
	*(cur+1)='\0';
	for(cur=s; *cur==' '; cur++);
	
	return cur;
}

int parse_line(char *s){
	char *cur, *argv[1024];
	int i;
	
	i=0;
	argv[i++]=cur=strip(s);
	while ((cur=strpbrk(cur, " "))){
		*cur++='\0';
		while (*cur==' ') cur++;
		argv[i++]=cur;
	}
	argv[i]=NULL;
	if(strcmp("exit", argv[0])==0)exit(0);
	return simple_cmd(argv);
}

/*int simple_command(char *argv[]){
	if(argv[0] == "exit")exit(0);
	char* cur=argv[0];
	int inc=sizeof(char*);
	while(){
		return;
	}
	
}*/

int main(int argc, char *argv[]){
	char buf[1024];
	while(1){
		printf("$ "); fflush(stdout);
		parse_line(fgets(buf, 1024, stdin));
	}
}
