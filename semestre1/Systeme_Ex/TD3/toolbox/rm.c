#include "toolbox.h"

int main_rm(int argc, char *argv[])
{

    if(argc != 2) {
        fprintf(stderr, "usage: %s file\n", argv[0]);
        exit(1);
    }
    if(unlink(argv[1]) == -1) {
        perror("unlink");
        return 1;
    }
    return 0;
}