package mesActions;

/**
 * Created by YohanBoichut on 07/10/2016.
 */
public class Deconnexion extends MonEnvironnementCommun {


    @Override
    public String execute() throws Exception {
        this.getMaFacade().deconnexion(this.getIdentifiant());
        this.getMapSession().remove(MONID);

        return SUCCESS;
    }
}
