package rmiService;


import modele.*;
import modele.exceptions.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by YohanBoichut on 08/10/14.
 */
public class MonServiceImpl implements MonService {

    private  GestionDemineurEvolueInterface maDAO = GestionDemineurEvolue.getInstance();

    @Override
    public Integer connexion(String nom, String password) throws RemoteException, JoueurDejaConnecte, ConnexionImpossible {
        return maDAO.connexion(nom,password);
    }

    @Override
    public Boolean decouvrir(Integer id, Integer x, Integer y) throws RemoteException, BombeException {
        return maDAO.decouvrir(id,x,y);
    }

    @Override
    public void updateTemps(Integer id, Long temps) throws RemoteException, ExceptionTriche {
        this.maDAO.updateTemps(id,temps);
    }

    @Override
    public Classement getClassementPartie(Integer i) throws RemoteException {
        return this.maDAO.getClassementPartie(i);
    }

    @Override
    public ArrayList<Integer> getListeIdentifiantParties() throws RemoteException {
        return new ArrayList<Integer>(this.maDAO.getListeIdentifiantParties());
    }

    @Override
    public Plateau getPlateau(Integer idUser) throws RemoteException {
        return this.maDAO.getPlateau(idUser);
    }

    @Override
    public void associerNouvelleGrille(Integer idUser, Integer grille) throws RemoteException {
        this.maDAO.associerNouvelleGrille(idUser,grille);
    }

    @Override
    public void resetPartie(Integer idUser) throws RemoteException {
        this.maDAO.resetPartie(idUser);
    }

    @Override
    public Integer inscription(String nom, String password) throws RemoteException, ExceptionLoginDejaPris {
        return this.maDAO.inscription(nom,password);
    }

    @Override
    public void deconnexion(Integer idUser) throws RemoteException {
        this.maDAO.deconnexion(idUser);
    }

    @Override
    public Joueur getJoueurById(Integer id) throws RemoteException {
        return this.maDAO.getJoueurById(id);
    }


}
