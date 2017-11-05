OBJS=bio.o bcp.o 

CC=gcc
CFLAGS=-Wall -Werror

all: bcp
	
bcp: $(OBJS)
	$(CC) -o $@ $(OBJS)
	


clean:
	-\rm -rf $(OBJS)
	
bcp.o: bio.h
bio.o: bio.h

