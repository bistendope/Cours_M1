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
public class VictoireVue {


    Controleur monControleur;


    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }





    @FXML
    VBox victoire;



    public Node getNode() {
        return victoire;
    }

    private void initialize() {

            Classement classement = this.monControleur.getClassementPartieCourante();
            ObservableList<String> data = FXCollections.observableArrayList(Classement.classementToStringList(classement));
            ListView<String> maListe = new ListView<>(data);
        Label label = new Label("VICTOIRE");
        Button choix = new Button("Choisir une partie");
        choix.setOnAction(e -> monControleur.resetPartie());
        Button deconnecter = new Button("Deconnexion");
        deconnecter.setOnAction(e -> monControleur.deconnexion());
        this.victoire.getChildren().addAll(label,maListe,choix,deconnecter);
    }




    public static VictoireVue creerInstance(Controleur c) {
        URL location = VictoireVue.class.getResource("/views/victoire.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        VictoireVue vue = fxmlLoader.getController();
        vue.setMonControleur(c);
        vue.initialize();
        return vue;
    }

}
