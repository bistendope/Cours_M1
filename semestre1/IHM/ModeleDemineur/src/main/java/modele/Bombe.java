package modele;

/**
 * Created by YohanBoichut on 21/10/15.
 */
public class Bombe implements Case {

    private int x;
    private int y;

    public Bombe(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void incrementeVoisinage() {

    }

    @Override
    public boolean getCachee() {
        return false;
    }

    @Override
    public int getValeur() {
        return -1;
    }

    @Override
    public Case devoilerCase() {
        return this;
    }
}
