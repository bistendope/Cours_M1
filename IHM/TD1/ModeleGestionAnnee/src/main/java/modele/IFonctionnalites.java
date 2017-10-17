package modele;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by YohanBoichut on 12/09/15.
 */
public interface IFonctionnalites {

    /**
     * Permet d'ajouter un étudiant en base
     * @param numeroEtudiant
     * @param nom
     * @param prenom
     * @param d : date de naissance
     */
    public Etudiant ajouterEtudiantBase(long numeroEtudiant, String nom, String prenom,Date d);

    /**
     * Permet d'ajouter un professeur en base. La gestion des identifiants est faite automatiquement
     * @param nom
     * @param prenom
     * @param d : date de naissance
     * @return l'instance du professeur créé
     */
    public Prof ajouterProfesseurBase(String nom, String prenom,Date d);

    /**
     * Permet d'ajouter l'étudiant numeroEtudiant à la filière identifiée par idFiliere
     * @param idFiliere
     * @param numeroEtudiant
     */
    public void ajouterEtudiantFiliere(String idFiliere, long numeroEtudiant);

    /**
     * Permet d'ajouter le professeur idProf en tant qu'intervenant dans la filière identifiée par idFiliere
     * @param idFiliere
     * @param idProf
     */
    public void ajouterProfesseurFiliere(String idFiliere, int idProf);

    /**
     * Permet de créer une filière idFilière et d'associer à cette filière un professeur responsable correspondant
     * à l'identifiant idProf.
     * @param idFiliere
     * @param idProf
     */
    public Annee creerFiliere(String idFiliere, int idProf);

    /**
     * Permet de récupérer la liste des étudiants inscrits dans la filière idFiliere
     * @param idFiliere
     * @return
     */
    public Collection<Etudiant> getEtudiantFromFiliere(String idFiliere);

    /**
     * Permet de récupérer le groupe idGroupe de la filière idFiliere
     * @param idFiliere
     * @param idGroupe
     * @return
     */
    public Groupe getGroupeTD(String idFiliere, int idGroupe);

    /**
     * Permet de récupérer tous les groupes de la filière idFiliere
     * @param idFiliere
     * @return
     */
    public List<Groupe> getGroupes(String idFiliere);

    /**
     * Permet de récupérer le responsable de la filière idFiliere
     * @param idFiliere
     * @return le professeur responsable de la filière concernée
     */
    public Prof getResponsable(String idFiliere);

    /**
     * Permet de récupérer l'objet métier Etudiant enregistré dans l'université correspondant au numéro numero
     * @param numeroEtudiant
     * @return l'étudiant de l'université dont le numéro d'étudiant est numeroEtudiant
     */
    public Etudiant getEtudiantFromBase(long numeroEtudiant);

    /**
     *
     * @return la liste des professeurs enregistrés au sein de l'université
     */
    public Collection<Prof> getProfsBase();

}
