package servlets;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import bdd.JDBCPostgres;
import metier.InscriptionForm;
import metier.Joueur;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
@MultipartConfig
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		JDBCPostgres bdd_psql = new JDBCPostgres("etudiant","123456789");


	   
	
	    File fileToSave = new File("./WebContent/avatar");
	    System.out.println(new File(".").getAbsolutePath());
	    Path pathToFile = Paths.get(fileToSave.toURI());
	    System.out.println(pathToFile.toAbsolutePath());
		
		
		InscriptionForm form = new InscriptionForm();
        Joueur joueur = form.inscrireJoueur(request);
        request.setAttribute("forms", form);
        if(form.getResultat()!="Échec de l'inscription.") {
    	    String nomFichier = "avatar_"+request.getParameter("pseudo")+".jpg";
    	    
  
    	    
    	    uploadAvatar(request, nomFichier);
    	    joueur.setCheminAvatar(nomFichier);
	        request.setAttribute("joueur", joueur);
	        bdd_psql.insererJoueur(joueur);
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
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
	
	
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

	
	
}

