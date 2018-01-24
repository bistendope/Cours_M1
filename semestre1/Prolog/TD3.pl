/*dernier1(L,X) est vrai si X est le dernier  ́element de L.*/
dernier1([X],X).
dernier1([_|Q],X):- dernier1(Q,X).

/*sup_dernier(L,L2) est vrai si L2 est la liste L tronquée d'un élément.*/
sup_dernier([_],[]).
sup_dernier([T|Q1],[T|Q2]):- sup_dernier(Q1,Q2).

/*element (X, L) est vrai si X est element de L*/
element(X, [X|_]).
element(X, [_|Q]):- element(X, Q).

/*compte(L,N) est vrai si N est le nombre d'éléments de L*/
compte([], 0).
compte([_|Q], N):-compte(Q, N1), N is N1 + 1.

/*somme(L,N) est vrai si N est la somme des ́elements de la liste d’entiers L.*/
somme([],0).
somme([T|Q], N):-somme(Q, N1), N is N1 + T.

/*nieme(N,L,X) est vrai si X est le N-ieme ́element de la liste L.*/
nieme(1,[X|_],X).
nieme(N,[_|Q],X):-nieme(N1,Q,X), N is N1 + 1. 

/*occurrence(L,X,N) est vrai si N est le nombre de fois où X est present dans la liste L.*/
occurrence([],_,0).
occurrence([X|Q], X, N):- occurrence(Q, X, N1), N is N1+1.
occurrence([T|Q], X,N):- T == X,occurrence(Q,X,N).

/*sous-ensemble(L1,L2) est vrai si tous les ́eléments de la liste L1 font partie de la liste L2.*/
sous_ensemble([],_).
sous_ensemble([T|L1], L2):-element(T,L2), sous_ensemble(L1, L2).

/*substitue(X,Y,L1,L2) est vrai si L2 est le resultat du remplacement de X par Y dans L1.*/
substitue(_,_,[],[]).
substitue(X,Y,[X|L1],[Y|L2]):-substitue(X,Y,L1,L2).
substitue(X,Y,[T|L1],[T|L2]):-X\==T,substitue(X,Y,L1,L2).

/*retourne(L,L1) est vrai si la liste L1 est la liste L dans l’ordre inverse.*/
retourne([],[]).
retourne([T|Q], L1):-dernier1(L1, T),sup_dernier(L1,L2), retourne(Q,L2).


