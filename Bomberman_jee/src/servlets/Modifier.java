package servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bdd.JDBCPostgres;
import metier.Joueur;
import metier.ModificationForm;

/**
 * Servlet implementation class Modifier
 */

@WebServlet("/Modifier")
@MultipartConfig
public class Modifier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modifier() {
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
		if(j==null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);

		}else {
			request.setAttribute("joueur", j);
			this.getServletContext().getRequestDispatcher("/WEB-INF/modification.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JDBCPostgres bdd_psql = new JDBCPostgres("etudiant","123456789");
	    System.out.println("psuedo "+request.getParameter("pseudo"));

		
		HttpSession session = request.getSession();
		Joueur j = (Joueur) session.getAttribute("joueur");
		String mail_joueur = j.getEmail();
		File fileToSave = new File("./WebContent/avatar");
	    System.out.println(new File(".").getAbsolutePath());
	    Path pathToFile = Paths.get(fileToSave.toURI());
	    System.out.println(pathToFile.toAbsolutePath());
		
	    ModificationForm modif_form = new ModificationForm();
	    Joueur joueur_modif = modif_form.modifierJoueur(request);
        request.setAttribute("forms", modif_form);
        
        if(modif_form.getResultat()!="Échec de la modification.") {
    	    String nomFichier = "avatar_"+request.getParameter("pseudo")+".jpg";
    	    
  
    	    
    	    uploadAvatar(request, nomFichier);
    	    joueur_modif.setCheminAvatar(nomFichier);
	       // request.setAttribute("joueur", joueur);
	        bdd_psql.modifierJoueur(mail_joueur,joueur_modif);
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/modification.jsp").forward(request, response);

	}
	
	public void uploadAvatar(HttpServletRequest request,String nomFichier) throws IOException, ServletException {
		Part filePart = request.getPart("avatar");
		
		//get the InputStream to store the file somewhere
	    InputStream fileInputStream = filePart.getInputStream();
	    
	    
	    
	    //for example, you can copy the uploaded file to the server
	    //note that you probably don't want to do this in real life!
	    //upload it to a file host like S3 or GCS instead
	    File fileToSave = new File("eclipse-workspace-jee/Bomberman_jee/WebContent/avatar/" + nomFichier);
	   
	   
        Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

}
