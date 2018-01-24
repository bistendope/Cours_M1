package modele;

import modele.exceptions.*;

import java.util.Collection;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public interface GestionDemineurEvolueInterface extends GestionDemineurSuperInterface {


    boolean decouvrir(int idJoueur, int x, int y) throws BombeException;

    Classement getClassementPartie(int idPartie);

    Collection<Integer> getListeIdentifiantParties();

    Plateau getPlateau(int IdJoueur);

    void associerNouvelleGrille(int idJoueur, int idGrille);

    void resetPartie(int id);

    int connexion(String pseudo, String password) throws ConnexionImpossible, JoueurDejaConnecte;

    boolean updateTemps(int idJoueur, long temps) throws ExceptionTriche;

    int inscription(String pseudo, String password) throws ExceptionLoginDejaPris;

    void deconnexion(int idJoueur);


    Joueur getJoueurById(int id);
}
