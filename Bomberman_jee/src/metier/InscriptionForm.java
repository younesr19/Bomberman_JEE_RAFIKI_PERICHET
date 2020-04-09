package metier;
/**
 * 
 */

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import bdd.JDBCPostgres;
/**
 * @author etudiant
 *
 */
public final class InscriptionForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "motdepasse";
    private static final String CHAMP_CONF   = "confmotdepasse";
    private static final String CHAMP_PSEUDO    = "pseudo";
    private static final String CHAMP_SEXE   = "sexe";

    
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    
    public String getResultat() {return this.resultat;}	
    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    
    public Joueur inscrireJoueur( HttpServletRequest request ) {
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String confirmation = getValeurChamp( request, CHAMP_CONF );
        String pseudo = getValeurChamp( request, CHAMP_PSEUDO );
        int sexe = Integer.parseInt(getValeurChamp( request, CHAMP_SEXE ));

        Joueur joueur = new Joueur();
      
        JDBCPostgres bdd_postgres = new JDBCPostgres("etudiant","123456789");
        if(bdd_postgres.mailExiste(email)) {
        	setErreur(CHAMP_EMAIL,"L'email existe déjà");
        }
        
        joueur.setEmail(email);
        
        try {
            validationMotsDePasse( motDePasse, confirmation );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }
        joueur.setMotDePasse( motDePasse );


        joueur.setPseudo( pseudo );

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'inscription. <a href='Connexion'>Connectez-vous ! </a>";
        } else {
            resultat = "Échec de l'inscription.";
        }

        joueur.setSexe(sexe);
        
		LocalDate localDate = LocalDate.now();

	    Date date = Date.from(localDate.atStartOfDay()
	    	      .atZone(ZoneId.systemDefault())
	    	      .toInstant());
		joueur.setDate_create(date);
		joueur.setNiveau(1);
        return joueur;
        
    }
    
    
    private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception {
        if ( motDePasse != null && confirmation != null ) {
            if ( !motDePasse.equals( confirmation ) ) {
                throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if ( motDePasse.length() < 3 ) {
                throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }


    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
 }}
