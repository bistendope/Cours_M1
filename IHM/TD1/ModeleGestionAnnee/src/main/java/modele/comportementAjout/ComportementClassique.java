package modele.comportementAjout;

import modele.Etudiant;
import modele.Groupe;
import modele.Promotion;

import java.util.List;

/**
 * Created by YohanBoichut on 11/09/15.
 */
public class ComportementClassique implements ComportementAjout {

    @Override
    public void ajouterEtudiant(Etudiant e, Promotion p, List<Groupe> l) {
        p.ajouterEtudiant(e);
    }
}
