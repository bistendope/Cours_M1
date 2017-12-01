#include "toolbox.h"

#define BUFSZ 8192

void cat(const char *path){
	char buf[BUFSZ];
	FILE *file;
	size_t nread;

	file = fopen(path, "r");
	if (file) {
		while ((nread = fread(buf, 1, sizeof buf, file)) > 0)
			fwrite(buf, 1, nread, stdout);
		if (ferror(file)) {
			perror("cat file");	
		}
		fclose(file);
	}
}

int main_cat(int argc, char *argv[])
{
	if(argc < 2) {
		fprintf(stderr, "usage: %s source_file\n", argv[0]);
		exit(1);
	}
	int i;
	for(i=1; i<argc; i++){
		cat(argv[i]);
	}
  return 0;
}



