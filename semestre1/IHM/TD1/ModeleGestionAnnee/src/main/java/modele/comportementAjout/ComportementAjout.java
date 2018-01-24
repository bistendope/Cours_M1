package modele.comportementAjout;

import modele.Etudiant;
import modele.Groupe;
import modele.Promotion;

import java.util.List;

/**
 * Created by YohanBoichut on 11/09/15.
 */
public interface ComportementAjout {


    void ajouterEtudiant(Etudiant e, Promotion p, List<Groupe> l);

}
