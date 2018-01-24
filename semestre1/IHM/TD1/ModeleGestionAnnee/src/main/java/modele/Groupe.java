package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by YohanBoichut on 11/09/15.
 */
public class Groupe {


    private List<Etudiant> etudiants;

    public Groupe() {
        this.etudiants = new ArrayList<Etudiant>();
    }



    public void ajouterEtudiant(Etudiant e) {
        this.etudiants.add(e);
    }

    public void supprimerEtudiant(Etudiant e) {
        this.etudiants.remove(e);
    }


    public boolean appartient(Etudiant e) {
        return this.etudiants.contains(e);
    }


    public Collection<Etudiant> getListeEtudiant() {
        return this.etudiants;
    }

}
