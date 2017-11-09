package modele;

import java.io.Serializable;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public class Rank implements Serializable {

    Joueur joueur;
    double tempsRealise;

    public Rank() {
    }

    public Rank(Joueur joueur, long tempsRealise) {
        this.joueur = joueur;
        this.tempsRealise = tempsRealise/(1000.0);
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public double getTempsRealise() {
        return tempsRealise;
    }

    public void setTempsRealise(long tempsRealise) {
        this.tempsRealise = tempsRealise;
    }

    @Override
    public String toString() {
        return joueur.getPseudoJoueur() + " : "+ tempsRealise;
    }
}
