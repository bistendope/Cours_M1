package actions;

import modele.Plateau;

public class Jouer extends Environment {
    private int xplayed;
    private int yplayed;
    private String pseudo;
    private boolean gagne;
    private Plateau plateau;

    public String execute() throws Exception{
        super.execute();
        pseudo = (String) sessionMap.get("pseudo");
        try{
            gagne = facade.decouvrir(pseudo, xplayed,  yplayed);
            if (gagne) return "gagne";
            plateau=facade.getPlateau(pseudo);

        }catch(Exception e){
            return "perdu";
        }
        return SUCCESS;
    }

    public int getXplayed() {
        return xplayed;
    }

    public void setXplayed(int xplayed) {
        this.xplayed = xplayed;
    }

    public int getYplayed() {
        return yplayed;
    }

    public void setYplayed(int yplayed) {
        this.yplayed = yplayed;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }




    public boolean isGagne() {
        return gagne;
    }

    public void setGagne(boolean gagne) {
        this.gagne = gagne;
    }
}
