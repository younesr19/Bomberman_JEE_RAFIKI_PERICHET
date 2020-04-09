package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bdd.JDBCPostgres;
import metier.Joueur;
import metier.Partie;

/**
 * Servlet implementation class Profil
 */
@WebServlet("/Profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JDBCPostgres bdd_psql = new JDBCPostgres("etudiant","123456789");
		HttpSession session = request.getSession();
		Joueur j = (Joueur) session.getAttribute("joueur");
		int id_profil =  Integer.parseInt(request.getParameter("id"));
		Joueur joueur = bdd_psql.getJoueur(id_profil);
		int ajout_ami = 0;
		 ajout_ami = Integer.parseInt(request.getParameter("ajoutAmi"));
		if(bdd_psql.estAmi(j.getId(), joueur.getId())) {
			request.setAttribute("estAmi", true);
		}
		else {
			request.setAttribute("estAmi", false);

		}
			
		if(ajout_ami ==1 ) {
			
			bdd_psql.ajouterAmi( j.getId(),  joueur.getId());
			bdd_psql.ajouterAmi( joueur.getId(),  j.getId());
			request.setAttribute("estAmi", true);


		}
		ArrayList<Partie> historique = bdd_psql.getReduceHistorique(id_profil);
		request.setAttribute("joueur", joueur);
		request.setAttribute("historique", historique);
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil_profil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
