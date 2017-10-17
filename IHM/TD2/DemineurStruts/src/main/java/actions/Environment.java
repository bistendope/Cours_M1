package actions;

import com.opensymphony.xwork2.ActionSupport;
import modele.GestionDemineur;
import modele.GestionDemineurInterface;
import org.apache.struts2.interceptor.ApplicationAware;
import java.util.Map;

public class Environment extends ActionSupport implements ApplicationAware {

    private Map<String,Object> applicationScope;
    protected GestionDemineurInterface facade;
    private  final static String FACADE = "facade";



    public String execute() throws Exception{
        //récupération des valeurs saisies dans le formulaire
        //youpi! grace aux setter/getter

        //envoi au modele

        facade = (GestionDemineurInterface) applicationScope.get(FACADE);

        if(facade == null){
            facade = new GestionDemineur();
            applicationScope.put(FACADE, facade);
        }
        return SUCCESS;
    }

    public Map<String, Object> getApplicationScope() {
        return applicationScope;
    }

    public void setApplicationScope(Map<String, Object> applicationScope) {
        this.applicationScope = applicationScope;
    }

    @Override
    public void setApplication(Map<String, Object> map) {
        applicationScope=map;
    }

    public GestionDemineurInterface getFacade() {
        return facade;
    }

    public void setFacade(GestionDemineurInterface facade) {
        this.facade = facade;
    }
}
