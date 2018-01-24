package listener;

import java.util.EventObject;

/**
 * Created by YohanBoichut on 04/11/2016.
 */
public class InscriptionErrorEvent extends EventObject {

    String message;
    public InscriptionErrorEvent(Object source, String m) {
        super(source);
        this.message = m;
    }

    public String getMessage() {
        return message;
    }
}
