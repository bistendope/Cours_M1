
#include <stdio.h>
#include <unistd.h>
#include "bio.h"

#define SZ 10000

int main(int argc, char *argv[])
{
	BFILE *bfdest;
	BFILE *bfsrc;
	ssize_t nb;
	char buf[SZ];
	
	if (argc!=3) {
		fprintf(stderr,"usage: %s dest_filename src_filename\n",argv[0]);
		return 1;
	}
	if ((bfdest=bopen(argv[1],"w"))==NULL) {
		perror("bopen");
		return 2;
	}
	if ((bfsrc=bopen(argv[2],"r"))==NULL) {
		perror("bopen");
		return 3;
	}
	while ((nb=bread(buf,SZ,bfsrc))==SZ)
		bwrite(buf,nb,bfdest);
	if (nb>0)
		bwrite(buf,nb,bfdest);
	
	bclose(bfdest);
	bclose(bfsrc);
	return 0;
}
