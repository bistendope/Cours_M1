package service;

import modele.Contact;
import modele.Personne;

import javax.jws.WebParam;
import java.util.Date;
import java.util.HashMap;

public class AnnuaireImpl implements AnnuaireI{
    // fake DAO
    private static HashMap<String, Personne> annuaire;
    static {
        annuaire = new HashMap<String, Personne>();

        Personne fred = new Personne("moal", "frederic", new Date());
        fred.addContact(new Contact("tel", "0238000000", fred));
        fred.addContact(new Contact("mail", "frederic.moal@univ-orleans.fr", fred));
        annuaire.put(fred.getNom(), fred);

        final Personne matthieu = new Personne("exbrayat", "matthieu", new Date());
        matthieu.addContact(new Contact("tel", "0202020202", matthieu));
        annuaire.put(matthieu.getNom(), matthieu);

        final Personne yohan = new Personne("yohan", "boichut", new Date());
        matthieu.addContact(new Contact("mail", "yohan.boichut@univ-orleans.fr", yohan));
        annuaire.put(yohan.getNom(), yohan);
    }

    public Personne getPersonneByName(String name){
        return annuaire.get(name);
    }

    public String searchTelephone(@WebParam String nom) {
        Personne p = annuaire.get(nom);
        if(p!=null){
            for(Contact c :p.getContacts()){
                if(c.getType().equals("tel")){
                    return c.getAdresse();
                }
            }
        }
        return null;
    }
    public void addPersonne(Personne p){
        annuaire.put(p.getNom(), p);
    }

    public void addPersonneList(String nom, String prenom, String tel, Date date){
        Personne p = new Personne (nom, prenom date);
        Contact c = new Contact(, tel, p )


    }
}
