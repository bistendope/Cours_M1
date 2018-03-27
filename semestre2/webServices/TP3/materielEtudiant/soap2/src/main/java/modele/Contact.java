package modele;

public class Contact {
    public Contact(String type, String adresse, Personne personne) {
        this.type = type;
        this.adresse = adresse;
        this.personne = personne;
    }

    private String type;
    private String adresse;
    private Personne personne;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }


}
