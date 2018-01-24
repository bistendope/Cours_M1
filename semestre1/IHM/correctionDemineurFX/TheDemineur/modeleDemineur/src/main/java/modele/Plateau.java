package modele;

import modele.exceptions.BombeException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YohanBoichut on 21/10/15.
 */

public class Plateau implements Serializable {

    private Case[][] monPlateau;
    int nbCasesDevoilees = 0;
    public static int TAILLE=6;
    public static int NBBOMBES=2;

    public int getIdPlateau() {
        return idPlateau;
    }

    private int idPlateau;

    public Plateau() {
        this.monPlateau = new Case[TAILLE][TAILLE];
    }

    public Plateau(int id) {
        this.monPlateau = new Case[TAILLE][TAILLE];
        this.idPlateau = id;
    }






    private void miseAJourApresPosageDeMine(int x,int y) {
        if ((x-1)>=0) {
            for (int j = -1; j < 2; j++) {
                if ((y+j>=0) && (y+j<TAILLE)) {
                    this.monPlateau[x-1][j+y].incrementeVoisinage();
                }
            }
        }
        if (y-1>=0) {
            this.monPlateau[x][y-1].incrementeVoisinage();
        }

        if (y+1<TAILLE) {
            this.monPlateau[x][y+1].incrementeVoisinage();
        }


        if ((x+1)<TAILLE) {
            for (int j = -1; j < 2; j++) {
                if ((y+j>=0) && (y+j<TAILLE)) {
                    this.monPlateau[x+1][j+y].incrementeVoisinage();
                }
            }
        }

    }


    public Case[][] getMonPlateau() {
        return monPlateau;
    }



    public void initialiserPlateau() {

        for (int i=0;i<TAILLE;i++) {
            for (int j=0;j<TAILLE;j++) {
                this.monPlateau[i][j]= new CaseNonDecouverte(new CaseNormale(i,j));
            }
        }

        List<Bombe> dejaPosee = new ArrayList<Bombe>();

        for (int i=0;i<(NBBOMBES);i++) {
            int x,y;
            do {
                x = (int) (Math.random() * (TAILLE));
                y = (int) (Math.random() * (TAILLE));
            } while (this.monPlateau[x][y].getValeur()==-1);

            this.monPlateau[x][y] = new CaseNonDecouverte(new Bombe(x,y));
            this.miseAJourApresPosageDeMine(x,y);
        }

    }


    @Override
    public String toString() {
        String result="";
        for (int i=0;i<TAILLE;i++) {
            for (int j=0;j<TAILLE;j++) {
                if (this.monPlateau[i][j].getCachee()) {
                    result = result+"|"+false;
                }
                else {
                    result = result + "|" + this.monPlateau[i][j].getValeur();
                }
            }
            result+="\n";
        }

        return result;
    }




    private void propagation(int x, int y) {

        if (x>=0 && x<= (TAILLE-1) && y>=0 && y<= (TAILLE-1)) {
            if (this.monPlateau[x][y].getCachee() && this.monPlateau[x][y].getValeur()==0) {
                this.monPlateau[x][y] = this.monPlateau[x][y].devoilerCase();
                this.nbCasesDevoilees++;
                this.propagation(x - 1, y);
                this.propagation(x+1,y);
                this.propagation(x,y-1);
                this.propagation(x,y+1);
            }
        }
    }

    public void decouvrirCase(int x, int y) throws BombeException {
        if (this.monPlateau[x][y].getValeur() == -1) {
            this.monPlateau[x][y]= this.monPlateau[x][y].devoilerCase();
            throw new BombeException();
        }
        if (this.monPlateau[x][y].getValeur() == 0) {
            this.propagation(x,y);
        }

        if (this.monPlateau[x][y].getValeur()>0) {
            this.monPlateau[x][y] = this.monPlateau[x][y].devoilerCase();
            this.nbCasesDevoilees++;
        }
    }



    public boolean partieGagnee() {
        return ((TAILLE*TAILLE)-this.nbCasesDevoilees)== NBBOMBES;
    }

    public Plateau copie() {
        Plateau plateau = new Plateau(this.idPlateau);
        for (int i=0; i<TAILLE*TAILLE;i++) {
            plateau.monPlateau[i/TAILLE][i%TAILLE]=this.monPlateau[i/TAILLE][i%TAILLE];
        }
        return plateau;
    }
}
