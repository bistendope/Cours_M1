%deuxieme partie de l'exercice

:- dynamic(borne/1).

borne(30).

goDFBAB :-
    noeud_init(N),
    dfbab(N,[],C),
    reverse(C,S),
    write(S).


noeud_init(node(E,0,H)) :- etat_init(E), heuristic(E,H).
noeud_final(node(E,_,_)) :- etat_final(E).
cout_cumule(node(_,C,_),C).
cout_estime(node(_,_,H),H).

noeud_suivant(node(E,C,_), node(ES,CS,HS)) :-
    action(E,ES,Cout),
    CS is C+Cout,
    heuristic(ES,HS).


%% %------------------------------------------------------------------------
%% % Le probleme traverse du pont mais sans limite de temps
%% % On cherche donc à minimiser le temps utilisé pour traverser le pont
%% %------------------------------------------------------------------------

etat_init([[1,2,5,10],[],gauche]).
etat_final([[],_,_]).
action([G,D,gauche],[GS,DS,droite],X) :-
    member(X,G), supprimer(X,G,G1),
    member(Y,G1), supprimer(Y,G1,GS),
    X>Y,
    DS = [X,Y|D].
    
action([G,D,gauche],[GS,DS,droite],X) :-
    member(X,G), supprimer(X,G,GS),
    DS = [X|D].

action([G,D,droite],[GS,DS,gauche],X) :-
    member(X,D), supprimer(X,D,D1),
    member(Y,D1), supprimer(Y,D1,DS),
    X>Y,
    GS = [X,Y|G].
    
action([G,D,droite],[GS,DS,gauche],X) :-
    member(X,D), supprimer(X,D,DS),
    GS = [X|G].

heuristic([L,_,_],H) :- max(L,H).

max([],0).
max([X],X).
max([X|L],M) :-
    max(L,Y),
    (X>Y -> M=X; M=Y).

supprimer(X,[X|L],L) :- !.
supprimer(X,[Y|L1], [Y|L2]) :-
    supprimer(X, L1, L2).

%% %------------------------------------------------------------------------
%% % EXERCICE  : Ecrire le prédicat dfbab
%% %------------------------------------------------------------------------

dfbab(N, S, [N|S]):-
	noeud_final(N),
	cout_cumule(N,C),
	retract(borne(_)),
	asserta(borne(C)).
	
dfbab(N, D, S):-
	noeud_suivant(N, NS),
	\+ member(NS,D),
	cout_estime(NS, HS),
	cout_cumule(NS, CS),
	borne(B),
	CS + HS < B,
	dfbab(NS, [N|D], S).

