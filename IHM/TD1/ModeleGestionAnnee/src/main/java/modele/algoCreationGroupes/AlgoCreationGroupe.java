package modele.algoCreationGroupes;

import modele.Groupe;
import modele.Promotion;

import java.util.List;

/**
 * Created by YohanBoichut on 12/09/15.
 */
public interface AlgoCreationGroupe {


    void creationGroupes(Promotion promo, List<Groupe> tds);
}
