package controleur;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.GestionDemineurInterface;
import modele.exceptions.ExceptionLoginDejaPris;
import views.Accueil;
import views.Demineur;
import views.MenuDem;

/**
 * Created by YohanBoichut on 10/11/15.
 */
public class Controleur {

    private Stage stage;
    private Accueil accueil;
    private MenuDem menu;
    private Demineur demineur;
    private GestionDemineurInterface facade;


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
        } catch (ExceptionLoginDejaPris exceptionLoginDejaPris) {
            // erreur de log, on reste sur le login
            accueil.afficheMessageErreur("Login déjà pris");
        }


    }


    public Stage getStage() {
        return stage;
    }
}
