package Actions;

import com.opensymphony.xwork2.ActionSupport;
import facade.erreurs.CoupleUtilisateurMDPInconnuException;
import modele.personnes.Utilisateur;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class Login extends MonContexte  {

    private String username;
    private String password;

    @Override
    public String execute() throws Exception {
        try {
            Utilisateur u = this.getMaFacade().connexion(username, password);;
            this.getMapSession().put(MONID,u.getIdentifiant());
            this.getMapSession().put(MONPSEUDO,username);
            return SUCCESS;
        }
        catch (CoupleUtilisateurMDPInconnuException e){
            addFieldError("password",getText("login.error1"));
            return INPUT;
        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
