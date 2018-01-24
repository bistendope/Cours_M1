package mesActions;

import modele.Classement;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public class VoirClassement extends MonEnvironnementCommun {
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

}
