package modele;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public class PartiesReferencees {


    private static int NBPARTIESREFERENCES  = 3;

    private PartiesReferencees() {
        this.classemenPartiesReferences = new HashMap<>();
        this.partiesReferences = new HashMap<>();
    }



    private static PartiesReferencees instance = null;


    public static PartiesReferencees getInstance() {
        if (instance == null) {
            instance = new PartiesReferencees();
            instance.initialiser();
        }
        return instance;
    }


    private void initialiser() {
        for (int i=0;i<NBPARTIESREFERENCES;i++) {
            Plateau plateau = new Plateau(i);
            plateau.initialiserPlateau();
            this.partiesReferences.put(i,plateau);
            this.classemenPartiesReferences.put(i,new Classement());
        }

    }


    public Classement getClassementPartie(int idPartie) {
        return this.classemenPartiesReferences.get(idPartie);
    }


    public Collection<Integer> getIdentifiantsParties() {
        return this.partiesReferences.keySet();
    }


    public boolean updateClassement(Joueur joueur, int idPartie, long temps) {
        return this.classemenPartiesReferences.get(idPartie).ajouterNouveauTemps(joueur,temps);
    }

    public Plateau creerPartie(int indice) {
        return this.partiesReferences.get(indice).copie();
    }



    private  Map<Integer,Plateau> partiesReferences;
    private  Map<Integer,Classement> classemenPartiesReferences;




}
