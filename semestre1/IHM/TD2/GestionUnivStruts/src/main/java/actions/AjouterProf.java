package actions;

import com.opensymphony.xwork2.ActionSupport;
import modele.FonctionnalitesStaticVersion;
import modele.IFonctionnalites;
import modele.Prof;
import org.apache.struts2.interceptor.ApplicationAware;

import java.util.Date;
import java.util.Map;

public class AjouterProf extends ActionSupport implements ApplicationAware{
    private String nomProf;
    private String prenomProf;
    private Date dateDeNaissanceProf;
    private Prof prof;



    private Map<String,Object> applicationScope;

    @Override
    public String execute() throws Exception{
        //récupération des valeurs saisies dans le formulaire
        //youpi! grace aux setter/getter

        //envoi au modele

        IFonctionnalites facade = (IFonctionnalites) applicationScope.get("facade");

        if(facade== null){
            facade = new FonctionnalitesStaticVersion();
            applicationScope.put("facade", facade);
        }

        prof = facade.ajouterProfesseurBase(nomProf, prenomProf, dateDeNaissanceProf);


        return SUCCESS;
    }

    public void setApplication(Map<String, Object> map) {
        applicationScope=map;
    }

    public void setNomProf(String nomProf) {
        this.nomProf = nomProf;
    }

    public String getPrenomProf() {
        return prenomProf;
    }

    public void setPrenomProf(String prenomProf) {
        this.prenomProf = prenomProf;
    }

    public String getNomProf() {
        return nomProf;
    }

    public Date getDateDeNaissanceProf() {
        return dateDeNaissanceProf;
    }

    public void setDateDeNaissanceProf(Date dateDeNaissanceProf) {
        this.dateDeNaissanceProf = dateDeNaissanceProf;
    }
    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }


}
