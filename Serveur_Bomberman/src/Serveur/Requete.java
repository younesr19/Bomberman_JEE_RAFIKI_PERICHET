package Serveur;
import java.io.Serializable;
public class Requete implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String nom_requete;
	public String parametre;
	public Requete(String nom_requete, String parametre) {
		super();
		this.nom_requete = nom_requete;
		this.parametre = parametre;
	}
	/**
	 * @return the nom_requete
	 */
	public String getNom_requete() {
		return nom_requete;
	}
	/**
	 * @param nom_requete the nom_requete to set
	 */
	public void setNom_requete(String nom_requete) {
		this.nom_requete = nom_requete;
	}
	/**
	 * @return the parametre
	 */
	public String getParametre() {
		return parametre;
	}
	/**
	 * @param parametre the parametre to set
	 */
	public void setParametre(String parametre) {
		this.parametre = parametre;
	}
	@Override
	public String toString() {
		return "Requete [nom_requete=" + nom_requete + ", parametre=" + parametre + "]";
	}
	
	
}
