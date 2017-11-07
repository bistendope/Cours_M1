/* ========= */
/* les faits */
/* ========= */

/* les hommes */
homme(andre).
homme(bernard).
homme(babar).
homme(clement).
homme(dudulle).
homme(damien).
homme(baptiste).
homme(cedric).
homme(didier).
homme(dagobert).

/* les femmes */
femme(augustine).
femme(becassine).
femme(brigitte).
femme(chantal).
femme(celestine).
femme(caroline).
femme(charlotte).
femme(daniela).
femme(dominique).

/* les relations de parenté */
enfant(bernard,andre).
enfant(bernard,augustine).
enfant(babar,andre).
enfant(babar,augustine).
enfant(brigitte,andre).
enfant(brigitte,augustine).
enfant(clement,bernard).
enfant(clement,becassine).
enfant(celestine,bernard).
enfant(caroline,brigitte).
enfant(caroline,baptiste).
enfant(cedric,brigitte).
enfant(cedric,baptiste).
enfant(dudulle,clement).
enfant(dudulle,chantal).
enfant(damien,clement).
enfant(damien,chantal).
enfant(daniela,clement).
enfant(daniela,chantal).
enfant(didier,cedric).
enfant(didier,charlotte).
enfant(dagobert,cedric).
enfant(dagobert,charlotte).
enfant(dominique,cedric).
enfant(dominique,charlotte).


/* relations */

parent(X, Y):- enfant(Y, X).
fils(X, Y):- enfant(X, Y),
	homme(X).
fille(X, Y):- enfant(X, Y),
	femme(X).
pere(X, Y):-parent(X,Y),
	homme(X).
mere(X,Y):-parent(X,Y),
	femme(X).
grand_parent(X,Z):-parent(X,Y),
	parent(Y,Z).
petit_enfant(X,Z):-enfant(X,Y),
	enfant(Y,Z).
grand_pere(X,Y):-grand_parent(X,Y),homme(X).
grand_mere(X,Y):-grand_parent(X,Y),femme(X).

petit_fils(X,Y):-petit_enfant(X,Y), homme(X).
petite_fille(X,Y):-petit_enfant(X,Y), femme(X).

frere_ou_soeur(X,Y):-pere(Z,X), pere(Z,Y), X\=Y.
frere(X,Y):-frere_ou_soeur(X,Y), homme(X).
soeur(X,Y):-frere_ou_soeur(X,Y), femme(X).

oncle_ou_tante(X,Y):-frere_ou_soeur(X,Z), parent(Z,Y).
oncle(X,Y):-frere(X,Z), parent(Z,Y).
tante(X,Y):-soeur(X,Z), parent(Z,Y).

cousin_ou_cousine(X,Y):-grand_pere(Z,X), grand_pere(Z,Y), pere(P,X), pere(Q,Y), P\=Q.
cousin(X,Y):-cousin_ou_cousine(X,Y), homme(X).
cousine(X,Y):-cousin_ou_cousine(X,Y), femme(X).

ancetre(X,Y):-parent(X,Y).
ancetre(X,Y):-parent(X,Z), ancetre(Z,Y).
	
