package modele.algoCreationGroupes;

import modele.Etudiant;
import modele.Groupe;
import modele.Promotion;

import java.util.List;

/**
 * Created by YohanBoichut on 12/09/15.
 */
public class AlgoClassique implements  AlgoCreationGroupe{


    @Override
    public void creationGroupes(Promotion promo, List<Groupe> tds) {
        if (promo.getListeEtudiants().size() > 80) {
            tds.add(new Groupe());
            tds.add(new Groupe());
            tds.add(new Groupe());
        }

        if (promo.getListeEtudiants().size()>40 && promo.getListeEtudiants().size()<=80) {
            tds.add(new Groupe());
            tds.add(new Groupe());

        }

        if (promo.getListeEtudiants().size()<=40) {
            tds.add(new Groupe());
        }


        int indice = 0;
        for (Etudiant e : promo.getListeEtudiants()) {
            (tds.get(indice%(tds.size()))).ajouterEtudiant(e);
            indice++;
        }
    }
}
