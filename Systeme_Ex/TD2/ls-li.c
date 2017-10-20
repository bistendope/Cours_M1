#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <dirent.h>

#define BUFSZ 8192
int compareSize(const void *const A, const void *const B)
{
    return strcmp((*(struct dirent **) A)->d_name, (*(struct dirent **) B)->d_name);
}

void printSortedNames(const char *const path)
{	
	struct stat buf;

	
    int count;
    DIR *dir;
    struct dirent **list;
    struct dirent *entry;

    dir = opendir(path);
    if (dir == NULL)
    {
        fprintf(stderr, "cannot open `%s'\n", path);
        return;
    }

    /* First determine the number of entries */
    count = 0;
    while ((entry = readdir(dir)) != NULL)
        ++count;
    /* Allocate enough space */
    list = malloc(count * sizeof(*list));
    if (list == NULL)
    {
        closedir(dir);
        fprintf(stderr, "memory exhausted.\n");
        return;
    }
    /* You don't need to allocate the list elements
     * you can just store pointers to them in the
     * pointer array `list'
     */
    rewinddir(dir); /* reset position */
    /* Save the pointers allocated by `opendir()' */
    count = 0;
    while ((entry = readdir(dir)) != NULL)
        list[count++] = entry;
    /* Call `qsort()', read about the `sizeof' operator */
    qsort(list, count, sizeof(*list), compareSize);
    /* Print the sorted entries now */
    for (int index = 0 ; index < count ; ++index){
    	
        if(lstat(list[index]->d_name, &buf) == -1) {
    		perror("lstat");
    		exit(1);
  		}
  		printf("%lu ", buf.st_ino);
  		printf( (S_ISDIR(buf.st_mode)) ? "d" : "-");
		printf( (buf.st_mode & S_IRUSR) ? "r" : "-");
		printf( (buf.st_mode & S_IWUSR) ? "w" : "-");
		printf( (buf.st_mode & S_IXUSR) ? "x" : "-");
		printf( (buf.st_mode & S_IRGRP) ? "r" : "-");
		printf( (buf.st_mode & S_IWGRP) ? "w" : "-");
		printf( (buf.st_mode & S_IXGRP) ? "x" : "-");
		printf( (buf.st_mode & S_IROTH) ? "r" : "-");
		printf( (buf.st_mode & S_IWOTH) ? "w" : "-");
		printf( (buf.st_mode & S_IXOTH) ? "x" : "-");
		
		printf("\n");
  	}
    closedir(dir);
}

int main(int argc, char *argv[])
{

  if(argc == 1) {
    printSortedNames(".");
  } else {
    printSortedNames(argv[1]);
  }
  return 0;
}
