package views;

import controleur.Controleur;
import controleur.erreurs.LoginDejaPris;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import listener.InscriptionErrorEvent;
import listener.InscriptionErrorListener;

import java.io.IOException;
import java.net.URL;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public class InscriptionVue {


    private Controleur monControleur;


    @FXML
    TextField pseudo;

    @FXML
    PasswordField mdp;

    @FXML
    PasswordField confirmation;

    @FXML
    VBox inscription;
    public Node getNode() {
        return inscription;
    }


    public static InscriptionVue creerInstance(Controleur c) {
        URL location = InscriptionVue.class.getResource("/views/inscription.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InscriptionVue vue = fxmlLoader.getController();
        vue.setMonControleur(c);
        return vue;
    }

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }


    public void validerInscription(ActionEvent actionEvent) {

        String motDePasse = this.mdp.getText();
        String confirmationMotDePasse = this.confirmation.getText();
        String nom = this.pseudo.getText();

        if (motDePasse.length() == 0 || confirmationMotDePasse.length() == 0 || nom.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Problème d'inscription");
            alert.setContentText("Tous les champs sont obligatoires");
            alert.showAndWait();
        } else {

            if (this.mdp.getText().equals(this.confirmation.getText())) {
                try {
                    this.monControleur.inscription(nom,motDePasse);
                } catch (LoginDejaPris event) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Problème d'inscription");
                    alert.setContentText(event.getMessage());
                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Problème d'inscription");
                alert.setContentText("Les mots de passe diffèrent !");
                alert.showAndWait();
            }

        }
    }
}
