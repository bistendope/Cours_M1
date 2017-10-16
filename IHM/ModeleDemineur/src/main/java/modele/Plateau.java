package modele;

import modele.exceptions.BombeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YohanBoichut on 21/10/15.
 */

public class Plateau {
    private static final int DEFAULT_POURCENTAGE_BOMBES = 33;
    private static int DEFAULT_SIZE = 6;
    private Case[][] monPlateau;
    private int taille;
    private int nbCasesDevoilees = 0;
    private int nbBombes;

    public Plateau() {
        this(DEFAULT_SIZE);
    }

    public Plateau(int taille) {
        monPlateau = new Case[taille][taille];
        this.taille = taille;
    }

    private void miseAJourApresPosageDeMine(int x, int y) {
        if ((x - 1) >= 0) {
            for (int j = -1; j < 2; j++) {
                if ((y + j >= 0) && (y + j < taille)) {
                    this.monPlateau[x - 1][j + y].incrementeVoisinage();
                }
            }
        }
        if (y - 1 >= 0) {
            this.monPlateau[x][y - 1].incrementeVoisinage();
        }

        if (y + 1 < taille) {
            this.monPlateau[x][y + 1].incrementeVoisinage();
        }


        if ((x + 1) < taille) {
            for (int j = -1; j < 2; j++) {
                if ((y + j >= 0) && (y + j < taille)) {
                    this.monPlateau[x + 1][j + y].incrementeVoisinage();
                }
            }
        }

    }


    public Case[][] getMonPlateau() {
        return monPlateau;
    }

    public void initialiserPlateau() {
        initialiserPlateau(DEFAULT_POURCENTAGE_BOMBES);
    }
    public void initialiserPlateau(int pourcentageBombe) {

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                this.monPlateau[i][j] = new CaseNonDecouverte(new CaseNormale(i, j));
            }
        }

        List<Bombe> dejaPosee = new ArrayList<Bombe>();

        nbBombes = (taille * taille * pourcentageBombe) / 100;
        for (int i = 0; i < nbBombes; i++) {
            int x, y;
            do {
                x = (int) (Math.random() * (taille));
                y = (int) (Math.random() * (taille));
            } while (this.monPlateau[x][y].getValeur() == -1);

            this.monPlateau[x][y] = new CaseNonDecouverte(new Bombe(x, y));
            this.miseAJourApresPosageDeMine(x, y);
        }

    }


    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (this.monPlateau[i][j].getCachee()) {
                    result = result + "|" + false;
                } else {
                    result = result + "|" + this.monPlateau[i][j].getValeur();
                }
            }
            result += "\n";
        }

        return result;
    }


    private void propagation(int x, int y) {

        if (x >= 0 && x <= (taille-1) && y >= 0 && y <= (taille-1)) {
            if (this.monPlateau[x][y].getCachee() && this.monPlateau[x][y].getValeur() == 0) {
                this.monPlateau[x][y] = this.monPlateau[x][y].devoilerCase();
                this.nbCasesDevoilees++;
                this.propagation(x - 1, y);
                this.propagation(x + 1, y);
                this.propagation(x, y - 1);
                this.propagation(x, y + 1);
            }
        }
    }

    public void decouvrirCase(int x, int y) throws BombeException {
        if (this.monPlateau[x][y].getValeur() == -1) {
            this.monPlateau[x][y] = this.monPlateau[x][y].devoilerCase();
            throw new BombeException();
        }
        if (this.monPlateau[x][y].getValeur() == 0) {
            this.propagation(x, y);
        }

        if (this.monPlateau[x][y].getValeur() > 0) {
            this.monPlateau[x][y] = this.monPlateau[x][y].devoilerCase();
            this.nbCasesDevoilees++;
        }
    }


    public boolean partieGagnee() {
        return (taille*taille - this.nbCasesDevoilees) == nbBombes;
    }


}
