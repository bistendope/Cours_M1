
%------------------------------------------------------------------------
% Modelisation de problème : pour chaque problème, il faut définir
% * etat_initial(-Etat) : initialiser à Etat l'état initial 
% * etat_final(+Etat) : succès si Etat correspond à un état final
% * h(+Etat,-H) : calculer la valeur de l'heuristique sur Etat
% * action(+Etat,-EtatSuivant,-NomAction,-CoutAction) : les actions possibles
%------------------------------------------------------------------------

% PROBLÈME DES FLÈCHES

etat_initial([1,1,1,0,1,0]).

etat_final([0,0,0,0,0,0]).

%------------------------------------------------------------------------
% action(+Etat,-EtatSuivant,-NomAction,-CoutAction)
% NomAction ici peut être un terme tourner(X), où X est la position
% de la première flèche tournée
% Exemple :
% Etat = [1,0,1,0,1,1], NomAction=tourner(3),
% EtatSuivant = [1,0,0,1,1,1], CoutAction = 1
%------------------------------------------------------------------------


%action(etat_final, _, _, CoutAction).
%action([X, Y|L], [X1, Y1|L], tourner(1), 1)):-
%	X1 is (X+1)rem 2, 
%	Y1 is (Y + 1)rem 2.
	
%action([X|L], [X|L], tourner(Taille), 1):- 
%	Taille>1, 
%	Taille1 is Taille -1, 
%	action(L, L, Taille1, 1).


% correction
action(State, NewState, tourner(X), 1):-
	member(X,[1,2,3,4,5]), % take X for a value from 1 to 5
	new_state(State, NewState, X).
	
new_state([0,0|L], [1,1|L], 1):-!.
new_state([0,1|L], [1,0|L], 1):-!.
new_state([1,0|L], [0,1|L], 1):-!.
new_state([1,1|L], [0,0|L], 1):-!.
new_state([T|L], [T|NewL], X):-
	X1 is X-1,
	new_state(L, NewL, X1).
	

%------------------------------------------------------------------------
% h(+Etat,-H) : calculer la valeur H de l'heuristique sur Etat
% Exemple : H = nombre de flèches vers le bas // 2
%------------------------------------------------------------------------

h(E, H):-
	somme(E,S),
	H is S//2.

somme([], 0).
somme([X|L], Somme):-Somme1 is Somme + X, somme(L, Somme1).

%-------------------------------------------------------------------------
% ESPACE DE RECHERCHE 
% chaque noeud dans l'espace de recherche est représenté par une structure
% node(Etat, Action_menant_a_Etat, Cout_reel, F_value)
% * Etat : etat courant correspondant au noeud
% * Action : l'action permettant d'arriver de l'état précédent à l'état courant
% * Cout_reel : le coût réel cumulé depuis l'état initial jusqu'à l'état courant
% * F_value : la valeur de Cout_reel+H, où H est le coût estimé de l'état
%           courant jusqu'à l'état final
%-------------------------------------------------------------------------

%------------------------------------------------------------------------
% Des prédicats utiles
%------------------------------------------------------------------------

noeud_initial(node(Etat, noOp, 0, H)) :-
    etat_initial(Etat),
    h(Etat,H).

noeud_final(node(Etat,_,_,_)) :-
	etat_final(Etat).

cout_du_noeud(node(_,_,Cout,_),Cout).




%-------------------------------------------------------------------------
% goAstar : lancer la recherche A*
%-------------------------------------------------------------------------


goAstar :-
    noeud_initial(Node),
    astar([[Node]], Path, [1,1], [NbGeneres,NbVisites]),
    Path = [Last|_],
    reverse(Path,Solution),
    cout_du_noeud(Last,Cost),
    write('Solution trouvee :'), write(Solution), nl,
    write('Cout total : '), write(Cost), nl,
    write('Nombre de noeuds generes : '), write(NbGeneres), nl,
    write('Nombre de noeuds visites : '), write(NbVisites), nl.

%-------------------------------------------------------------------------
% Moteur de recherche A*
% astar(+LChemins, -Chemin_trouvé, +StatIn, -StatOut)
% * LChemins : liste de chemins, chaque chemin est une liste de noeuds,
%   dont le premier element est un noeud courant
%   le chemin ici est dans l'ordre inverse du chemin du noeud initial au
%   noeud courant, le noeud courant est donc à la tête du chemin
% * Chemin_trouvé : liste du noeud final au noeud initial 
% * StatIn : statistique en entree 
% * StatOut : statistique en sortie 
%   StatIn et StatOut sont des listes de deux éléments
%      [Nombre_de_noeuds_générés, Nombre_de_noeuds_visités]
%------------------------------------------------------------------------

astar([[Node|Path]|_], [Node|Path], Stat, Stat):-
	noeud_final(Node), !.
	
astar([[Node|Path]|Open], Solution, StatIn, StatOut):-
	tous_voisins([Node|Path], NewPaths), % on genere la liste des chemins à prendre à l'aide f'un find_all(action())
	inserer_tous(NewPaths, Open, NewOpen), % on insere ces chemins dans l'ordre c + h dans la liste de chemins NewOpen
	length(NewPaths, NumberNewPaths), %On calcule le nombre de nouveaux chemins
	StatIn = [NbGen1, NbVis1],
	NbGen2 is NbGen1 + NumberNewPaths, % on met à jour les stats
	NbVis2 is NbVis1 + 1,
	astar(NewOpen, Solution, [NbGen2, NbVis2], StatOut).% et on repart

tous_voisins([Node|Path], NewNodes):-
	findall([N,Node|Path], un_voisin(N,Node), NewNodes).
un_voisin(N, Node):-
	noeud_suivant(Node, N).

noeud_suivant(node(State, _, Cost, _), node(NewState, Action, NewCost, Fvalue)):-
	action(State, NewState, Action, ActionCost),
	NewCost is Cost + ActionCost,
	h(NewState, Hvalue),
	Fvalue is NewCost + Hvalue. % pour le B* (Best First Search), on peut remplacer cette ligne par "Fvalue is Hvalue"
	
inserer_tous([], Open, Open).
inserer_tous([Path|OtherPaths], Open, NewOpen):-
	inserer_tous(OtherPaths, Open, Aux),
	inserer_un(Path, Aux, NewOpen).

inserer_un(Path, [], [Path]).
inserer_un(Path, [Path1|L], [Path, Path1|L]):-
	Path = [N |_],
	Path1 = [N1 | _],
	N = node(_,_,_, F),
	N1 = node(_,_,_,F1),
	F<F1, !.

inserer_un(Path, [T|List], [T|NewList]):-
	inserer_un(Path, List, NewList).


	


