package controleur;


import controleur.erreurs.CoupleIdentifiantInexistant;
import controleur.erreurs.JoueurDejaConnecteAppli;
import controleur.erreurs.LoginDejaPris;
import listener.ConnexionErrorEvent;
import listener.InscriptionErrorEvent;
import listener.ModelChangedEvent;
import modele.*;
import modele.exceptions.*;
import rmiService.MonService;
import views.FenetrePrincipale;
import views.MaVue;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;

/**
 * Created by YohanBoichut on 10/11/15.
 */
public class Controleur {


    private FenetrePrincipale maFenetrePrincipale;

    private MonService monGestionDemineurInterface;

    private Integer identifiantUtilisateur;
    private Long t0 ;
    private Long t1;

    private static String HOST = "127.0.0.1";

    public Controleur() {

        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(HOST, 9345);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        String[] names = new String[0];
        try {
            names = registry.list();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        for(String name1 : names){
            System.out.println("~~~~" + name1 + "~~~~");
        }
        try {
            this.monGestionDemineurInterface  = (MonService) registry.lookup(MonService.serviceName);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

        this.maFenetrePrincipale = FenetrePrincipale.creerInstance(this);
        this.maFenetrePrincipale.setConnexionVue();
    }




    public void decouvrir(int i1, int i2)  {


        try {

            try {
                if (this.monGestionDemineurInterface.decouvrir(identifiantUtilisateur,i1,i2)) {
                    t1 = System.currentTimeMillis()-t0;
                    this.monGestionDemineurInterface.updateTemps(identifiantUtilisateur,t1);
                    this.maFenetrePrincipale.setVictoireVue();
                    this.maFenetrePrincipale.modelChanged(new ModelChangedEvent(this));

                }
                else {
                    this.maFenetrePrincipale.modelChanged(new ModelChangedEvent(this));
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } catch (BombeException e) {

            this.maFenetrePrincipale.setDefaiteVue();
        } catch (ExceptionTriche exceptionTriche) {

        }
    }


    public Classement getClassementPartieCourante() {
        try {
            return this.monGestionDemineurInterface.getClassementPartie(this.monGestionDemineurInterface.getPlateau(identifiantUtilisateur).getIdPlateau());
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }



    public Classement getClassementPartie(int i) {
        try {
            return this.monGestionDemineurInterface.getClassementPartie(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Collection<Integer> getListeIdentifiantParties() {
        try {
            return monGestionDemineurInterface.getListeIdentifiantParties();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Plateau getPlateau() {
        try {
            return monGestionDemineurInterface.getPlateau(identifiantUtilisateur);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void associerNouvelleGrille(int i1) {
        try {
            this.monGestionDemineurInterface.associerNouvelleGrille(identifiantUtilisateur,i1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.maFenetrePrincipale.setJeuVue();
        t0 = System.currentTimeMillis();
    }


    public void resetPartie() {
        try {
            this.monGestionDemineurInterface.resetPartie(identifiantUtilisateur);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.maFenetrePrincipale.setChoixVue();
    }


    public void connexion(String s, String s1) throws CoupleIdentifiantInexistant, JoueurDejaConnecteAppli {
        try {
            this.identifiantUtilisateur = this.monGestionDemineurInterface.connexion(s,s1);
            this.maFenetrePrincipale.setChoixVue();
        } catch (ConnexionImpossible connexionImpossible) {
            throw new CoupleIdentifiantInexistant("Couple d'identifiants inexistants !");
        } catch (JoueurDejaConnecte joueurDejaConnecte) {
            throw  new JoueurDejaConnecteAppli("Joueur déjà connecté !");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



    public void inscription(String s, String s1) throws LoginDejaPris {
        try {
            this.identifiantUtilisateur = this.monGestionDemineurInterface.inscription(s,s1);
            this.maFenetrePrincipale.setChoixVue();
        } catch (ExceptionLoginDejaPris exceptionLoginDejaPris) {
            throw new LoginDejaPris("Login déjà pris !!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void deconnexion() {
        try {
            this.monGestionDemineurInterface.deconnexion(this.identifiantUtilisateur);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.identifiantUtilisateur = null;
        this.t0 = null;
        this.t1 = null;
        this.maFenetrePrincipale.setConnexionVue();
    }

    public Joueur getJoueurById() {
        try {
            return this.monGestionDemineurInterface.getJoueurById(this.identifiantUtilisateur);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void goToInscription() {
        this.maFenetrePrincipale.setInscriptionVue();
    }
}
