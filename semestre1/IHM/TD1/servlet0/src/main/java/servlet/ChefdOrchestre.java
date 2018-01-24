package servlet;

import modele.FonctionnalitesStaticVersion;
import modele.IFonctionnalites;
import modele.Prof;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Fred on 15/09/2016.
 */
public class ChefdOrchestre extends HttpServlet {
    IFonctionnalites fsc;
    public void init() throws ServletException{
        fsc = new FonctionnalitesStaticVersion();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action =  request.getParameter("action");
        System.out.println("paramètre: " + action);

        switch(action) {
            case "menu":
                getServletContext().getRequestDispatcher("/WEB-INF/vues/menu.jsp").forward(request, response);
                break;
            case "gotoAjouterEtudiant":
                getServletContext().getRequestDispatcher("/WEB-INF/vues/ajouterEtudiant.jsp").forward(request, response);
                break;
            case "gotoAjouterProf":
                getServletContext().getRequestDispatcher("/WEB-INF/vues/ajouterProf.jsp").forward(request, response);
                break;
            case "gotoAjouterFiliere":
                getServletContext().getRequestDispatcher("/WEB-INF/vues/ajouterFiliere.jsp").forward(request, response);
                break;
            case "gotoGererFiliere":
                getServletContext().getRequestDispatcher("/WEB-INF/vues/gererFiliere.jsp").forward(request, response);
                break;
            case"ajouterEtudiant":
                getServletContext().getRequestDispatcher("/control?action=ajouterEtudiant").forward(request, response);
                break;
            case "ajouterProf":
                // 1) récupération des paramètres
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String dateNaissance = request.getParameter("dateNaissance");

                // 2) conversion des types
                Date dateNaiss = null;
                try {
                    dateNaiss = new SimpleDateFormat("dd/MM/yyyy").parse(dateNaissance);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // 3) vérification de la validité des paramètres
                if(nom==null || nom.length()<4){
                    getServletContext().getRequestDispatcher("/WEB-INF/vues/ajouterProf.jsp").forward(request, response);
                }
                if(prenom==null || prenom.length()<4){
                    getServletContext().getRequestDispatcher("/WEB-INF/vues/ajouterProf.jsp").forward(request, response);
                }
                if(dateNaiss==null){
                    getServletContext().getRequestDispatcher("/WEB-INF/vues/ajouterProf.jsp").forward(request, response);
                }

                // 4) appel à la façade du modèle
                Prof prof = fsc.ajouterProfesseurBase(nom, prenom, dateNaiss);

                // 5) préparation des infos pour la vue
                request.setAttribute("prof", prof);
                request.setAttribute("listeProfs", fsc.getProfsBase());

                // 6) choix de la vue résultat
                getServletContext().getRequestDispatcher("/WEB-INF/vues/profAjoute.jsp").forward(request, response);
                break;
            case "ajouterFiliere":

                break;
            default: getServletContext().getRequestDispatcher("/WEB-INF/vues/menu.jsp").forward(request, response);
        }
    }
}
