package modele;

import modele.algoCreationGroupes.AlgoClassique;
import modele.algoCreationGroupes.AlgoCreationGroupe;
import modele.comportementAjout.ComportementAjout;
import modele.comportementAjout.ComportementClassique;
import modele.comportementAjout.ComportementExtraTemporae;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by YohanBoichut on 11/09/15.
 */
public class Annee {


    private String libelle;
    private Prof responsable;
    private Promotion promo;
    private List<Groupe> tds;
    private Set<Prof> intervenants;

    private ComportementAjout ajouter = new ComportementClassique();



    private AlgoCreationGroupe algo = new AlgoClassique();

    public Annee(String libelle, Prof responsable) {
        this.libelle = libelle;
        this.responsable = responsable;
        this.promo = new Promotion();
        intervenants = new HashSet<Prof>();
    }


    public void ajouterEtudiant(Etudiant e) {
        this.ajouter.ajouterEtudiant(e,this.promo,this.tds);
    }


    public void creationGroupes() {
        this.ajouter = new ComportementExtraTemporae();
        this.tds = new ArrayList<Groupe>();
        this.algo.creationGroupes(this.promo,this.tds);
    }







    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Prof getResponsable() {
        return responsable;
    }

    public void setResponsable(Prof responsable) {
        this.responsable = responsable;
    }

    public Promotion getPromo() {
        return promo;
    }

    public void setPromo(Promotion promo) {
        this.promo = promo;
    }

    public List<Groupe> getTds() {
        return tds;
    }

    public void setTds(List<Groupe> tds) {
        this.tds = tds;
    }

    public Set<Prof> getIntervenants() {
        return intervenants;
    }

    public void setIntervenants(Set<Prof> intervenants) {
        this.intervenants = intervenants;
    }

    public void ajouterProf(Prof prof) {
        this.intervenants.add(prof);
    }
}
