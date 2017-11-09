package rmiService;

import modele.Classement;
import modele.Joueur;
import modele.Plateau;
import modele.exceptions.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by YohanBoichut on 08/10/14.
 */
public interface MonService extends Remote{


    public final String serviceName = "DemineurDAO";

    abstract Integer connexion(String nom, String password) throws RemoteException, JoueurDejaConnecte, ConnexionImpossible;
    abstract Boolean decouvrir(Integer id, Integer x, Integer y) throws RemoteException, BombeException;
    abstract void updateTemps(Integer id,Long temps) throws RemoteException, ExceptionTriche;
    abstract Classement getClassementPartie(Integer i) throws RemoteException;
    abstract ArrayList<Integer> getListeIdentifiantParties() throws RemoteException;
    abstract Plateau getPlateau(Integer idUser) throws RemoteException;
    abstract void associerNouvelleGrille(Integer idUser,Integer grille) throws RemoteException;
    abstract void resetPartie(Integer idUser) throws RemoteException;
    abstract Integer inscription(String nom, String password) throws RemoteException, ExceptionLoginDejaPris;
    abstract void deconnexion(Integer idUser) throws  RemoteException;
    abstract Joueur getJoueurById(Integer id) throws RemoteException;

}
