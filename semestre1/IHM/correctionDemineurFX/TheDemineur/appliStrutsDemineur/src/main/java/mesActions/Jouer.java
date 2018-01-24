package mesActions;

import modele.Classement;
import modele.exceptions.BombeException;
import modele.exceptions.ExceptionTriche;

/**
 * Created by YohanBoichut on 06/10/2016.
 */
public class Jouer  extends MonEnvironnementCommun {


    private int hiddenX;
    private int hiddenY;


    public int getHiddenX() {
        return hiddenX;
    }

    public void setHiddenX(int hiddenX) {
        this.hiddenX = hiddenX;
    }

    public int getHiddenY() {
        return hiddenY;
    }

    public void setHiddenY(int hiddenY) {
        this.hiddenY = hiddenY;
    }



    public Classement getClassement() {
        return this.getMaFacade().getClassementPartie(this.getMaFacade().getPlateau(getIdentifiant()).getIdPlateau());
    }


    @Override
    public String execute() throws Exception {

        System.out.println(this.getPseudo()+" - "+this.hiddenX + " | "+ this.hiddenY);

        boolean gagne = false;
        try {
            gagne = this.getMaFacade().decouvrir(this.getIdentifiant(), hiddenX, hiddenY);

        } catch (BombeException e) {
            this.getMaFacade().resetPartie(this.getIdentifiant());
            return PERDU;
        }
        getMapSession().put("t1", System.currentTimeMillis());
        if (gagne) {
            long monTemps = ((Long) getMapSession().get("t1"))-((Long) getMapSession().get("t0"));
            try {
                this.getMaFacade().updateTemps(this.getIdentifiant(),monTemps);
            } catch (ExceptionTriche exceptionTriche) {
                return SUCCESS;
            }
            return GAGNE;
            }
            else {
                return SUCCESS;
            }

    }
}
