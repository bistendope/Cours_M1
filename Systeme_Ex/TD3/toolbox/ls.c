#include "toolbox.h"

int main_ls(int argc, char *argv[])
{
  DIR *d;
  struct dirent *dir;
if(argc==1){
    d = opendir(".");
}else if(argc==2){
    d = opendir(argv[1]);
}else{
    fprintf(stderr, "usage: %s directory or none\n", argv[0]);
    exit(1);
}
  if (d)
  {
    while ((dir = readdir(d)) != NULL)
    {
      printf("%s\n", dir->d_name);
    }

    closedir(d);
  }

  return(0);
}