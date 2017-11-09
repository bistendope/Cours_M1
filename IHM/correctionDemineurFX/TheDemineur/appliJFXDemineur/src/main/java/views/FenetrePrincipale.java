package views;

import controleur.Controleur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import listener.*;

import java.io.IOException;
import java.net.URL;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public class FenetrePrincipale implements ModelListener{



    @FXML
    BorderPane maFenetre;

    ConnexionVue connexionVue;
    InscriptionVue inscriptionVue;
    ChoixVue choixVue;
    JeuVue jeuVue;
    private Controleur monControleur;

    public void setMonTheatre(Stage monTheatre) {
        this.monTheatre = monTheatre;
    }

    private Stage monTheatre;


    public void setConnexionVue() {
        this.maFenetre.setCenter(this.connexionVue.getNode());
        this.monTheatre.show();
    }



    public void setInscriptionVue() {
        this.maFenetre.setCenter(this.inscriptionVue.getNode());
        this.monTheatre.show();
    }


    public void setChoixVue() {
        this.maFenetre.setCenter(this.choixVue.getNode());
        this.monTheatre.show();
    }

    public void setJeuVue() {
        this.jeuVue = JeuVue.creerInstance(this.monControleur);
        this.maFenetre.setCenter(this.jeuVue.getNode());
        this.monTheatre.show();
    }

    public static FenetrePrincipale creerInstance(Controleur c) {
        URL location = FenetrePrincipale.class.getResource("/views/fenetrePrincipale.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FenetrePrincipale vue = fxmlLoader.getController();
        vue.setMonControleur(c);

        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root,600,600));
        vue.setMonTheatre(primaryStage);
        return vue;
    }

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
        this.connexionVue = ConnexionVue.creerInstance(monControleur);
        this.inscriptionVue = InscriptionVue.creerInstance(monControleur);
        this.choixVue = ChoixVue.creerInstance(monControleur);
    }



    @Override
    public void modelChanged(ModelChangedEvent event) {
        this.choixVue.modelChanged(event);
        this.jeuVue.modelChanged(event);
        this.monTheatre.show();
    }

    public void setVictoireVue() {
        this.maFenetre.setCenter(VictoireVue.creerInstance(this.monControleur).getNode());
        this.monTheatre.show();
    }

    public void setDefaiteVue() {
        this.maFenetre.setCenter(DefaiteVue.creerInstance(this.monControleur).getNode());
        this.monTheatre.show();
    }
}
