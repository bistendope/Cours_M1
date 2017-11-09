package listener;

import java.util.EventListener;

public interface InscriptionErrorListener extends EventListener {
    public void errorDetected(InscriptionErrorEvent event);
}
