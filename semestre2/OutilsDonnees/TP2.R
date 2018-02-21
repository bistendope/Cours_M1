t=seq(0,2*pi,0.1)
y=sin(t)
plot(t,y,type="l", xlab="time", ylab="Sine wave", main="Graphe de la fonction sinus")

plot(dnorm,-4,4)
#abline(v=0.2)
#abline(v=3)
abline(h=0)
#segments(x0=c(-2), y0=c(0.0), x1=c(3), y1=c(0.3),col="pink")
#segments(x0=c(-2), y0=c(0.0), x1=c(3), y1=c(0.5),col="red")
#segments(x0=c(-2), y0=c(0.0), x1=c(3), y1=c(0.9),col="blue")
segments(0,0,0,dnorm(0), lty=2)

curve(dt(x, df=5),add=TRUE, col=2)
curve(dt(x, df=30),add=TRUE, col=3)
legend("topleft", legend=c("Normale", "Student(5)", "Student(30)"), col=1:3, lty=1)

#Exercice 3

ozone <- read.table("ozone.txt", header=TRUE)
ozone
plot(maxO3~T12, data=ozone)#pour relier les points il suffit d'utiliser l'argument type="l"; ce graphe n'est pas lisible il faut trier les données

plot(maxO3~T12, data=ozone, type="l")
ordon <- order(ozone[,"T12"])
plot(maxO3~T12, data=ozone[ordon,], type="b")

#Loi des grands nombres
#En statistiques, la loi des grands nombres exprime le fait que les caractéristiques d'un échantillon aléatoire se rapprochent des caractéristiques de l'ensemble de la population pour peu que cet échantillon soit suffisamment grand

set.seed(123)
X <- rbinom(1000, size=1, prob=0.6)
S1 <- cumsum(X) #la fonction cumsum permet de construire un vecteur des sommes cumulées
M1<-S1/(1:1000)
plot(M1, type="l")
abline(h=0.6, col=2)
