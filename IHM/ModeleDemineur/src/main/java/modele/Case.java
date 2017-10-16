package modele;

/**
 * Created by YohanBoichut on 21/10/15.
 */
public interface Case {


    void incrementeVoisinage();

    boolean getCachee();

    /**
     * getValeur() retourne -1 s'il s'agit d'une bombe et un autre entier positif
     * représentant le voisinage.
     */
    int getValeur();

    Case devoilerCase();
}
