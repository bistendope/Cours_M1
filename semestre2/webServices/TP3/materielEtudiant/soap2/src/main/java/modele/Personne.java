package modele;

import java.util.Collection;
import java.util.Date;

public class Personne {
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private Collection<Contact> contacts;
    public Personne() {
    }

    public Personne(String nom, String prenom, Date dateNaissance) {

        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Collection<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<Contact> contacts) {
        this.contacts = contacts;
    }


    public void addContact(Contact tel) {

    }
}