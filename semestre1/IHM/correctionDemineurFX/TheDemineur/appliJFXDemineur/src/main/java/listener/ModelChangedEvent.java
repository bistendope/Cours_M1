package listener;

import modele.Plateau;

import java.util.EventObject;

public class ModelChangedEvent extends EventObject {


    public ModelChangedEvent(Object source) {
        super(source);
    }



}
