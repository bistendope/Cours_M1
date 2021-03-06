#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>

#define BUFSZ 8192

void copy(const char *src, const char *dst, mode_t mode)
{
  int fds, fdd;
  char buf[BUFSZ];
  ssize_t nb;
  if((fds = open(src, O_RDONLY)) == -1) {
    perror("open src");
    exit(1);
  }
  if((fdd = open(dst, O_WRONLY | O_CREAT | O_EXCL, mode)) == -1) {
    perror("open dst");
    exit(2);
  }
  while((nb = read(fds, buf, BUFSZ)) > 0) {
    if(write(fdd, buf, nb) == -1) {
      perror("write");
      exit(3);
    }
  }
  if(nb == -1) {
    perror("read");
    exit(4);
  }
  if(close(fdd));
  if(close(fds));
}

int main(int argc, char *argv[])
{
  struct stat buf;
  if(argc != 3) {
    fprintf(stderr, "usage: %s source_file target_file\n", argv[0]);
    exit(1);
  }
  if(stat(argv[1], &buf) == -1) {
    perror("stat");
    exit(1);
  }
  copy(argv[1], argv[2], buf.st_mode&0777);
}



