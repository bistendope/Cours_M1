%------------------------------------------------------------------------
% Algorithmes de recherche non informee
% chaque noeud est de la forme node(Etat,Action)
% Etat : etat du probleme
% Action : action menant a Etat a partir de l etat precedent
%------------------------------------------------------------------------

%------------------------------------------------------------------------
% Recherche en profondeur
% on utilise le principe de recherche en profondeur de Prolog pour 
% memoriser le chemin
%-----------------------------------------------------------------------

goP :- 
    noeud_initial(Node),
    rp(Node,[],Chemin),
    reverse(Chemin,Solution),
    afficher(Solution),
    length(Chemin,N),
    nl,write(N).

afficher([]).
afficher([node(E,Action)|L]) :-
    write(Action), write(' ===> '), write(E), nl,
    afficher(L).
	
rp(N,D,[N|D]) :-
    noeud_final(N), !.
rp(node(Etat,A),DejaVus,Chemin) :-
    action(Etat,EtatSuivant,Action),
    \+ dejaVisite(EtatSuivant,DejaVus),
    rp(node(EtatSuivant,Action),[node(Etat,A)|DejaVus],Chemin).

dejaVisite(Etat,[node(Etat,_)|_]) :- !.
dejaVisite(Etat,[_|L]) :-
    dejaVisite(Etat,L).

noeud_initial(node(E,debut)) :- etat_initial(E).
noeud_final(node(E,_)) :- etat_final(E). 


%% %------------------------------------------------------------------------
%% % EXERCICE  : Modelisation du probleme traverse du pont
%% %------------------------------------------------------------------------
%% Un état est représenté par une liste
%%  [Liste_gauche, Liste_droite, Position_torche, Temps_utilise], où
%%  * Liste_gauche et Liste_droite sont des listes de temps des personnes au côté gauche et droite, respectivement, par exemple [2,1]
%%  * Position_torche est gauche ou droite
%%  * Temps_utilise est le temps utilisé depuis le début
%%
%% 1. Ecrire les prédicats etat_initial et etat_final
%% 2. Ecrire le prédicat supprimer(X,L1,L2) qui calcule la liste L2 en supprimant l'élément X de la liste L1
%% 3. Ecrire le prédicat action(Etat, Etat_suivant, Nom_action)

etat_initial([[10,5,2,1],[],0,0]).

etat_final([[],_,_,Temps]):-Temps < 18. % on peut spécifier ici le temps que l'on veut

supprimer(X,[X|L],L) :- !.
supprimer(X,[Y|L1], [Y|L2]) :-
    supprimer(X, L1, L2).
	
action([Lg, Ld, gauche, Temps], [LSg, [X|Ld], droite, TempsS], allerADroite(X)):-
	member(X, Lg),
	TempsS is Temps + X, TempsS =< 17,
	supprimer(X, Lg, LSg).
	
action([Lg, Ld, gauche, Temps], [LSg, [X,Y|Ld], droite, TempsS], allerADroite(X, Y)):-
	member(X, Lg),
	supprimer(X, Lg, L1),
	member(Y, L1),
	X>Y,
	TempsS is Temps + X, TempsS =< 17,
	supprimer(Y, L1, LSg).
	
action([Lg, Ld, droite, Temps], [[X|Lg], LSd, gauche, TempsS], allerAGauche(X)):-
	member(X, Ld),
	TempsS is Temps + X, TempsS =< 17,
	supprimer(X, Ld, LSd).
	
action([Lg, Ld, droite, Temps], [[X,Y|Lg], LSd, gauche, TempsS], allerAGauche(X, Y)):-
	member(X, Ld),
	supprimer(X, Ld, L1),
	member(Y, L1),
	X>Y,
	TempsS is Temps + X, TempsS =< 17,
	supprimer(Y, L1, LSd).
		




