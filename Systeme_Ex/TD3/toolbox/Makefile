OBJS=echo.o toolbox.o cp.o link.o mkdir.o mv.o rmdir.o
BINS=bin/echo bin/toolbox bin/cp bin/link bin/mkdir bin/mv bin/rmdir
MANS=man/echo.t2t

CC=gcc
CFLAGS=-Wall -Werror -ansi -g

all: $(BINS) $(MANS) $(TXT)

toolbox: $(OBJS)
	$(CC) -o $@ $(OBJS)

clean:
	-\rm -rf $(OBJS) toolbox bin/* man/man1/*

man/man1/%.1: %.t2t
	./txt2tags -i $? -o $@ -t man

bin/echo: toolbox
	-rm $@
	ln $? $@

toolbox.o: toolbox.h
echo.o: toolbox.h
