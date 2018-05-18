:- op(50,xfx,'==>'). % pas associativite
:- op(30,yfx, et). % associativite gauche
:- op(40,yfx, ou). % associativite gauche
:- op(10,fy, non). % avec associativite

:- dynamic(regle/1).
:- dynamic(fait/1).

% -----------------------------------------------------------------
% Chainage avant et arriere sur une base de connaissances attribut-valeur
% Base : liste de regles et de faits
% regle de forme a1 et/ou a2 et .. et an ==> b
% fait de forme a
% But : fait a prouver
% les faits a sont de la forme egal(Attribut,Valeur)
% -----------------------------------------------------------------

% Quelques règles de départ
liste_regles([
    egal(panneau,ecole) ==> egal(prox_etab,vrai),
    sup_eg(poids,3.5) ==> egal(type_veh,lourd),
    inf(poids,3.5) ==> egal(type_veh,leger),
    egal(panneau_vit,30) ==> egal(limit_vit,vrai),
    egal(meteo,pluie) ==> egal(mauv_visib,vrai),
    egal(meteo,neige) ==> egal(chaussee_glissante,vrai),
    
    egal(mauv_visib,vrai) ou 
    egal(chaussee_glissante,vrai)                      ==> egal(danger,vrai),

    egal(lieu,en_ville) et egal(type_veh,leger) et 
    non egal(prox_etab,vrai) et non egal(danger,vrai) 
    et non egal(limit_vit,vrai)                        ==> egal(vitesse,50),

    egal(lieu,en_ville) et egal(type_veh,leger) et
	non egal(prox_etab,vrai) et egal(danger,vrai)
	et non egal(limit_vit,vrai)                    ==> egal(vitesse,40)
]).

% Quelques faits de départ
liste_faits([
    egal(lieu,en_ville),
    egal(poids,3),
    egal(meteo,neige)
	      ]).

% Quelques prédicats utiles

initier_base([]).
initier_base([ P ==> C | L]) :-
    !,
    asserta(regle(P ==> C)),
    initier_base(L).
initier_base([F|L]) :-
    asserta(fait(F)),
    initier_base(L).

nettoyage :- 
    retract(regle(_)), fail.
nettoyage :-
    retract(fait(_)), fail.
nettoyage.

ajouter_fait(egal(A,V)) :-
     asserta(fait(egal(A,V))).

% EXERCICE 1
% 1. Charger ce fichier en gprolog
% 2. La base de fait doit être constituée des faits Prolog par exemple
%    fait(egal(lieu,en_ville)).
%   Quelle est la requête pour créer la base de faits à partir de liste_faits ?
%    asserta(fait(F)).
% 3. La base de règles doit être constituée des faits Prolog par exemple
%    regle(egal(meteo,neige)==>egal(chaussee_glissante,vrai)).
%   Quelle est la requête pour créer la base de règles à partir de liste_regles ?
%	asserta(regle(P ==> C))
% 4. Quelle est la requête pour effacer la base de faits et la base de règles ?
% retract


% -----------------------------------------------------------------
% Chainage avant FC Forward Chaining
% -----------------------------------------------------------------

go_fc(Attribut) :-
    nettoyage,
    liste_regles(BR),
    liste_faits(BF),
    initier_base(BR),
    initier_base(BF),
    ch_avant_saturation,
    fait(egal(Attribut,Valeur)),
    write(Attribut), write(' : '), write(Valeur), nl.

nouveau_fait_derive(C) :-
    regle(P ==> C),
    \+ existe_comme_fait(C),
    satisfait(P).

%% EXERCICE 2
%% 1. Ecrire le prédicat ch_avant_saturation qui est appelé dans go_fc. Ce prédicat utilisera le prédicat nouveau_fait_derive(C) proposé.
%% Une règle est de la forme P==>C. 
%% 2. Ecrire le prédicat existe_comme_fait(C).
%% 3. Ecrire le prédicat satisfait(P) qui réussit si une prémisse P est satisfaite par la base de faits. Consulter la base de règles pour les formes possibles dune prémisse.


ch_avant_saturation:- 
	nouveau_fait_derive(F), !, 
	write("Nouveau fait: "), write(F), nl, 
	ajouter_fait(F),
	ch_avant_saturation.
	
ch_avant_saturation:- write("Plus de nouveaux faits.").

existe_comme_fait(C).
	fait(C).
	
satisfait(P1 et P2):-
 satisfait(P1),
 satisfait(P2).
 
satisfait(P1 ou _):- satisfait(P1), !.
satisfait(_ ou P2):- satisfait(P2).

satisfait(non P1):-
	\+ satisfait(P1).
	
% satisfait(P1 ==> P2):-
%	satisfait (satisfait(non P1) ou satisfait(P2)).
 
satisfait(sup_eg(A, V)):-
	fait(egal(A,V1)),
	V1 >= V.
	
satisfait(inf(A,V)):-
	fait(egal(A,V1)),
	V1 < V.
 

% -----------------------------------------------------------------
% Chainage arriere BC Backward Chaining
% -----------------------------------------------------------------

go_bc(Attribut,Valeur) :-
    nettoyage,
    liste_regles(BR),
    liste_faits(BF),
    initier_base(BR),
    initier_base(BF),
    ch_arriere(egal(Attribut,Valeur)).

%% EXERCICE 3
%% Ecrire le prédicat ch_arriere(But).

ch_arriere(F):-
	fait(F), !.

ch_arriere(C):-
	regle(P ==> C),
	ch_arriere_cond(P).

ch_arriere_cond(P1 et P2):-
	ch_arriere_cond(P1),
	ch_arriere_cond(P2).

ch_arriere_cond(P1 ou _):-
	ch_arriere_cond(P1), !.
	
ch_arriere_cond(_ ou P2):-
	ch_arriere_cond(P2).

ch_arriere_cond(non P):-
	\+ ch_arriere_cond(P).
	
ch_arriere_cond(F):-
	fait(F), !.
	
ch_arriere_cond(sup_eg(A, V)):-
	fait(egal(A, V1)), V1 >= V, !.

ch_arriere(inf(A, V)):-
	fait(egal(A, V1)), V1 < V, !.

ch_arriere_cond(F):-
	ch_arriere(F).


%% EXERCICE 4
%% Compléter la base de règles avec des cas de lexercice 2 en TD

