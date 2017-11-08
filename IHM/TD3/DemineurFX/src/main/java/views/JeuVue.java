package views;

import controleur.Controleur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modele.Case;
import modele.exceptions.BombeException;

import java.io.IOException;
import java.net.URL;

public class JeuVue {

    private Controleur monControleur;

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
        maStage.setScene(new Scene(root, 300, 275));
        maStage.show();
        vue.setMonControleur(c);
        return vue;
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
