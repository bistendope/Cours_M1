package mesActions;

import modele.exceptions.ExceptionLoginDejaPris;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public class Inscription extends MonEnvironnementCommun {

    private String username;
    private String password;
    private String confirmationMDP;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setConfirmationMDP(String confirmationMDP) {
        this.confirmationMDP = confirmationMDP;
    }

    public String getConfirmationMDP() {
        return confirmationMDP;
    }

    @Override
    public String execute() throws Exception {
        int id = 0;
        try {
            id = this.getMaFacade().inscription(username,password);

        } catch (ExceptionLoginDejaPris exceptionLoginDejaPris) {
            addFieldError("username",getText("Erreur.pseudopris"));
            return INPUT;
        }
        this.getMapSession().put(MONID,id);
        return SUCCESS;

    }

    @Override
    public void validate() {
        super.validate();
        if (!password.equals(confirmationMDP)) {
            addFieldError("confirmationMDP",getText("confirmationdifferente"));
        }
    }
}
