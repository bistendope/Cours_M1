package listener;

import javafx.event.Event;

import java.util.EventObject;

/**
 * Created by YohanBoichut on 04/11/2016.
 */
public class ConnexionErrorEvent extends EventObject {

    String message;
    public ConnexionErrorEvent(Object source,String m) {
        super(source);
        this.message = m;
    }

    public String getMessage() {
        return message;
    }
}
