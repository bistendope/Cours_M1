package mesActions;

import com.opensymphony.xwork2.ActionSupport;
import modele.Case;
import modele.GestionDemineur;
import modele.GestionDemineurEvolue;
import modele.GestionDemineurEvolueInterface;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Collection;
import java.util.Map;

/**
 * Created by YohanBoichut on 06/10/2016.
 */
public class MonEnvironnementCommun extends ActionSupport implements ApplicationAware,SessionAware{


    static String MAFACADE = "maFacade";
    static String MONPSEUDO = "monPseudo";
    static String PERDU= "perdu";
    static String GAGNE = "gagne";
    static String MONID = "monId";

    GestionDemineurEvolueInterface maFacade;
    Map<String,Object> mapSession;


    public void setApplication(Map<String, Object> map) {
        this.maFacade = (GestionDemineurEvolueInterface)map.get(MAFACADE);
        if (this.maFacade == null) {
            this.maFacade = GestionDemineurEvolue.getInstance();
            map.put(MAFACADE,maFacade);
        }
    }


    public GestionDemineurEvolueInterface getMaFacade() {
        return maFacade;
    }


    public Map<String, Object> getMapSession() {
        return mapSession;
    }

    public void setSession(Map<String, Object> map) {
        this.mapSession= map;
    }


    public String getPseudo() {
        return this.getMaFacade().getJoueurById(getIdentifiant()).getPseudoJoueur();
    }

    public int getIdentifiant() {
        return (Integer)this.mapSession.get(MONID);
    }


    public Collection<Integer> getIdentifiantsParties() {
        return getMaFacade().getListeIdentifiantParties();
    }


    public Case[][] getPlateauJoueurCourant() {
        return maFacade.getPlateau(getIdentifiant()).getMonPlateau();
    }
}
