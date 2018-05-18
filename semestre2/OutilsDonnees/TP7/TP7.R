##show distribution of sample means of varying size samples
numcases <- 10000 #how many samples to take?
min <- 0 #lowest value
max <- 1
ntimes <- 6
op<- par(mfrow=c(ntimes,1), mar=c(2.5, 2.5,2.5,2.5), mgp=c(3, 1, 0)) #stack ntimes graphs on
top of each other
i2 <- 1 #initialize counters
for (i in 1:ntimes) #repeat n times
{ sample=rep(0,numcases) #create a vector
k=0 #start off with an empty set of counters
for (j in 1:i2) # inner loop
{
sample <- sample +runif(numcases,min,max)
k <- k+1 }
x <- sample/k
hist(x, xlim=range(0,1),prob=T ,main=paste( "samples of size", k ),col="blue")
i2 <- 2*i2 } #end of i loop


load("algae.RData")
attach(algae)
hist(a1,prob=T,breaks=30)
#Now, suppose that we would like to infer the mean of a1 in algae based on subsamples — the actual mean
#of a1 in algae is 16.9235. Let’s take 100 subsample of size 30.

results =numeric(0) # a place to store the results
for (i in 1:100) { # the for loop --- 100 samples
	S = sample(a1,30) # take a random sample of size 30
	results[i]= mean(S) # store the answer
}
hist(results,prob=T)



## t-distribution ****
m <- mean(iris[iris$Species=="setosa","Petal.Length"])
s <- sd(iris[iris$Species=="setosa","Petal.Length"])
n <- nrow(iris)
error <- qt(0.975,df=n-1)*s/sqrt(n) ## qt is a quantile function for the t-distribution with
#df degrees of freedom. A quantile function returns for a given probability, the value which
#the random variable will be at, or below, with that probability
T_left <- m-error
T_right <- m+error
T_left
T_right


gardens <- read.table("gardens.txt", header=TRUE)
attach(gardens)
t.test(gardenA,gardenB)

ma<-mean(gardenA)
mb<-mean(gardenB)
sa<-sd(gardenA)

sb <- sd(gardenB)
m <- ma- mb
na <- length(gardenA)
nb <- length(gardenB) 
s <- sqrt(sa^2/na + s^2/nb)




