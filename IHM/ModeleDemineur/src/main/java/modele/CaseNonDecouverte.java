package modele;

/**
 * Created by YohanBoichut on 21/10/15.
 */
public class CaseNonDecouverte implements Case {


    private Case caseCachee;


    public CaseNonDecouverte(Case caseCachee) {
        this.caseCachee = caseCachee;
    }

    @Override
    public void incrementeVoisinage() {
        this.caseCachee.incrementeVoisinage();
    }

    @Override
    public boolean getCachee() {
        return true;
    }

    @Override
    public int getValeur() {
        return this.caseCachee.getValeur();
    }

    @Override
    public Case devoilerCase() {
        return this.caseCachee;
    }


}
