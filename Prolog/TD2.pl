somme(X,Y,Z):- Z is X + Y.

max2(X,Y, X):-X>Y.
max2(X,Y,Y).

max3(X,Y,Z,X):-X>=Y,X>=Z.
max3(X,Y,Z,Y):-Y>=Z.
max3(X,Y,Z,Z).

bonjour(0).
bonjour(N):-N1 is N-1,
	write('bonjour!'),nl,
	bonjour(N1). 

suite(N):-suite(N,1).	
suite(N,Inc):- Inc =< N,
	write(Inc), nl,
	Inc1 is Inc + 1,
	suite(N,Inc1).
	
pair_ou_impair(0):-write('pair!').
pair_ou_impair(1):-write('impair!').
pair_ou_impair(N):-N>1,N1 is N-2, pair_ou_impair(N1).

/*nul*/
somme(N):- somme(N,0).
somme(0,0).
somme(N,R):-R1 is R+N, N1 is N-1, somme(N1, R1).

som(0,0).
som(N,Y):-N>0, N2 is N-1, som(N2, Y2), Y is Y2+N.

fact(0,0).
fact(1,1).
fact(N,Y):-N>1, N2 is N-1, fact(N2, Y2), Y is Y2*N.

fibo(0,1,1).
fibo(1,1,2).
fibo(X,Y,Z):-X>0, Z1 is Z+Y+X,fibo(X1,Y1,Z1),X is X1-1, Y is Y1-1.
