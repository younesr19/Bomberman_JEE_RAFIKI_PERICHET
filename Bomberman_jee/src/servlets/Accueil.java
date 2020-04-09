package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bdd.JDBCPostgres;
import metier.Joueur;

import java.util.Date;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Joueur j = (Joueur) session.getAttribute("joueur");
		
		Date d = new Date(session.getCreationTime());
	
		
		if(j==null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);

		}else {
			request.setAttribute("joueur",j);
			JDBCPostgres bdd_psql = new JDBCPostgres("etudiant","123456789");
			request.setAttribute("historique", bdd_psql.getHistorique(j.getId()));
			request.setAttribute("liste_joueurco",bdd_psql.getJoueursConnectes());
			request.setAttribute("liste_ami", bdd_psql.getListeAmi(j.getId()));
			request.setAttribute("classement", bdd_psql.getClassement());
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public HttpServletResponse redirige(String msg) {
		System.out.println(msg);
		return null;
	}

}
