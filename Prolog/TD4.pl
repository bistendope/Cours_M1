/* Prédicats spéciaux */

/* Exercice 2 */

:- dynamic(sol/1).

appliquer(_, [], []).

appliquer(_, [X], [X]).

appliquer(P, [X, Y | L], [Z | R]) :-
	Pred =.. [P, X, Y, Z],
	call(Pred),
	appliquer(P, L, R).
	
plus(X, Y, Z) :-
	Z is X + Y.
	
mult(X, Y, Z) :-
	Z is X * Y.
	
+(X, Y, Z) :-
	Z is X + Y.
	
/* Exercice 3 */

p(1,3,5).
p(2,4,1).
p(3,5,2).
p(4,3,1).
p(5,2,4).

mon_findall(X, Pred, L) :-
	executerPred(X, Pred),
	recupererSol(L).
	
executerPred(X, Pred) :-
	call(Pred),
	asserta(sol(X)),
	fail.
	
executerPred(_, _).
	
recupererSol([V | L]) :-
	sol(V),
	retract(sol(V)),
	!,
	recupererSol(L).

recupererSol([]).

