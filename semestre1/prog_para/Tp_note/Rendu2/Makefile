MPICXX = mpic++
SRC= $(wildcard *.cpp)
OBJ= $(SRC:.cpp=.o)
EXEC= main

%.o :%.cpp
	$(MPICXX) -o $@ -c $< 

all:  main_chaleur

main_chaleur : main_chaleur.o fonctions.o 
	$(MPICXX) -o $@ $^

.PHONY: clean mrproper

clean:
	@rm -rf *.o

mrproper: clean
	@rm -rf $(EXEC)
