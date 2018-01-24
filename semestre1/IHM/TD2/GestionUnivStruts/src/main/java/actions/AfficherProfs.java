package actions;
import modele.Prof;
import java.util.Collection;


public class AfficherProfs extends Environment {

    private Collection<Prof> listeProfs;

    public String execute() throws Exception{
        super.execute();
        listeProfs = this.getFacade().getProfsBase();
        return SUCCESS;
    }

    public Collection<Prof> getListeProfs() {
        return listeProfs;
    }
    public void setListeProfs(Collection<Prof> listeProfs) {
        this.listeProfs = listeProfs;
    }

}
