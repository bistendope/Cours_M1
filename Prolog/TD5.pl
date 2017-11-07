analyse([qui,est|L]):-
	etat3(L).
etat3([le|L]):-etat4(L).
etat3([l|L]):-etat5(L).
etat3([la|L]):-etat10(L).

etat4([X|L]):-member(X, [frere, neveu, pere, parent, grand_pere, grand_parent, cousin, parent]),
	etat6(L).


etat5([X|L]):-member(X, [ancetre, oncle, enfant]),
	etat6(L).

etat6([d|L]):-etat7(L).
etat6([de|L]):-etat8(L).
etat6([du|L]):-etat4(L).

etat7([X|L]):-homme(X), etat9(L).
etat7([X|L]):-femme(X), etat9(L).

etat8([l|L]):-etat5(L).
etat8([la|L]):-etat10(L).
etat8([X|L]):-homme(X), etat9(L).
etat8([X|L]):-femme(X), etat9(L).

etat9([]).

etat10([mere|L]):-member(X, [mere, fille, soeur, grand_mere, petite_fille, tante, cousine]),
	etat6(L).


