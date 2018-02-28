algae <- read.table("donnees.txt", header=TRUE)
algae

hist(mxPH, prob=T, xlab='', main='Histogram of mxPH', ylim=0:1)
lines(density(mxPH, na.rm=T))

yule <- function(data, seq){
	q<- quantile(data, seq, na.rm=T)
	y<- (q[:-2]+q2Cl-(2*q3Cl))/(q4Cl-q2Cl)
}


