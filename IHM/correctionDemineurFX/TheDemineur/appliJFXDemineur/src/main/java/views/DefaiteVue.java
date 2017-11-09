package views;

import controleur.Controleur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modele.Classement;

import java.io.IOException;
import java.net.URL;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public class DefaiteVue {


    Controleur monControleur;


    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }





    @FXML
    VBox defaite;



    public Node getNode() {
        return defaite;
    }


    private void initialize() {
        Label label = new Label("La loose...");
        Button choix = new Button("Choisir une partie");
        choix.setOnAction(e -> monControleur.resetPartie());
        Button deconnecter = new Button("Deconnexion");
        deconnecter.setOnAction(e -> monControleur.deconnexion());
        this.defaite.getChildren().addAll(label,choix,deconnecter);
    }

    public static DefaiteVue creerInstance(Controleur c) {
        URL location = DefaiteVue.class.getResource("/views/defaite.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DefaiteVue vue = fxmlLoader.getController();
        vue.setMonControleur(c);
        vue.initialize();
        return vue;
    }

}
