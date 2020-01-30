package metier;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 */

/**
 * @author etudiant
 *
 */

public class Joueur implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String email;
	private String motDePasse;
	private String pseudo;
	private LocalDate date_create;
	private int sexe;
	private int niveau;
	private String cheminAvatar;
	
	
	public Joueur() {}
	public Joueur(String email, String motDePasse, String pseudo, LocalDate date_create, int sexe) {
		super();
		this.id=0;
		this.email = email;
		this.motDePasse = motDePasse;
		this.pseudo = pseudo;
		this.date_create = date_create;
		this.sexe = sexe;
		this.niveau=1;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public LocalDate getDate_create() {
		return date_create;
	}
	public void setDate_create(LocalDate date_create) {
		this.date_create = date_create;
	}
	public int getSexe() {
		return sexe;
	}
	public void setSexe(int sexe) {
		this.sexe = sexe;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public String getCheminAvatar() {
		return cheminAvatar;
	}
	public void setCheminAvatar(String cheminAvatar) {
		this.cheminAvatar=cheminAvatar;
	}
	
	
}
