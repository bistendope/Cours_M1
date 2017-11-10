package views;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by YohanBoichut on 10/11/15.
 */
public class Accueil {


    private Controleur monControleur;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    private Label message;

    @FXML
    private Pane root;

    @FXML
    private Button submit;

    @FXML
    private TextField pseudo;

    public static Accueil creerEtAfficher(Controleur c) {
        URL location = Accueil.class.getResource("/views/accueil.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = c.getStage();
        Accueil vue = fxmlLoader.getController();
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
        vue.setMonControleur(c);
        return vue;
    }


    public void onSubmit(ActionEvent actionEvent) {
        monControleur.tenteLeLogin(pseudo.getText());
    }


    public void afficheMessageErreur(String message) {
        this.message.setText(message);
    }
}
