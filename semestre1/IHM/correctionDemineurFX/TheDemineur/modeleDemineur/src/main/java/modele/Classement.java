package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public class Classement implements Serializable{



    List<Rank> classement;

    private static int TAILLECLASSEMENT = 10;



    public Classement() {
        classement = new ArrayList<>();
    }



    public boolean ajouterNouveauTemps(Joueur idJoueur, long temps) {
        int indice = 0;
        for (Rank r : this.classement) {
            if (r.getTempsRealise()*1000<temps) {
                indice++;
            }
        }


        if (indice<TAILLECLASSEMENT) {
            this.classement.add(indice,new Rank(idJoueur,temps));
            if (this.classement.size()>=TAILLECLASSEMENT) {
                this.classement.remove(TAILLECLASSEMENT);
            }
            return true;
        }
        return false;

    }

    public List<Rank> getClassement() {
        return classement;
    }



    public static List<String> classementToStringList(Classement c) {
        List<String> resultat = new ArrayList<>();
        int i =1;
        for(Rank r : c.getClassement()) {
            resultat.add(i+ " - "+r.toString());
            i++;
        }
        return resultat;
    }

}
