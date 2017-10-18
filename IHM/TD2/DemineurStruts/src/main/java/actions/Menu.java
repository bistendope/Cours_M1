package actions;
import com.opensymphony.xwork2.ActionContext;
import modele.Plateau;

import java.util.Map;

public class Menu extends Environment {
    private int percentBombes;
    private int taille;
    private Plateau plateau;
    private String pseudo;



    public String execute() throws Exception{
        super.execute();
        pseudo = (String) sessionMap.get("pseudo");
        if(percentBombes == 0 && taille == 0 ){
            facade.associerNouvelleGrille(pseudo);
        }else{
            facade.associerNouvelleGrille(pseudo, taille, percentBombes);
        }
        plateau = facade.getPlateau(pseudo);
        return SUCCESS;
    }


    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }


    public int getPercentBombes() {
        return percentBombes;
    }

    public void setPercentBombes(int percentBombes) {
        this.percentBombes = percentBombes;
    }



    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }
}
