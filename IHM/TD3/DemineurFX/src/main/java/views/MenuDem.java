package views;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Plateau;

import java.io.IOException;
import java.net.URL;

/**
 * Created by YohanBoichut on 10/11/15.
 */
public class MenuDem {


    private Controleur monControleur;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    private Button submit;

    @FXML
    private Slider pBombes;

    @FXML
    private Slider taille;

    public static MenuDem creerEtAfficher(Controleur c) {
        URL location = MenuDem.class.getResource("/views/menu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuDem vue = fxmlLoader.getController();
        Stage stage = c.getStage();
        stage.setTitle("Menu");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
        vue.setMonControleur(c);
        return vue;
    }


    public void lancerUnePartie(ActionEvent actionEvent) {
        monControleur.lancerUnePartie((int) taille.getValue(), (int) pBombes.getValue());
    }


}
