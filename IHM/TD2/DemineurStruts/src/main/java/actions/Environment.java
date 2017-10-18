package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import modele.GestionDemineur;
import modele.GestionDemineurInterface;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class Environment extends ActionSupport implements ApplicationAware, SessionAware {

    protected Map sessionMap;
    private Map<String,Object> applicationScope;
    protected GestionDemineurInterface facade;
    private  final static String FACADE = "facade";



    public String execute() throws Exception{
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

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = map;
    }
}
