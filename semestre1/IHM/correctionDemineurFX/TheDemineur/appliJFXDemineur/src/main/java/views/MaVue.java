package views;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by YohanBoichut on 10/11/15.
 */
public class MaVue {


    private Controleur monControleur;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    private Button monBouton;



    @FXML
    private TextField monChamp;

    public static MaVue creerInstance(Controleur c) {
        URL location = MaVue.class.getResource("/views/sample.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MaVue vue = fxmlLoader.getController();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        vue.setMonControleur(c);
        return vue;
    }


    public void cliquer(ActionEvent actionEvent) {
        this.setMonChamp("Yeah!!");
    }

    public void setMonChamp(String affichage) {
        this.monChamp.setText(affichage);
    }
}
