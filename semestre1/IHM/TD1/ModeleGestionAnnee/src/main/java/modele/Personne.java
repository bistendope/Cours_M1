package modele;

import java.util.Date;

/**
 * Created by YohanBoichut on 11/09/15.
 */
public abstract class Personne {

    private String nom;
    private String prenom;
    private Date dateDeNaissance;

    protected Personne(){

    }
    protected Personne(String nom, String prenom, Date dateDeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }


}
