#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include "toolbox.h"

typedef struct tool {
	char *name;
	char *usage;
	int (*main)(int,char *[]);
} tool;

tool tools[]= {
	{ "echo", "write arguments to the standard output", main_echo },
	{ "cp", "copy a file", main_cp },
	{ "link", "create a symbolic link to a file or directory", main_link },
	{ "mkdir", "create a new directory", main_mkdir },
	{ "mv", "move a file to the targeted location", main_mv },
	{ "rmdir", "erase directory", main_rmdir },
	{ NULL, NULL, NULL }
};

int main(int argc, char *argv[])
{
	int i=0;
	char *name;
	
	name = basename(argv[0]);
	while ((tools[i].name != NULL) && strcmp(name, tools[i].name))
		i++;
	if (tools[i].name != NULL)
		return tools[i].main(argc, argv);
	else {
		fprintf(stderr, "usage: tool arg1 ... argN\nwhere tool is a valid tool name among:\n");
		for (i=0; tools[i].name != NULL; i++)
			fprintf(stderr, "    %s -- %s\n", tools[i].name, tools[i].usage);
		return EXIT_FAILURE;
	}
}
