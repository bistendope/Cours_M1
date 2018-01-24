question1 :-
	fd_domain([Jean, Jeanne], 0, 100),
	Jean #= Jeanne + 10,
	Jean + 5 #= 2 * (Jeanne + 5),
	write(Jean),
	nl,
	write(Jeanne).

question2 :-
	fd_domain([Parent, Jumeaux, Enfant], 0, 100),
	Parent #= Jumeaux * 3,
	Parent * Parent #= Jumeaux * Jumeaux * Enfant,
	2 * (Parent - 1) #= 2 * (2 * Jumeaux + Enfant + 3),
	fd_labeling([Parent, Jumeaux, Enfant]),
	write(Parent),
	nl,
	write(Jumeaux),
	nl,
	write(Enfant).

question3 :-
	fd_domain([S, E, N, D, M, O, R, Y], 0, 9),
	(S * 1000 + E * 100 + N * 10 + D) + (M * 1000 + O * 100 + R * 10 + E) #= (M * 10000 + O * 1000 + N * 100 + E * 10 + Y),
	fd_all_different([S, E, N, D, M, O, R, Y]),
	S #\= 0,
	M #\= 0,
	fd_labeling([S, E, N, D, M, O, R, Y]),
	write('S = '), write(S), nl,
	write('E = '), write(E), nl,
	write('N = '), write(N), nl,
	write('D = '), write(D), nl,
	write('M = '), write(M), nl,
	write('O = '), write(O), nl,
	write('R = '), write(R), nl,
	write('Y = '), write(Y).

question4 :-
	fd_domain([A, B, C, D, E, F, G, H, I], 1, 9),
	fd_all_different([A, B, C, D, E, F, G, H, I]),
	A #> D,
	D #> G,
	A * (E * 10 + F) * (H * 10 + I) + D * (B * 10 + C) * (H * 10 + I) + G * (B * 10 + C) * (E * 10 + F) #= (B * 10 + C) * (E * 10 + F) * (H * 10 + I),
	fd_labeling([A, B, C, D, E, F, G, H, I]),
	write('A = '), write(A), nl,
	write('B = '), write(B), nl,
	write('C = '), write(C), nl,
	write('D = '), write(D), nl,
	write('E = '), write(E), nl,
	write('F = '), write(F), nl,
	write('G = '), write(G), nl,
	write('H = '), write(H), nl,
	write('I = '), write(I).

question5 :-
	fd_domain([Y1, Y2, Y3, Y4], 1, 4),
	fd_all_different([Y1, Y2, Y3, Y4]),
	Y1 - Y2 #\= 1,
	Y1 - Y3 #\= 2,
	Y1 - Y4 #\= 3,
	Y2 - Y3 #\= 1,
	Y2 - Y4 #\= 2,
	Y3 - Y4 #\= 1,
	Y2 - Y1 #\= 1,
	Y3 - Y1 #\= 2,
	Y4 - Y1 #\= 3,
	Y3 - Y2 #\= 1,
	Y4 - Y2 #\= 2,
	Y4 - Y3 #\= 1,
	fd_labeling([Y1, Y2, Y3, Y4]),
	write('Y1 = '), write(Y1), nl,
	write('Y2 = '), write(Y2), nl,
	write('Y3 = '), write(Y3), nl,
	write('Y4 = '), write(Y4).

question6 :-
	fd_domain([A, B, C], 0, 180),
	A #> B,
	B #> C,
	A + B + C #= 180,
	360 rem A #= 0,
	360 rem B #= 0,
	360 rem C #= 0,
	fd_labeling([A, B, C]),
	write('A = '), write(A), nl,
	write('B = '), write(B), nl,
	write('C = '), write(C).

question21 :-
	fd_domain([Rob, Leon, Mark, Queen, King, Prince, Classique, Pop, Jazz], 24, 26),
	fd_all_different([Rob, Leon, Mark]),
	fd_all_different([Queen, King, Prince]),
	fd_all_different([Classique, Pop, Jazz]),
	Rob #> Queen,
	Queen #= Classique,
	Pop #\= Prince,
	Pop #\= 24,
	Leon #\= King,
	Leon #= 25,
	Mark #\= Jazz,
	fd_labeling([Rob, Leon, Mark, Queen, King, Prince, Classique, Pop, Jazz]),
	write('Rob = '), write(Rob), nl,
	write('Leon = '), write(Leon), nl,
	write('Mark = '), write(Mark), nl,
	write('Queen = '), write(Queen), nl,
	write('King = '), write(King), nl,
	write('Prince = '), write(Prince), nl,
	write('Classique = '), write(Classique), nl,
	write('Pop = '), write(Pop), nl,
	write('Jazz = '), write(Jazz).

question22 :-
	fd_domain([Renault, Ferrari, Peugeot, Volvo, Noire, Rouge, Blanche, Jaune], 1, 4),
	fd_all_different([Renault, Ferrari, Peugeot, Volvo]),
	fd_all_different([Noire, Rouge, Blanche, Jaune]),
	Renault #= Noire,
	Ferrari #= Rouge,
	Blanche #\= 1,
	Blanche #\= 4,
	Renault - Peugeot #\= 1,
	Peugeot - Renault #\= 1,
	Ferrari #< Volvo,
	((Jaune #< Rouge) #/\ (Jaune #< Noire)) #\/ ((Jaune #> Rouge) #/\ (Jaune #> Noire)),
	(Ferrari - Renault #= 1) #\/ (Renault - Ferrari #= 1),
	fd_labeling([Renault, Ferrari, Peugeot, Volvo, Noire, Rouge, Blanche, Jaune]),
	write('Renault = '), write(Renault), nl,
	write('Ferrari = '), write(Ferrari), nl,
	write('Peugeot = '), write(Peugeot), nl,
	write('Volvo = '), write(Volvo), nl,
	write('Noire = '), write(Noire), nl,
	write('Rouge = '), write(Rouge), nl,
	write('Blanche = '), write(Blanche), nl,
	write('Jaune = '), write(Jaune).

question23 :-
	fd_domain([Habitant], 1, 4),
	((Habitant #= 1) ## (Habitant #= 3)) #<=> ((Habitant #= 2) #/\ (Habitant #\= 4)),
	((Habitant #= 2) ## (Habitant #= 4)) #<=> ((Habitant #\= 2) #/\ (Habitant #= 4)),
	fd_labeling([Habitant]),
	write('Habitant = '), write(Habitant).

question25 :-
	L = [X0, X1, X2, X3],
	fd_domain(L, 0, 4),
	fd_domain([B00, B01, B02, B03, B10, B11, B12, B13, B20, B21, B22, B23, B30, B31, B32, B33], 0, 1),
	B00 #= 1 #<=> X0 #= 0,
	B01 #= 1 #<=> X0 #= 1,
	B02 #= 1 #<=> X0 #= 2,
	B03 #= 1 #<=> X0 #= 3,
	B10 #= 1 #<=> X1 #= 0,
	B11 #= 1 #<=> X1 #= 1,
	B12 #= 1 #<=> X1 #= 2,
	B13 #= 1 #<=> X1 #= 3,
	B20 #= 1 #<=> X2 #= 0,
	B21 #= 1 #<=> X2 #= 1,
	B22 #= 1 #<=> X2 #= 2,
	B23 #= 1 #<=> X2 #= 3,
	B30 #= 1 #<=> X3 #= 0,
	B31 #= 1 #<=> X3 #= 1,
	B32 #= 1 #<=> X3 #= 2,
	B33 #= 1 #<=> X3 #= 3,
	X0 #= B00 + B10 + B20 + B30,
	X1 #= B01 + B11 + B21 + B31,
	X2 #= B02 + B12 + B22 + B32,
	X3 #= B03 + B13 + B23 + B33,
	fd_labeling(L),
	write(L).

magic(N, L) :-
	length(L, N),
	fd_domain(L, 0, N),
	contraintes(L, L, 0),
	fd_labeling(L).

contraintes([], _, _).

contraintes([X | Xs], L, I) :-
	somme(L, I, X),
	I1 is I + 1,
	contraintes(Xs, L, I1).

somme([], _, 0).

somme([X | Xs], I, S) :-
	somme(Xs, I, S1),
	X #= I #<=> B,
	S #= B + S1.

magic_r(N, L) :-
	length(L, N),
	fd_domain(L, 0, N),
	contraintes_r(L, L, 0, N, N),
	fd_labeling(L).

contraintes_r([], _, _, 0, 0).

contraintes_r([X | Xs], L, I, S1, S2) :-
	somme(L, I, X),
	I1 is I + 1,
	contraintes_r(Xs, L, I1, S3, S4),
	S3 + X #= S1,
	I * X + S4 #= S2.
	
ou_exc(A,B,C):-
	fd_domain([A,B,C],0,1),
	(A+B+C) rem 2 #=1,
	fd_labeling([A,B,C]).
	
auPlus1(A,B,C):-
	fd_domain([A,B,C],0,1),
	(A+B+C) #=<1,
	fd_labeling([A,B,C]).
	
question33(L):-
	L = [Or,Argent,Plomb, Prop1, Prop2, Prop3],
	fd_domain(L,0,1),
	
	Prop1 #<=> Or,
	Prop2 #<=> #\Argent,
	Prop3 #<=> #\Or,
	/*ou_exc(Or, Argent, Plomb), ne marche pas*/
	/*auPlus1(Prop1, Prop2, Prop3),*/
	(Or+Argent+Plomb) #=1,
	Prop1 + Prop2 + Prop3 #<2,
	fd_labeling([Prop1, Prop2, Prop3]).
	
question34([A,B,C,D]):-
	fd_domain([A,B,C,D],0,1),
	fd_domain([Ra, Rb, Rc, Aa, Ab, Ac],0,1),
	fd_domain([Rment,Ament],0,1),
	Ra #<=> A + B + C #= 1,
	Rb #<=> B + C + D #= 1,
	Rc #<=> A + D #= 1,
	Aa #<=> A + B + C #= 1,
	Ab #<=> B + C + D #= 1,
	Ac #<=> A + C + D #= 1,
	Rment #<=> Ra + Rb + Rc #<3,
	Ament #<=> Aa + Ab + Ac #<3,
	Rment + Ament #= 1,
	%Ra + Rb + Rc#<3 ## Aa + Ab + Ac #< 3,
	fd_labeling([A,B,C,D]).
	
reines(N, L):-
	length(L,N),
	fd_domain(L,1,N),
	% pas d'attaques en ligne
	fd_all_different(L),
	%pas d'attaques en diagonale
	contrainte_diag(L),
	fd_labeling(L).

contrainte_diag([]).
contrainte_diag([X|Q]):-
	poser_contraintes(X, Q, 1),
	contrainte_diag(Q).

poser_contraintes(_, [], _).
poser_contraintes(X, [Y|Q], N):-
	X-Y #\=N, Y-X #\=N,
	N1 is N + 1,
	poser_contraintes(X, Q, N1).
	
	
	
