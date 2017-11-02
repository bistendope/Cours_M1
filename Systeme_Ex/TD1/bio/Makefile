OBJS=bio.o cat.o 

CC=gcc
CFLAGS=-Wall -Werror

all: cat
	
cat: $(OBJS)
	$(CC) -o $@ $(OBJS)
	


clean:
	-\rm -rf $(OBJS)
	
cat.o: bio.h
bio.o: bio.h

