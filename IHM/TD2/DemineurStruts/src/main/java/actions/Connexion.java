package actions;



import com.opensymphony.xwork2.ActionContext;

import java.util.Map;

public class Connexion extends Environment {
    private String pseudo;


    public String execute() throws Exception{
        super.execute();
        facade.connexion(pseudo);
        Map session = ActionContext.getContext().getSession();
        sessionMap.put("pseudo",pseudo);
        return SUCCESS;
    }


    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
