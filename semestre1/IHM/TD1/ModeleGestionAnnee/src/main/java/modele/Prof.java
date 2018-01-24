package modele;

import java.util.Date;

/**
 * Created by YohanBoichut on 11/09/15.
 */
public class Prof extends  Personne{



    private int id;


    private static int identifiant = 0;


    public Prof() {

    }

    public Prof(String nom, String prenom, Date dateDeNaissance) {
        super(nom,prenom,dateDeNaissance);
        this.id = identifiant;
        identifiant++;

    }

    public int getId() {
        return id;
    }

}
