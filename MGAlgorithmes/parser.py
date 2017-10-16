def parserGraphe(nomFichier=None): # a ajouter au constructeur de Graphe
	"""
	Fonction qui retourne un tuple (taille, liste d'adjacences) correspondant au graphe que l'on veut traiter
	"""
	if nomFichier:
		f = open(nomFichier + ".graphe")
		listTemp = f.read().split() #on commence par splitter dans une liste le fichier en fonction des sauts de ligne
		taille = int(listTemp.pop(0)) # la taille correspond à la première valeur
		adjacences = {}
		while listTemp:
			key, strValue = listTemp.pop(0).split(':') # on split ensuite case par case la liste en fonction du caractère ':', ce qui permet de récupérer le sommet et ses voisins
			strValue = re.sub('[\[\]]', '', strValue)
			value = strValue.split(',')
			adjacences[str(key)]=value

		return taille, adjacences # retourne le tuple qui servira à construire le graphe
	else:
		return None
	return res