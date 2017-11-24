#include "toolbox.h"

int main(int argc, char *argv[])
{
  if(argc != 3) {
    fprintf(stderr, "usage: %s source_file target_file\n", argv[0]);
    exit(1);
  }
  if(link(argv[1], argv[2]) == -1) {
    perror("link");
    exit(1);
  }
  return 0;
}
