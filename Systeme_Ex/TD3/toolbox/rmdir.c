#include "toolbox.h"

int main(int argc, char *argv[])
{
  if(argc != 2) {
    fprintf(stderr, "usage: %s directory_name\n", argv[0]);
    exit(1);
  }
  if(rmdir(argv[1]) == -1) {
    perror("rmdir");
    exit(1);
  }
  return 0;
}
