package Actions;

import facade.erreurs.RoleDejaAttribueException;
import facade.erreurs.UtilisateurDejaExistantException;

/**
 * Created by o2144945 on 07/12/16.
 */
public class ValidateRegister extends MonContexte{
    private long id;
    private String erreur;

    @Override
    public String execute() throws Exception {
        try{
            this.getMaFacade(). validerInscription(this.getIdentifiant(), this.getId());
            return SUCCESS;
        }
        catch (UtilisateurDejaExistantException e){
            this.setErreur("Utilisateur Deja Existant");

        }catch (RoleDejaAttribueException e){
            this.setErreur("Utilisateur Deja attribue");
        }
        return ERROR;
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
