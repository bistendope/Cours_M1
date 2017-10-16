#-*- coding: utf-8 -*-

import re
import sys

class Graphe:

	def __init__(self, k, nbSommets, listesAdj):
		self.k = int(k)
		self.nbSommets = int(nbSommets)
		self.listesAdj = listesAdj

	def supprimer_sommet(self, sommetASup):
		if sommetASup in self.listesAdj: #verifie que le sommet a supprimer existe
			self.nbSommets = self.nbSommets - 1
			self.listesAdj.pop(sommetASup, None)
			for key in self.listesAdj:
				self.listesAdj[key] = [sommet for sommet in self.listesAdj[key] if sommet != sommetASup]

	def fvs1(self):
		sommetsASup = []
		for v in self.listesAdj:
			if v in self.listesAdj[v]:
				sommetsASup.append(v)
		for v in sommetsASup:
			self.k = self.k - 1
			self.supprimer_sommet(v)
			return True
		if sommetsASup == []:
			return False

	def fvs2(self):


	def fvs3(self):
		for sommet in self.listesAdj:
			if self.getDegre(sommet) == 0 or 1:
				self.supprimer_sommet(sommet)
				return True
		return False

	def fvs4(self):
		for sommet in self.listesAdj:
			if self.getDegre(sommet) == 2:
				self.linker_voisinsFVS4(sommet)
				self.supprimer_sommet(sommet)
				return True
		return False

	def fvs5(self):
		if self.k < 0:
			return False
		else:
			return True

	def get_degre(self, sommet):
		return len(self.listesAdj[sommet])

	def get_multiplicite(self, x, y):
		return self.listesAdj[x].count(y)
			
	def linker_voisinsFVS4(self, sommet):
		voisin1, voisin2 = self.listesAdj[sommet]
		self.listesAdj[voisin1].append(voisin2)
		self.listesAdj[voisin2].append(voisin1)

def construire_graphe(lignesFic):
	k = lignesFic[1]
	nbSommets = lignesFic[2]
	listesAdj = {}
	for i in range(3, len(lignesFic)):
		listesSommets = re.split(': \[|, |]\n|]', lignesFic[i])
		print(listesSommets)
		del listesSommets[-1]
		print (listesSommets)
		v = listesSommets.pop(0)
		listesAdj[v] = listesSommets
	return k, nbSommets, listesAdj

def reduire_graphe(graphe=None):
	if not graphe break
	grapheModifie = True
	while grapheModifie:
		grapheModifie = False
		grapheModifie = True if graphe.fvs1()
		grapheModifie = True if graphe.fvs2()
		grapheModifie = True if graphe.fvs3()
		grapheModifie = True if graphe.fvs4()
		grapheModifie = True if graphe.fvs5()
	return graphe
	


				
def main():
	with open(sys.argv[1], 'r') as fichier:
		lignesFic = fichier.readlines()
	k, nbSommets, listesAdj = construire_graphe(lignesFic)
	construire_graphe(lignesFic)
	graphe = Graphe(k, nbSommets, listesAdj)
	print(graphe.k)
	print(graphe.nbSommets)
	print(graphe.listesAdj)
	graphe.fvs4()
	print(graphe.k)
	print(graphe.nbSommets)
	print(graphe.listesAdj)

main()