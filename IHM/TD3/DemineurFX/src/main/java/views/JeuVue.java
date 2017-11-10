package views;

import controleur.Controleur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Case;
import modele.Plateau;
import modele.exceptions.BombeException;

import java.io.IOException;
import java.net.URL;

public class JeuVue {

    @FXML
    VBox racine;

    Button[][] tousMesBoutons;

    private Controleur monControleur;

    private int LONGUEUR = monControleur.getPlateau().getMonPlateau().length;
    private int LARGEUR = monControleur.getPlateau().getMonPlateau()[0].length;
    private int LONGUEURb = 500/LONGUEUR;
    private int LARGEURb = 500/LARGEUR;

    public static JeuVue creerEtAfficher(Controleur c) {
        URL location = JeuVue.class.getResource("/views/jeuVue.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JeuVue vue = fxmlLoader.getController();
        Stage maStage = c.getStage();
        maStage.setTitle("Jeu Demineur");
        maStage.setScene(new Scene(root, 500, 500));
        maStage.show();
        vue.setMonControleur(c);
        vue.initialiser();
        return vue;
    }

    private void initialiser() {
        tousMesBoutons = new Button[LONGUEUR][LARGEUR];
        GridPane monPlateau = new GridPane();
        Plateau lePlateau = this.monControleur.getPlateau();
        for (int i=0;i< LARGEUR*LONGUEUR;i++) {
            Button monBouton = new Button();
            monBouton.setPrefSize(LONGUEURb,LARGEURb);
            final int j =i;
            if (lePlateau.getMonPlateau()[i/LONGUEUR][i%LARGEUR].getCachee()) {
                monBouton.setText("U");
                monBouton.setOnAction(e -> monControleur.decouvrir(j/LONGUEUR,j%LARGEUR));
            }
            else {
                monBouton.setText(((Integer)lePlateau.getMonPlateau()[i/LONGUEUR][i%LARGEUR].getValeur()).toString());
                monBouton.setDisable(true);
            }
            this.tousMesBoutons[i/LONGUEUR][i%LARGEUR] = monBouton;
            monPlateau.add(monBouton,i/LONGUEUR,i%LARGEUR);
        }
        racine.getChildren().addAll(monPlateau);
        racine.setAlignment(Pos.CENTER);
        racine.setPrefSize(500,500);
    }


    public void setMonControleur(Controleur monControleur){
        this.monControleur = monControleur;
    }

    public void afficherGrille(){
        GridPane grille = new GridPane();
        Case[][] jeu = monControleur.getPlateau().getMonPlateau();

        final int lignes = jeu.length;
        final int colonnes = jeu[0].length;

        for(int lig=0; lig<lignes; lig++){
            for (int col=0; col<colonnes; col++){
                Case laCase = jeu[lig][col];
                Button bouton = new Button();
                int finalLig = lig;
                int finalCol = col;
                if(laCase.getCachee()){
                    bouton.setText("?");
                    bouton.setOnAction(e->monControleur.decouvrir(finalLig, finalCol));
                }else{
                    bouton.setText(""+laCase.getValeur());
                    bouton.setDisable(true);
                }
                grille.add(bouton, col, lig);
            }
        }
    }
}
