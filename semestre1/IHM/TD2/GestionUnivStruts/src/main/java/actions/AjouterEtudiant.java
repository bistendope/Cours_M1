package actions;

import com.opensymphony.xwork2.ActionSupport;
import modele.Etudiant;
import modele.FonctionnalitesStaticVersion;
import modele.IFonctionnalites;
import org.apache.struts2.interceptor.ApplicationAware;

import java.util.Date;
import java.util.Map;

public class AjouterEtudiant extends ActionSupport implements ApplicationAware{
    private long numEtudiant;
    private String nomEtu;
    private String prenomEtu;
    private Date dateDeNaissanceEtu;
    private Etudiant etudiant;



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

        etudiant = facade.ajouterEtudiantBase(numEtudiant, nomEtu, prenomEtu, dateDeNaissanceEtu);


        return SUCCESS;
    }

    public void setApplication(Map<String, Object> map) {
        applicationScope=map;
    }

    public long getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(long numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public String getNomEtu() {
        return nomEtu;
    }

    public void setNomEtu(String nomEtu) {
        this.nomEtu = nomEtu;
    }

    public String getPrenomEtu() {
        return prenomEtu;
    }

    public void setPrenomEtu(String prenomEtu) {
        this.prenomEtu = prenomEtu;
    }

    public Date getDateDeNaissanceEtu() {
        return dateDeNaissanceEtu;
    }

    public void setDateDeNaissanceEtu(Date dateDeNaissanceEtu) {
        this.dateDeNaissanceEtu = dateDeNaissanceEtu;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Map<String, Object> getApplicationScope() {
        return applicationScope;
    }

    public void setApplicationScope(Map<String, Object> applicationScope) {
        this.applicationScope = applicationScope;
    }




}
