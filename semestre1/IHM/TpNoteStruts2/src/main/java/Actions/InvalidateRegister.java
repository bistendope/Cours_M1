package Actions;

import facade.erreurs.RoleDejaAttribueException;
import facade.erreurs.UtilisateurDejaExistantException;

/**
 * Created by o2144945 on 07/12/16.
 */
public class InvalidateRegister extends MonContexte{
    private long id;
    private String erreur;

    @Override
    public String execute() throws Exception {
            this.getMaFacade().refuserInscription(this.getIdentifiant(), this.getId());
            return SUCCESS;
    }

    public long getId() {
        return id;
    }

    public void setId(long identifiant) {
        this.id = identifiant;
    }

    public String getErreur() {
        return erreur;
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
    }
}
