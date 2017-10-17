package Actions;

/**
 * Created by o2144945 on 07/12/16.
 */
public class Logout extends MonContexte {
    @Override
    public String execute() throws Exception {
        this.getMaFacade().deconnexion(this.getIdentifiant());
        this.getMapSession().remove(MONID);

        return SUCCESS;
    }
}
