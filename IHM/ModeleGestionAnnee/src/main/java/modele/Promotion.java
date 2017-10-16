package modele;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YohanBoichut on 11/09/15.
 */
public class Promotion {

    private Map<Long,Etudiant> promo;



    public Promotion() {
        this.promo = new HashMap<Long, Etudiant>();
    }



    public void ajouterEtudiant(Etudiant e) {

        this.promo.put(e.getNumeroEtudiant(),e);
        e.setMaPromo(this);
    }


    public void supprimerEtudiant(Etudiant e) {
        this.promo.remove(e.getNumeroEtudiant());
    }


    public Etudiant getEtudiant(long id) {
        return this.promo.get(id);
    }

    public boolean appartient(Etudiant e) {
        return this.promo.containsKey(e.getNumeroEtudiant());
    }



    public Collection<Etudiant> getListeEtudiants() {
        return this.promo.values();
    }

}
