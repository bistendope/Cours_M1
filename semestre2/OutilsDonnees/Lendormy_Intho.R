#Algorithme du perceptron

perceptron <- function(dframe){

	#ajout d'une colonne x0 de valeur 1
	x0 <- rep(1,nrow(dframe));

	#ajout de x0 au debut de notre dframe
	df<-data.frame(x0,dframe);

	#initialisation de somme pour calcul de poids et de sorties
	somme<-0;
	somme2<-0;

	#Variables de poids et de sorties
	W<-sample(-5:5,size=ncol(df),replace=T);
	W2<-sample(-5:5,size=ncol(df),replace=T);

	for(i in 1:ncol(df)){
		somme<-somme+(df[,i]*W[i]);
		somme2<-somme2+(df[,i]*W2[i]);
	}

	signe<-ifelse(somme<0,0,1);
	my_classe <- ifelse(somme2<0,0,1);

	## génération du graphique selon 2 types de coordonnées
	plot(dframe[,2]~dframe[,1],data=df);
	## ici on marque les catégories avec "+" ou "-"
	points(subset(data.frame(df[,2],df[,3]),signe==0), col="green", pch="-");
	points(subset(data.frame(df[,2],df[,3]),signe==1), col="red", pch="+");
	
	#ajout du signe en fin de notre dataframe existante
	df2<-data.frame(df,signe);

	#booleen qui représente la convergence
	bool <- FALSE;

	ITER<-0;

	while((bool==FALSE) && (ITER<10)){
		ITER<-ITER+1;

		bool= TRUE;

		for(i in 1:nrow(df2)){

			#comparaison jusqu'à convergence
			if(signe[i]!=my_classe[i]){

				##Note: anomalie au niveau de df2[i,ncol(df2)]
				W2<-W2+df2[i,ncol(df2)]*(signe[i]-my_classe[i]);
				bool = FALSE;

				for(i in 1:ncol(df)){
					somme2<-somme2+(df[,i]*W2[i]);
				}
				my_classe <- ifelse(somme2<0,0,1);
				abline(a=(-(W2[1]/W2[3])), b=(-(W2[2]/W2[3])), col='black');
				
			}

			

				print(my_classe);

				
			
	
		}
		##Note remplacer ensuite par W2 quand anomalie sera résolue
		abline(a=(-(W[1]/W[3])), b=(-(W[2]/W[3])), col='blue');

	}

}




##Simulation du perceptron sur un jeu de données au hasard

set.seed(007);

#on fait un tirage de 100 valeurs
x1 <- runif(100, -10, 10);
x2 <- runif(100, -10, 10);

#on réunit x1 et x2 dans une dataframe df
df <- data.frame(x1,x2);


perceptron(df);
		






