package Actions;

import com.opensymphony.xwork2.ActionSupport;
import facade.ConnexionService;
import facade.ServiceImpl;
import modele.inscription.InscriptionPotentielle;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.*;

public class MonContexte  extends ActionSupport implements ApplicationAware,SessionAware {
    static String MAFACADE = "maFacade";
    static String MONID = "monId";
    static String MONPSEUDO = "monPseudo";
    public static String ADMIN = "ADMIN";
    public static String VENDEUR = "VENDEUR";
    public static String RESPONSABLESTOCK = "RESPONSABLESTOCK";
    static List<String> roles = new ArrayList<String>();


    private ServiceImpl maFacade;
    Map<String,Object> mapSession;

    public List<String> getRoles() {
        return roles;
    }

    public void setApplication(Map<String, Object> map) {
        this.maFacade = (ServiceImpl) map.get(MAFACADE);
        if (this.maFacade == null)
        {
            this.maFacade = new ServiceImpl();
            map.put(MAFACADE, this.maFacade);
        }
    }

    public ServiceImpl getMaFacade() {
        return maFacade;
    }

    public Map<String, Object> getMapSession() {
        return mapSession;
    }

    public void setSession(Map<String, Object> map) {
        this.mapSession= map;
    }

    public long getIdentifiant() {
        return (Long) this.mapSession.get(MONID);
    }

    public List<String> getListeRole(){
        this.getRoles().clear();
        this.getRoles().add(ADMIN);
        this.getRoles().add(VENDEUR);
        this.getRoles().add(RESPONSABLESTOCK);
        return this.getRoles();
    }

    public boolean getEstAdmin(){
        return this.getMaFacade().estUnAdmin(this.getIdentifiant());
    }

    public boolean getEstVendeur(){
        return this.getMaFacade().estUnVendeur(this.getIdentifiant());
    }

    public boolean getEstRespon(){
        return this.getMaFacade().estResponsableDesStocks(this.getIdentifiant());
    }

    public Collection<InscriptionPotentielle> getListeDesDemandesNonTraitees(){
        return this.getMaFacade().getListeDesDemandesNonTraitees(this.getIdentifiant());
    }

}
