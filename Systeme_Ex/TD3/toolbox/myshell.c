#include "toolbox.h"

int chdir_cmd(char *argv[])
{
  if (argv[1]!=NULL && argv[2]==NULL) {
    return chdir(argv[1]);
  } else {
    fprintf(stderr, "usage: cd directory\n");
    return 1;
  }
}

struct internalcmd {
    char *name;
    int (*cmd)(char *[]);
};

struct internalcmd internals[] = {
    { "cd", chdir_cmd },
    { NULL, NULL }
};

int simple_cmd(char *argv[])
{
    pid_t blop;
    int st, i;

    if (argv[0]==NULL) {
        fprintf(stderr, "empty command...\n");
        return 0;
    }
    for(i=0;internals[i].name;i++)
        if (strcmp(argv[0], internals[i].name)==0)
            return internals[i].cmd(argv);
    blop=fork();
    if (blop==-1) {
        perror("fork");
        return 1;
    }
    if (blop) {
        waitpid(blop, &st, 0);
        return WEXITSTATUS(st);
    } else {
        execvp(argv[0],argv);
        perror("execvp");
        exit(1);
    }
}

char *strip(char *s)
{
  char *cur;

  if ((cur=strpbrk(s, "#"))) *cur='\0';
  cur=s+strlen(s)-1;
  if (*cur=='\n') cur--;
  while ((cur>=s) && (*cur==' ')) cur--;
  *(cur+1)='\0';
  for(cur=s;*cur==' ';cur++);
  return cur;
}

int parse_line(char *s)
{
  char *cur, *argv[1024];
  int i;

  i=0;
  argv[i++]=cur=strip(s);
  while ((cur=strpbrk(cur, " "))) {
    *cur++='\0';
    while (*cur==' ') cur++;
    argv[i++]=cur;
  }
  argv[i]=NULL;
  if (strcmp("exit", argv[0])==0) exit(0);
  return simple_cmd(argv);
}

int main_myshell(int argc, char *argv[])
{
  char buf[1024];

  while(1) {
    printf("$ "); fflush(stdout);
    parse_line(fgets(buf, 1024, stdin));
  }
}