#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>

#define BUFSZ 8192

int main(int argc, char *argv[])
{
  if(argc != 3) {
    fprintf(stderr, "usage: %s source_file target_file\n", argv[0]);
    exit(1);
  }
  
  if(rename(argv[1], argv[2]) == -1) {
    if(copy(argv[1], argv[2]) == -1){
      exit(1);
    }else{
      unlink(argv[1]);
    }
  }
}
