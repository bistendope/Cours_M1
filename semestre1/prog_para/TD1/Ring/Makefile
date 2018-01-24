MPICXX = mpic++
SRC= $(wildcard *.cpp)
OBJ= $(SRC:.cpp=.o)
EXEC= main

%.o: %.cpp
	$(MPICXX) -o $@ -c $< 

main: $(OBJ) 
	$(MPICXX) -o $@ $^

run: main
	mpirun -np 8 ./main 0

clean:
	@rm -rf *.o

cleanall: clean
	@rm -rf $(EXEC)

