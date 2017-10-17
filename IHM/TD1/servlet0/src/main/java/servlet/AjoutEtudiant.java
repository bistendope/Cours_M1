package servlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modele.FonctionnalitesStaticVersion;
import modele.Etudiant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class AjoutEtudiant extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FonctionnalitesStaticVersion fsc = new FonctionnalitesStaticVersion();
        long numEtudiant = Long.parseLong(request.getParameter("numEtudiant"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String dateNaissance = request.getParameter("dateNaissance");
        request.getParameter("numEtudiant");

        // 2) conversion des types
        Date dateNaiss = null;
        try {
            dateNaiss = new SimpleDateFormat("dd/MM/yyyy").parse(dateNaissance);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 3) vérification de la validité des paramètres
        if()
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
        Etudiant etu = fsc.ajouterEtudiantBase(numEtudiant, nom, prenom, dateNaiss);

        // 5) préparation des infos pour la vue
        request.setAttribute("etu", etu);
        request.setAttribute("listeEtu", fsc.getEtudiantFromFiliere("Science"));

        // 6) choix de la vue résultat
        getServletContext().getRequestDispatcher("/WEB-INF/vues/profAjoute.jsp").forward(request, response);



    }
}
