package controleur.erreurs;

/**
 * Created by YohanBoichut on 18/11/2016.
 */
public class LoginDejaPris extends Exception {
    public LoginDejaPris(String s) {
        super(s);
    }
}
