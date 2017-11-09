package mesActions;

import modele.Classement;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public class ChoixPartie extends MonEnvironnementCommun {
    public int getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(int idPartie) {
        this.idPartie = idPartie;
    }

    int idPartie;


    public Classement getClassement() {
        return this.getMaFacade().getClassementPartie(idPartie);
    }


    @Override
    public String execute() throws Exception {
        this.getMaFacade().associerNouvelleGrille(this.getIdentifiant(),idPartie);
        this.getMapSession().put("t0", System.currentTimeMillis());
        return SUCCESS;
    }
}
