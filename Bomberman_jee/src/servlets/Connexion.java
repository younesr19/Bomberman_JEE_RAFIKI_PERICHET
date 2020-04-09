package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bdd.JDBCPostgres;
import metier.InscriptionForm;
import metier.Joueur;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
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
		Joueur joueur = (Joueur) session.getAttribute("joueur");
		if(joueur!=null) {
			bdd_psql.supprimerConnexion(((Joueur) session.getAttribute("joueur")).getId());

			session.removeAttribute("joueur");
		}
	
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JDBCPostgres bdd_psql = new JDBCPostgres("etudiant","123456789");
		boolean estPresent = bdd_psql.joueurExiste(request.getParameter("email"),request.getParameter("motdepasse"));
		request.setAttribute("estPresent", estPresent);
		if(estPresent) {
			Joueur j = bdd_psql.getJoueur(request.getParameter("email"));
			HttpSession session = request.getSession();
			session.setAttribute("joueur",j);
			bdd_psql.insererConnexion(session.getId(), j.getId());
			System.out.println("connexion de "+j.getPseudo());
			response.sendRedirect("Accueil");
			
		}
		else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);

		}

	}

}
