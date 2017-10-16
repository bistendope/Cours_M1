package modele;

import java.util.*;

/**
 * Created by YohanBoichut on 12/09/15.
 */
public class FonctionnalitesStaticVersion implements IFonctionnalites {

    private Map<Integer,Prof> profsUniversite;
    private Map<Long,Etudiant> etudiantsUniversite;
    private Map<String,Annee> filieres;




    public FonctionnalitesStaticVersion() {

        profsUniversite = new HashMap<Integer, Prof>();
        etudiantsUniversite = new HashMap<Long, Etudiant>();
        filieres = new HashMap<String, Annee>();
    }







    @Override
    public Etudiant ajouterEtudiantBase(long numeroEtudiant, String nom, String prenom, Date d) {
        Etudiant etudiant = new Etudiant(nom,prenom,d,numeroEtudiant);
        this.etudiantsUniversite.put(numeroEtudiant, etudiant);
        return etudiant;
    }

    @Override
    public Prof ajouterProfesseurBase(String nom, String prenom, Date d) {
        Prof p = new Prof(nom,prenom,d);
        this.profsUniversite.put(p.getId(),p);
        return p;
    }

    @Override
    public void ajouterEtudiantFiliere(String idFiliere, long numeroEtudiant) {
        Annee annee = this.filieres.get(idFiliere);
        annee.ajouterEtudiant(etudiantsUniversite.get(numeroEtudiant));
    }

    @Override
    public void ajouterProfesseurFiliere(String idFiliere, int idProf) {
        Annee annee = this.filieres.get(idFiliere);
        annee.ajouterProf(profsUniversite.get(idProf));
    }

    @Override
    public Annee creerFiliere(String idFiliere, int idProf) {

        Prof prof = this.profsUniversite.get(idProf);
        Annee annee = new Annee(idFiliere,prof);
        this.filieres.put(idFiliere,annee);
        return annee;
    }

    @Override
    public Collection<Etudiant> getEtudiantFromFiliere(String idFiliere) {
        return this.filieres.get(idFiliere).getPromo().getListeEtudiants();
    }

    @Override
    public Groupe getGroupeTD(String idFiliere, int idGroupe) {

        return this.filieres.get(idFiliere).getTds().get(idGroupe);

    }

    @Override
    public List<Groupe> getGroupes(String idFiliere) {
        return this.filieres.get(idFiliere).getTds();
    }

    @Override
    public Prof getResponsable(String idFiliere) {
        return this.filieres.get(idFiliere).getResponsable();
    }

    @Override
    public Etudiant getEtudiantFromBase(long numero) {
        return this.etudiantsUniversite.get(numero);
    }

    @Override
    public Collection<Prof> getProfsBase() {
        return this.profsUniversite.values();
    }
}
