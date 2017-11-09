package modele;

/**
 * Created by YohanBoichut on 21/10/15.
 */
public class CaseNormale implements Case {

    private int x;
    private int y;
    private int voisinage;

    public CaseNormale(int x, int y) {
        this.x = x;
        this.y = y;
        this.voisinage =0;
    }

    @Override
    public void incrementeVoisinage() {
        this.voisinage++;
    }

    @Override
    public boolean getCachee() {
        return false;
    }

    @Override
    public int getValeur() {
        return this.voisinage;
    }
    @Override
    public Case devoilerCase() {
        return this;
    }
}
