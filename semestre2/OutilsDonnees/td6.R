Temp_algae <- read.table("donnees.txt", header=TRUE)

#task 1 
algae <- manyNAs(Temp_algae,0.20)
otheralgae <- centerImputation(algae)
attach(algae)
otheralgae
#table 2
Cpriori <- prop.table(table(a1))

# avec size=medium
Psize <- prop.table(table(speed(speed,al),2)



##task 3
##avec autumn, large, slow, 8.53,11.1 etc
Pseason <- prop.table(table(season,a1),2)
##probabilité que ce soit winter
PwinterFeqLow <- Pseason["winter", "feqLow"]

Psize <- prop.table(table(size,a1),2)
PmediumFeqHigh <- Psize["winter", "feqHigh"]

Pspeed <- prop.table(table(speed,a1),2)
PslowFeqMedium <- Pspeed["slow", "feqMedium"]


task3
------------------
PautFeqlow <- Pseason["autumn","feqlow"]
PlargeFeqlow <-Psize["large","feqlow"]
PsLowFeqLow <- Pspeed["slow", "feqLow"]

PautFeqMedium <- Pseason["autumn","feqMedium"]
PlargeFeqMedium <-Psize["large","feqMedium"]
PsLowFeqMedium <- Pspeed["slow", "feqMedium"]

PautFeqLarge <- Pseason["autumn","feqLarge"]
PlargeFeqLarge <-Psize["large","feqLarge"]
PsLowFeqLarge <- Pspeed["slow", "feqLarge"]

#p(mxPH|C)
meanmxPH <- mean(algae(a1=="feqlow","mxPH"], na.rm=T)
stdmxPH <- sd(algae(a1=="feqlow","mxPH"],na.rm=T)
//probabilité d'avoir la valeur 8.53
L_PmxPH <- dnorm(8.53, meanmxPH, stdmxPH)
L_PmxPH

meanmxPH <- mean(algae(a1=="feqmedium","mxPH"], na.rm=T)
stdmxPH <- sd(algae(a1=="feqmedium","mxPH"],na.rm=T)
M_PmxPH <- dnorm(8.53, meanmxPH, stdmxPH)
M_PmxPH

meanmxPH <- mean(algae(a1=="feqLarge","mxPH"], na.rm=T)
stdmxPH <- sd(algae(a1=="feqLarge","mxPH"],na.rm=T)
La_PmxPH <- dnorm(8.53, meanmxPH, stdmxPH)
La_PmxPH


##task 4
#p(mxPH=8.25|C=feqLow)
meanmaxPH <- mean(algae[a1=="feqLow", "mxPH"], na.rm=TRUE)
stdmaxPH <- sd(algae[a1 == "feqLow","mxPH"], na.rm=TRUE)
PmaxPH <- dnorm(8.25, meanmaxPH,stdmaxPH)

#p(mnO2=10.4|C=feqHigh)

meanmnO2 <- mean(algae[a1=="feqHigh", "mnO2"], na.rm=TRUE)
stdnmnO2 <- sd(algae[a1 == "feqHigh","mnO2"], na.rm=TRUE)
PmnO2 <- dnorm(10.4, meanmnO2,stdnmnO2)

#p(PO4=57.833|C=feqMedium)
#même chose avec PO4




##task 5

Freqlow :
	P(s=out | freqLow)*P(size=large|freqLow)*P(speed=low|freqLow)*P(mxPH=8.53|freqlow)*.... P(clha=21.22)*P(freqLow)

FreqMedium :
	P(s=out |FreqMedium)* P(size=large|freqMedium)...*P(FreqMedium)

FreqHigh :
	P(s=out | FreqHigh) * P(size=large|freqHigh)...*P(freqHigh)





Plow <- PautFeqlow*PlargeFeqLow*PslowFeqLow*L_PmxPh*L_PmnO2*Cpriori["feqLow"]

//on fait la meme avec medium et large



####################"""
Norma <- P_low+P_medium+P_large

P_low_Now <-P_low/Norma





#######################################
NrNArow<-function(x){
sum(is.na(x))
}



manyNAs<-function(x,t){
	aux <- apply(x,1,NrNArow)/ncol(x)
	x[aux <=t,]
}

centerImputation <- function(x)
{
	for(i in 1:ncol(x))
	{
		if((class(x[,i]) == "numeric")){
			x[,i][is.na(x[,i])] <-median(x[,i], na.rm = TRUE)
		}
		else if ((class(x[,i]) == "factor")){
			x[,i][is.na(x[,i])] <- Mode(x[,i])
		}
	}
}

Mode <- function(x)
{
	xtab<-table(x)
	xmode<-names(which(xtab == max(xtab)))
	if(length(xmode) > 1)
		xmode <- ">1 mode"
	return(xmode)
}
