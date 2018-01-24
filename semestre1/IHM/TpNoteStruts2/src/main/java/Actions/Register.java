package Actions;

/**
 * Created by o2144945 on 07/12/16.
 */
public class Register extends MonContexte{
    private String username;
    private String password;
    private String confirmationMDP;
    private String role;



    @Override
    public String execute() throws Exception {
        if (this.getMaFacade().estUnUtilisateurConnu(username)){
            addFieldError("username",getText("register.existingUser"));
            return INPUT;

        } else {
            this.getMaFacade().posterDemandeInscription(username, password, role);
            return SUCCESS;
        }

    }

    @Override
    public void validate() {
        super.validate();
        if (!password.equals(confirmationMDP)) {
            addFieldError("confirmationMDP",getText("register.errorPass"));
        }
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
