package modele;

import java.io.Serializable;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public class Joueur implements Serializable{
    private int idJoueur;
    private String pseudoJoueur;
    private String mdpJoueur;


    private static int identidiant = 0;



    public Joueur(){

    }

    public Joueur(String pseudo, String mdp) {
        this.pseudoJoueur = pseudo;
        this.mdpJoueur = mdp;
        this.idJoueur = identidiant;
        identidiant++;
    }



    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getPseudoJoueur() {
        return pseudoJoueur;
    }

    public void setPseudoJoueur(String pseudoJoueur) {
        this.pseudoJoueur = pseudoJoueur;
    }

    public String getMdpJoueur() {
        return mdpJoueur;
    }

    public void setMdpJoueur(String mdpJoueur) {
        this.mdpJoueur = mdpJoueur;
    }
}
