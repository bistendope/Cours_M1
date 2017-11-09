package views;

import controleur.Controleur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import listener.ModelChangedEvent;
import listener.ModelListener;
import modele.Plateau;

import java.io.IOException;
import java.net.URL;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public class JeuVue implements ModelListener{


    private Controleur monControleur;

    private static double LONGUEUR = 500/Plateau.TAILLE;
    private static double LARGEUR = 500/Plateau.TAILLE;

    @FXML
    VBox lieu;

    Button[][] tousMesBoutons;



    private void initialiser() {
        tousMesBoutons = new Button[Plateau.TAILLE][Plateau.TAILLE];
        GridPane monPlateau = new GridPane();
        Plateau lePlateau = this.monControleur.getPlateau();
        for (int i=0;i< Plateau.TAILLE*Plateau.TAILLE;i++) {
            Button monBouton = new Button();
            monBouton.setPrefSize(LONGUEUR,LARGEUR);
            final int j =i;
            if (lePlateau.getMonPlateau()[i/Plateau.TAILLE][i%Plateau.TAILLE].getCachee()) {
                monBouton.setText("U");
                monBouton.setOnAction(e -> monControleur.decouvrir(j/Plateau.TAILLE,j%Plateau.TAILLE));
            }
            else {
                monBouton.setText(((Integer)lePlateau.getMonPlateau()[i/Plateau.TAILLE][i%Plateau.TAILLE].getValeur()).toString());
                monBouton.setDisable(true);
            }
            this.tousMesBoutons[i/Plateau.TAILLE][i%Plateau.TAILLE] = monBouton;
            monPlateau.add(monBouton,i/Plateau.TAILLE,i%Plateau.TAILLE);
        }
        lieu.getChildren().addAll(monPlateau);
        lieu.setAlignment(Pos.CENTER);
        lieu.setPrefSize(500,500);
    }



    private void update() {
        Plateau lePlateau = this.monControleur.getPlateau();
        for (int i=0;i< Plateau.TAILLE*Plateau.TAILLE;i++) {
            Button monBouton = this.tousMesBoutons[i/Plateau.TAILLE][i%Plateau.TAILLE];
            if (!lePlateau.getMonPlateau()[i/Plateau.TAILLE][i%Plateau.TAILLE].getCachee()) {
                monBouton.setText(((Integer)lePlateau.getMonPlateau()[i/Plateau.TAILLE][i%Plateau.TAILLE].getValeur()).toString());
                monBouton.setDisable(true);
            }
        }

    }
    public static JeuVue creerInstance(Controleur c) {
        URL location = JeuVue.class.getResource("/views/jeu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JeuVue vue = fxmlLoader.getController();
        vue.setMonControleur(c);
        vue.initialiser();
        return vue;
    }

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }


    @Override
    public void modelChanged(ModelChangedEvent event) {
        this.update();
    }

    public Node getNode() {
        return lieu;
    }
}
