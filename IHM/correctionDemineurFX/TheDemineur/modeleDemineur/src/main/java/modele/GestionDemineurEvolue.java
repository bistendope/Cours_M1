package modele;

import modele.exceptions.*;

import java.util.*;

/**
 * Created by YohanBoichut on 03/11/2016.
 */
public class GestionDemineurEvolue implements GestionDemineurEvolueInterface {


    private Map<Integer,Joueur> joueurs;

    private Map<Integer,Plateau> partieJoueur;

    private List<Integer> joueursConnectes;

    PartiesReferencees lesParties = PartiesReferencees.getInstance();


    private GestionDemineurEvolue() {
        this.joueurs = new HashMap<>();
        this.partieJoueur = new HashMap<>();
        this.joueursConnectes = new ArrayList<>();
    }


    private static GestionDemineurEvolueInterface instance = null;

    public static GestionDemineurEvolueInterface getInstance() {
        if (instance == null) {
            instance = new GestionDemineurEvolue();
        }
        return instance;

    }

    @Override
    public boolean decouvrir(int idJoueur, int x, int y) throws BombeException {
        this.partieJoueur.get(idJoueur).decouvrirCase(x,y);
        return this.partieJoueur.get(idJoueur).partieGagnee();
    }

    @Override
    public Classement getClassementPartie(int idPartie) {
        return lesParties.getClassementPartie(idPartie);
    }



    @Override
    public Collection<Integer> getListeIdentifiantParties() {
        return this.lesParties.getIdentifiantsParties();
    }


    @Override
    public Plateau getPlateau(int idJoueur) {
        return this.partieJoueur.get(idJoueur);
    }

    @Override
    public void associerNouvelleGrille(int idJoueur, int idGrille) {
        this.partieJoueur.put(idJoueur,this.lesParties.creerPartie(idGrille));
    }

    @Override
    public void resetPartie(int id) {
        this.partieJoueur.remove(id);
    }

    @Override
    public int connexion(String pseudo, String password) throws ConnexionImpossible, JoueurDejaConnecte {

        for (int i : joueurs.keySet()) {
            if (joueurs.get(i).getPseudoJoueur().equals(pseudo)) {
                if (joueurs.get(i).getMdpJoueur().equals(password)) {
                    if (this.joueursConnectes.contains(joueurs.get(i).getIdJoueur())) {
                        throw new JoueurDejaConnecte();
                    }
                    this.partieJoueur.put(joueurs.get(i).getIdJoueur(),null);
                    this.joueursConnectes.add(joueurs.get(i).getIdJoueur());
                    return joueurs.get(i).getIdJoueur();
                }
            }
        }

        throw new ConnexionImpossible();
    }


    @Override
    public boolean updateTemps(int idJoueur, long temps) throws ExceptionTriche {
        if (this.partieJoueur.get(idJoueur).partieGagnee()) {
            return this.lesParties.updateClassement(getJoueurById(idJoueur),this.partieJoueur.get(idJoueur).getIdPlateau(),temps);
        }
        throw new ExceptionTriche();
    }


    @Override
    public int inscription(String pseudo, String password) throws ExceptionLoginDejaPris {
        for (Joueur j : joueurs.values())  {
            if (j.getPseudoJoueur().equals(pseudo)) {
                throw new ExceptionLoginDejaPris();
            }
        }

        Joueur j = new Joueur(pseudo,password);
        this.joueurs.put(j.getIdJoueur(),j);
        this.partieJoueur.put(j.getIdJoueur(),null);
        this.joueursConnectes.add(j.getIdJoueur());
        return j.getIdJoueur();
    }


    @Override
    public void deconnexion(int idJoueur) {
        this.joueursConnectes.remove(idJoueur);

        this.partieJoueur.remove(idJoueur);
    }

    @Override
    public Joueur getJoueurById(int id) {
        return this.joueurs.get(id);
    }
}
