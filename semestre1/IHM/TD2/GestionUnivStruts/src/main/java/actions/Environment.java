package actions;

import com.opensymphony.xwork2.ActionSupport;
import modele.FonctionnalitesStaticVersion;
import modele.IFonctionnalites;
import modele.Prof;
import org.apache.struts2.interceptor.ApplicationAware;

import java.util.Collection;
import java.util.Map;

public class Environment extends ActionSupport implements ApplicationAware {

    private Map<String,Object> applicationScope;
    private IFonctionnalites facade;
    private  final static String FACADE = "facade";



    public String execute() throws Exception{
        //récupération des valeurs saisies dans le formulaire
        //youpi! grace aux setter/getter

        //envoi au modele

        facade = (IFonctionnalites) applicationScope.get(FACADE);

        if(facade == null){
            facade = new FonctionnalitesStaticVersion();
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

    public IFonctionnalites getFacade() {
        return facade;
    }

    public void setFacade(IFonctionnalites facade) {
        this.facade = facade;
    }
}
