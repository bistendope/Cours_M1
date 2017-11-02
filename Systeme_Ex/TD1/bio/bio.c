#include <sys/errno.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include "bio.h"

BFILE *bopen(const char *filename, const char *mode)
{
	BFILE *stream;
	int flags;
	int fd;
	char md;
	
	if (strcmp(mode,"r") == 0) {
		flags = O_RDONLY;
		md = BMODE_READ;
	}else if (strcmp(mode,"w") == 0) {
		flags = O_WRONLY;
		md = BMODE_WRITE;
	}else if (strcmp(mode,"b") == 0) {
		flags = O_RDWR;
		md = BMODE_RDWR;
	} else {
		errno = EINVAL;
		return NULL;
	}
	if ((fd = open(filename, flags, PERM_FILE)) == -1)
		return NULL;
	if ((stream=(BFILE *)malloc(sizeof(BFILE)))!=NULL) {
		stream->fd=fd;
		stream->mode=md;
		stream->pos=0;
	}
	return stream;
}

ssize_t bread(void *buf, ssize_t size, BFILE *stream)
{
	char *ptr;
	ssize_t more;

	if ((stream==NULL)||(stream->mode!=BMODE_READ)) {
		errno = EBADF;
		return 0;
	}
	more=size;
	ptr=buf;
	while (stream->fill-stream->pos < more) {
		memcpy(ptr,&stream->buf[stream->pos],stream->fill-stream->pos);
		ptr+=stream->fill-stream->pos;
		more -= stream->fill - stream->pos;
		stream->pos=0;
		if ((stream->fill=read(stream->fd,stream->buf,size))<=0) {
			
			stream->eof=(stream->fill==0);
			stream->fill=0;
			return size-more;
		}
	}
	memcpy(ptr,&stream->buf[stream->pos],more);
	stream->pos+=more;
	return size;
}

/*int bflush(BFILE *stream){
	if (buf=NULL){
		stream->pos=0;
		stream->fill=0; 
		return 0;
	}else{
		return 1;
	}
}*/

ssize_t bwrite(void *buf, ssize_t size, BFILE *stream)
{
	char *ptr;
	ssize_t more;

	if ((stream==NULL)||(stream->mode!=BMODE_WRITE)) {
		errno = EBADF;
		return 0;
	}
	more=size;
	ptr=buf;
	while (stream->fill-stream->pos < more) {
		
		memcpy(&stream->buf[stream->pos],ptr,stream->fill-stream->pos);
		ptr+=stream->fill-stream->pos;
		more -= stream->fill - stream->pos;
		stream->pos=0;
		printf("avant");
		if ((stream->fill=write(stream->fd,stream->buf,1))<=0) {
			stream->eof=(stream->fill==0);
			stream->fill=0;
			return size-more;
		}
	}
	memcpy(&stream->buf[stream->pos],ptr,more);
	stream->pos+=more;
	printf("APRES");
	return size;
}

int beof(BFILE *stream)
{
	return stream->eof;
}

int bclose(BFILE *stream)
{
	int r;
	
	if (stream==NULL) {
		errno = EBADF;
		return BEOF;
	}
	if ((r=close(stream->fd))==0)
		free(stream);
	return r;
}
