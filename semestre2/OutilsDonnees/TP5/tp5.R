algae <- read.table("donnees.txt", header=TRUE)

algae

NrTotalNA <- function(x){
	res <- complete.cases(x)
	return (length(res[res==FALSE]))
}
# si on veut retirer tous les false de algae, on peut procéder comme suit: 
cleanedAlgae <- algae[complete.cases(algae),]
NrTotalNA(algae)

NrNARow <- function(x){
	sum(is.na(x))
}

manyNAs <- function(data, threshold){
	aux <- apply(data, 1 , percentNARow)
	data[aux<=threshold]
	
}

percentNARow <- function(x){
	mean(is.na(x))
}

table(algae[1])


#tache 3


