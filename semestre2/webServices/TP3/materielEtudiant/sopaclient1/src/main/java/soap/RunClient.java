package soap;

import net.webservicex.GeoIPService;
import service.Annuaire;
import service.AnnuaireService;

public class RunClient {
    public static void main(String[] args) {
        GeoIPService service = new GeoIPService();

        GeoIPService proxy = service.();
        System.out.println("liste : " + proxy.getAllNom());
    }
}
