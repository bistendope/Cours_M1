package controleur;

import javafx.stage.Stage;
import modele.GestionDemineurInterface;
import modele.Plateau;
import modele.exceptions.BombeException;
import modele.exceptions.ExceptionLoginDejaPris;
import views.Accueil;
import views.JeuVue;
import views.MenuDem;

/**
 * Created by YohanBoichut on 10/11/15.
 */
public class Controleur {

    private Stage stage;
    private Accueil accueil;
    private MenuDem menu;
    private GestionDemineurInterface facade;
    private String pseudo;
    private JeuVue jeuVue;


    public Controleur(GestionDemineurInterface facade, Stage stage) {
        this.facade = facade;
        this.stage = stage;
        this.accueil = Accueil.creerEtAfficher(this);
    }

    public void tenteLeLogin(String pseudo) {
        try {
            facade.connexion(pseudo);
            // on est loggé
            this.menu = MenuDem.creerEtAfficher(this);
            this.pseudo = pseudo;
        } catch (ExceptionLoginDejaPris exceptionLoginDejaPris) {
            // erreur de log, on reste sur le login
            accueil.afficheMessageErreur("Login déjà pris");
        }
    }

    public void lancerUnePartie(){
        facade.associerNouvelleGrille(pseudo);
        this.jeuVue = JeuVue.creerEtAfficher(this);
    }

    public Plateau getPlateau(){
        return facade.getPlateau(pseudo);
    }

    public void decouvrir(int ligne, int colonne){
        try{
            facade.decouvrir(pseudo, ligne, colonne);
        }catch(BombeException e){
            //gotoperdu
        }
        jeuVue.afficherGrille();
    }



    public Stage getStage() {
        return stage;
    }
}
