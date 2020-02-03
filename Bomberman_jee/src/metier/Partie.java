package metier;

import java.time.LocalDate;
import java.util.Date;

public class Partie {
	private int id_partie;
	private int id_joueur;
	private String pseudo;
	private Date date_partie;
	private int type_partie;
	private int score;	
	private boolean agagne;
	
	
	
	public Partie() {}
	public Partie(int id_partie, int id_joueur, String pseudo,Date date_partie, int type_partie, int score, boolean agagne) {
		super();
		this.id_partie = id_partie;
		this.id_joueur = id_joueur;
		this.pseudo=pseudo;
		this.date_partie = date_partie;
		this.type_partie = type_partie;
		this.score = score;
		this.agagne = agagne;
	}
	public int getId_partie() {
		return id_partie;
	}
	public void setId_partie(int id_partie) {
		this.id_partie = id_partie;
	}
	public int getId_joueur() {
		return id_joueur;
	}
	public void setId_joueur(int id_joueur) {
		this.id_joueur = id_joueur;
	}
	public Date getDate_partie() {
		return date_partie;
	}
	public void setDate_partie(Date date_partie) {
		this.date_partie = date_partie;
	}
	public int getType_partie() {
		return type_partie;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public void setType_partie(int type_partie) {
		this.type_partie = type_partie;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isAgagne() {
		return agagne;
	}
	public void setAgagne(boolean agagne) {
		this.agagne = agagne;
	}
	
	
}
