package views;

import controleur.Controleur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import listener.ModelChangedEvent;
import listener.ModelListener;
import modele.Classement;
import modele.Rank;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.NodeChangeEvent;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public class ChoixVue implements ModelListener{


    Controleur monControleur;


    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }





    @FXML
    VBox choix;

    Map<Integer,ObservableList<String>> datas = new HashMap<>();
    Map<Integer,ListView<String>> mesListes = new HashMap<>();

    public Node getNode() {
        return choix;
    }

    private void initialize() {

        Accordion mesVolets = new Accordion();


        for(int idPartie : this.monControleur.getListeIdentifiantParties()) {
            VBox box = new VBox();
            Classement classement = this.monControleur.getClassementPartie(idPartie);

            ObservableList<String> data = FXCollections.observableArrayList(Classement.classementToStringList(classement));
            ListView<String> maListe = new ListView<>(data);
            Button jouer = new Button("Jouer");
            jouer.setOnAction(event -> monControleur.associerNouvelleGrille(idPartie));
            box.getChildren().addAll(maListe,jouer);
            TitledPane monPaneau = new TitledPane("Partie "+idPartie,box);
            mesVolets.getPanes().add(monPaneau);
            datas.put(idPartie,data);
            mesListes.put(idPartie,maListe);
        }
        this.choix.getChildren().add(mesVolets);
    }




    public static ChoixVue creerInstance(Controleur c) {
        URL location = ChoixVue.class.getResource("/views/choix.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ChoixVue vue = fxmlLoader.getController();
        vue.setMonControleur(c);
        vue.initialize();
        return vue;
    }

    @Override
    public void modelChanged(ModelChangedEvent event) {
        for(Integer i : datas.keySet()) {
            datas.get(i).setAll(Classement.classementToStringList(monControleur.getClassementPartie(i)));
            mesListes.get(i).refresh();
        }
    }
}
