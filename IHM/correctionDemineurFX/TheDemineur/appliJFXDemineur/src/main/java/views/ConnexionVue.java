package views;

import controleur.Controleur;
import controleur.erreurs.CoupleIdentifiantInexistant;
import controleur.erreurs.JoueurDejaConnecteAppli;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import listener.ConnexionErrorEvent;
import listener.ConnexionErrorListener;

import java.io.IOException;
import java.net.URL;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public class ConnexionVue {


    @FXML
    VBox connexion;



    @FXML
    TextField pseudo;

    @FXML
    PasswordField mdp;

    private Controleur monControleur;

    public Node getNode() {
        return connexion;
    }


    public static ConnexionVue creerInstance(Controleur c) {
        URL location = ConnexionVue.class.getResource("/views/connexion.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConnexionVue vue = fxmlLoader.getController();
        vue.setMonControleur(c);
        return vue;
    }

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }


    public void runConnexion(ActionEvent actionEvent) {
        if ((this.pseudo.getText().length()>0) && (this.mdp.getText().length()>0)) {
            try {
                this.monControleur.connexion(this.pseudo.getText(),this.mdp.getText());
            } catch (CoupleIdentifiantInexistant coupleIdentifiantInexistant) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Problème de connexion");
                alert.setContentText(coupleIdentifiantInexistant.getMessage());
                alert.showAndWait();
            } catch (JoueurDejaConnecteAppli joueurDejaConnecteAppli) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Problème de connexion");
                alert.setContentText(joueurDejaConnecteAppli.getMessage());
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Problème de saisie");
            alert.setContentText("Les champs sont obligatoires!");
            alert.showAndWait();
        }
    }

    public void runInscription(ActionEvent actionEvent) {
        this.monControleur.goToInscription();
    }


}
