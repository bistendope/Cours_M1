package modele.comportementAjout;

import modele.Etudiant;
import modele.Groupe;
import modele.Promotion;

import java.util.List;

/**
 * Created by YohanBoichut on 11/09/15.
 */
public class ComportementExtraTemporae implements ComportementAjout {
    @Override
    public void ajouterEtudiant(Etudiant e, Promotion p, List<Groupe> l) {
        p.ajouterEtudiant(e);

        int effectif =p.getListeEtudiants().size()+1;
        Groupe groupeElu = null;
        for (Groupe g : l) {
            if (g.getListeEtudiant().size() < effectif) {
                effectif = g.getListeEtudiant().size();
                groupeElu = g;
            }
        }

        groupeElu.ajouterEtudiant(e);

    }
}
