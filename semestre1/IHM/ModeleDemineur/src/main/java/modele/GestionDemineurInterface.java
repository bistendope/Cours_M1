package modele;

import modele.exceptions.BombeException;
import modele.exceptions.ExceptionLoginDejaPris;

/**
 * Created by YohanBoichut on 22/10/15.
 */
public interface GestionDemineurInterface {


    /**
     * Le joueur pseudo veut dévoiler la case (x,y)
     * @param x
     * @param y
     * @return true si le joueur a gagné et false si la partie n'est pas encore terminée
     * @throws BombeException le joueur est tombé sur une bombe, la partie est terminée
     */

     boolean decouvrir(String pseudo,int x, int y) throws BombeException;

    /**
     * Permet d'obtenir le plateau du joueur pseudo
     * @return
     */
    Plateau getPlateau(String pseudo);

    /**
     * Permet de générer une nouvelle grille pour le joueur login
     */
    void associerNouvelleGrille(String pseudo);

    /**
     * Permet de générer une nouvelle grille avec paramètres pour le joueur login
     */
    void associerNouvelleGrille(String pseudo, int size, int pourcentageBombes);

    /**
     * Permet de savoir si la connexion est possible
     * @param pseudo
     * @throws ExceptionLoginDejaPris si la connexion n'est pas possible
     */
    public void connexion(String pseudo) throws ExceptionLoginDejaPris;


    /**
     * Supprime le joueur "pseudo" des joueurs connectés?
     * @param pseudo : Pseudo du joueur souhaitant une déconnexion
     */
    void deconnexion(String pseudo);
}
