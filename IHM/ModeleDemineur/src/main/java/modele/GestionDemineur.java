package modele;

import modele.exceptions.BombeException;
import modele.exceptions.ExceptionLoginDejaPris;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YohanBoichut on 22/10/15.
 */
public class GestionDemineur implements GestionDemineurInterface {

    private Map<String,Plateau> logins;

    public GestionDemineur() {
        this.logins = new HashMap<>();
    }

    @Override
    public boolean decouvrir( String pseudo,int x, int y) throws BombeException {
        Plateau plateauJoueur = this.logins.get(pseudo);
        plateauJoueur.decouvrirCase(x, y);
        return plateauJoueur.partieGagnee();
    }

    @Override
    public Plateau getPlateau(String pseudo) {
        return this.logins.get(pseudo);
    }

    @Override
    public void associerNouvelleGrille(String pseudo) {
        Plateau lePlateau = new Plateau();
        lePlateau.initialiserPlateau();
        this.logins.put(pseudo,lePlateau);
    }

    @Override
    public void associerNouvelleGrille(String pseudo, int size, int pourcentageBombes) {
        Plateau lePlateau = new Plateau(size);
        lePlateau.initialiserPlateau(pourcentageBombes);
        this.logins.put(pseudo,lePlateau);
    }

    @Override
    public void connexion(String pseudo) throws ExceptionLoginDejaPris {
        if (this.logins.containsKey(pseudo)) {
            throw new ExceptionLoginDejaPris();
        }
        Plateau lePlateau = new Plateau();
        lePlateau.initialiserPlateau();
        this.logins.put(pseudo,lePlateau);
    }

    @Override
    public void deconnexion(String pseudo) {
        this.logins.remove(pseudo);
    }

}
