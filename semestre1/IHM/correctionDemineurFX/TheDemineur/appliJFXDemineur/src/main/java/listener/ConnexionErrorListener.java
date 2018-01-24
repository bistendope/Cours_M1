package listener;

import java.util.EventListener;

public interface ConnexionErrorListener extends EventListener {
    public void errorDetected(ConnexionErrorEvent event);
}
