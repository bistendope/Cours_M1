/*#include <stdio.h>
#include "bio.h"

#define SZ 100

int main(int argc, char *argv[])
{
	BFILE *bf;
	ssize_t nb;
	char buf[SZ];
	
	if (argc!=2) {
		fprintf(stderr,"usage: %s filename\n",argv[0]);
		return 1;
	}
	if ((bf=bopen(argv[1],"r"))==NULL) {
		perror("bopen");
		return 2;
	}
	while ((nb=bread(buf,SZ,bf))==SZ)
		fwrite(buf,SZ,1,stdout);
	if (nb>0)
		fwrite(buf,nb,1,stdout);
	if (!beof(bf))
		perror("bread");
	bclose(bf);
	return 0;
}

*/
#include <stdio.h>
#include <unistd.h>

#include "bio.h"

#define SZ 100

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
		bwrite(buf,SZ,bfdest);
	if (nb>0)
		bread(buf,nb,bfsrc);
		bwrite(buf,nb,bfdest);
	
	bclose(bfdest);
	bclose(bfsrc);
	return 0;
}
