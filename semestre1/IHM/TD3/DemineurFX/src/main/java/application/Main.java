package application;

import controleur.Controleur;
import javafx.application.Application;
import javafx.stage.Stage;
import modele.GestionDemineur;
import modele.GestionDemineurInterface;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        GestionDemineurInterface facade = new GestionDemineur();
        facade.connexion("test");
        Controleur monControleur = new Controleur(facade, primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
