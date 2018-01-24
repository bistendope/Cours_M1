package modele;

import java.util.Date;

/**
 * Created by YohanBoichut on 11/09/15.
 */
public class Etudiant extends Personne {

    private long numeroEtudiant;
    private Promotion maPromo;

    public Promotion getMaPromo() {
        return maPromo;
    }

    public void setMaPromo(Promotion maPromo) {
        this.maPromo = maPromo;
    }


    public Etudiant() {
        super();
    }
    public Etudiant(String nom, String prenom, Date dateDeNaissance, long numeroEtudiant) {
        super(nom,prenom,dateDeNaissance);
        this.numeroEtudiant = numeroEtudiant;
        this.maPromo = null;
    }



    public long getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(long numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

}
