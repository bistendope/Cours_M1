package actions;

import modele.Plateau;

public class Connexion extends Environment {
    private String pseudo;
    private Plateau plateau;


    public String execute() throws Exception{
        super.execute();
        facade.connexion(pseudo);
        facade.associerNouvelleGrille(pseudo);
        plateau = facade.getPlateau(pseudo);
        return SUCCESS;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
