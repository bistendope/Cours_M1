print("Hello, world!")
ligne1<-c(1,5,5,0)
ligne2<-c(0,5,6,1)
ligne3<-c(3,0,3,3)
ligne4<-c(4,4,4,2)
mat<-matrix(c(ligne1, ligne2, ligne3, ligne4), nrow=4, ncol=4, byrow=TRUE, dimnames=list(c("Ligne 1", "Ligne 2", "Ligne 3", "Ligne 4"), c("Colonne 1", "Colonne 2", "Colonne 3", "Colonne 4")))
mat

vdiag<-diag(mat)
vdiag
v2FirstRows<-mat[1:2, ]
v2FirstRows
v2LastCols<-mat[ , 3:4]
v2LastColsAlt<-mat[,(ncol(mat)-1):ncol(mat)]
v2LastCols
v2LastColsAlt
vButThirdCol<-mat[ ,-3]
vButThirdCol

matInverse<-solve(mat)
matInverse

matDeterm<-det(mat)
matDeterm


data(iris)
iris2 <- subset(iris,Species=="versicolor")
#iris2<-iris[iris[,5]=="versicolor",]
iris2
iris2[order(iris2[,1], decreasing=TRUE),]
iris2

library(lattice)
data(ethanol)
summary(ethanol)

mat<-matrix(c(1,0,3,4,5,5,0,4,5,6,3,4,0,1,3,2), ncol=4)
mat3<-mat[,apply((mat<6),2,all)]
mat3

library(MASS)
data(Aids2)
summary(Aids2)

ind<-!unlist(lapply(Aids2, is.numeric))
Aids2.qual<-Aids2[,ind]
#Aids2.qual

lapply(Aids2.qual, levels)

conting<-matrix(c(2,1,3,0,0,4),2,3)
colnames(conting) <- c("Ang", "Mer", "Tex")

