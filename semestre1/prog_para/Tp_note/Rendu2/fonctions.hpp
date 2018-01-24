#ifndef FONCTIONS_HPP
#define FONCTIONS_HPP

#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <sstream>
#include <fstream>
#include <iostream>

using namespace std;

void generation_float(int n, int m, float* jeu);

bool shift_droite(void* send, int count, MPI_Datatype datatype, void* recv,  MPI_Comm Comm);
bool shift_gauche(void* send, int count, MPI_Datatype datatype, void* recv, MPI_Comm Comm);
bool shift_haut(void* send, int count, MPI_Datatype datatype, void* recv,  MPI_Comm Comm);
bool shift_bas(void* send, int count, MPI_Datatype datatype, void* recv, MPI_Comm Comm);

void distribution(float* tab, int n, int m, int* nbprocs_dims, float* tab_local, int root, MPI_Comm Comm);
void rassemblement(float* tab, int n, int m, int* nbprocs_dims, float* tab_local, int root, MPI_Comm Comm);

void echange_ghost(float* grille, int n, int m, MPI_Comm Comm);

void chaleur(float* grille, int n, int m, float lambda);

void lecture(float* & grille, int & n, int & m, const char* nom_file);
void ecriture(float* grille, int n, int m, const char* nom_file);

#endif
