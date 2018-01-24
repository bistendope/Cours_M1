package actions;

import modele.Annee;


public class AjouterFormation extends Environment{
    private Annee filiere;
    private int responsable;
    private String libelle;

//    @Override
//    public void Validate() {
//        super.validate();
//        if (libelle == null || libelle.length() < 5){
//            addFieldError("libelle", getText(errors.libelle));
//        }
//    }

    @Override
    public String execute() throws Exception {
        super.execute();
        filiere = this.getFacade().creerFiliere(libelle, responsable);

        return SUCCESS;
    }

    public Annee getFiliere() {
        return filiere;
    }

    public void setFiliere(Annee filiere) {
        this.filiere = filiere;
    }

    public int getResponsable() {
        return responsable;
    }

    public void setResponsable(int responsable) {
        this.responsable = responsable;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}