algae <- read.table("/home/etud/o2122130/Cours_M1/semestre2/OutilsDonnees/TP4/donnees.txt", header=TRUE)

NrTotalNA <- function(x){
	res <- complete.cases(x)
	return (length(res[res==FALSE]))
}
# si on veut retirer tous les false de algae, on peut procéder comme suit: cleanedAlgae <- algae[complete.cases(algae),]
NrTotalNA(algae)

NrNARow <- function(x){
	sum(is.na(x))
}

manyNAsfausse <- function(data, threshold){
	nrNAvec <- apply(data,1,NrNARow)
	nrCol = ncol(data)
	nrColToRemove = nrCol * (threshold/100)
	indexRes <- which(nrNAvec[nrNAvec < nrColToRemove], arr.ind=T)
	return(indexRes)
}

manyNAs <- function(data, threshold){
	aux <- apply(data, 1 , percentNARow)
	data[aux<=threshold]
}

percentNARow <- function(x){
	mean(is.na(x))
}

for(i in 1:ncol(x)){
	if()
		x[is.na(x[ ,i]), i] <- median(x[ ,i], na.rm=T)
}


Mode <- function (z) { # capital "M" to avoid conflict with R functions
	xtab <- table(z)
	xmode <- names(which(xtab == max(xtab)))
	if (length(xmode) > 1)
		xmode <- ">1 mode"
	return(xmode)
}

temp <- manyNAs (algae, 0.20)
cleanAlgae <- centerImputation(temp)

apply(Mode(algae))


