OBJS=echo.o toolbox.o cp.o link.o mkdir.o mv.o rmdir.o myshell.o rm.o true.o false.o cat.o ls.o time.o
BINS=bin/echo bin/toolbox bin/cp bin/link bin/mkdir bin/mv bin/rmdir bin/myshell bin/rm bin/true bin/false bin/cat bin/ls bin/time
MANS=man/man1/cat.1 man/man1/cp.1 man/man1/echo.1 man/man1/false.1 man/man1/link.1 man/man1/ln.1 man/man1/ls.1 man/man1/mkdir.1 man/man1/mv.1 man/man1/rm.1 man/man1/rmdir.1 man/man1/time.1 man/man1/true.1

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

bin/cp: toolbox
	-rm $@
	ln $? $@

bin/toolbox: toolbox
	-rm $@
	ln $? $@

bin/rmdir: toolbox
	-rm $@
	ln $? $@

bin/mkdir: toolbox
	-rm $@
	ln $? $@

bin/mv: toolbox
	-rm $@
	ln $? $@

bin/link: toolbox
	-rm $@
	ln $? $@

bin/myshell: toolbox
	-rm $@
	ln $? $@

bin/rm: toolbox
	-rm $@
	ln $? $@

bin/true: toolbox
	-rm $@
	ln $? $@

bin/false: toolbox
	-rm $@
	ln $? $@

bin/cat: toolbox
	-rm $@
	ln $? $@

bin/ls: toolbox
	-rm $@
	ln $? $@

bin/time: toolbox
	-rm $@
	ln $? $@

toolbox.o: toolbox.h
echo.o: toolbox.h
cp.o: toolbox.h
link.o: toolbox.h
mkdir.o: toolbox.h
mv.o: toolbox.h
rmdir.o: toolbox.h
myshell.o: toolbox.h
rm.o: toolbox.h
true.o: toolbox.h
false.o: toolbox.h
cat.o: toolbox.h
ls.o: toolbox.h
time.o: toolbox.h