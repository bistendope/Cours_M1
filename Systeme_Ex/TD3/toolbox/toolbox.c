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
	{ "rm", "erase file", main_rm },
	{ "true", "return true", main_true },
	{ "false", "return false", main_false },
	{ "cat", "print content of files in parameter", main_cat },
	{ "ls", "print current directory", main_ls },
	{ "time", "show the elapsed time by a function", main_time },
	{ "sh", "launch a shell", main_myshell },
	{ NULL, NULL, NULL }
};

int main(int argc, char *argv[])
{
	int i=0;
	char *name;
	
	name = basename(argv[1]);
	while ((tools[i].name != NULL) && strcmp(name, tools[i].name)){
		i++;
	}
	if (tools[i].name != NULL)
		return tools[i].main(argc-1, argv+1);
	else {
		fprintf(stderr, "usage: tool arg1 ... argN\nwhere tool is a valid tool name among:\n");
		for (i=0; tools[i].name != NULL; i++)
			fprintf(stderr, "    %s -- %s\n", tools[i].name, tools[i].usage);
		return EXIT_FAILURE;
	}
}
