/**
 * 
 */
package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.sql.Date;

import javax.xml.bind.DatatypeConverter;

import metier.Joueur;
import metier.Partie;

import java.io.UnsupportedEncodingException;
import java.security.*;
import sun.security.provider.MD5;

public class JDBCPostgres {
	/* Connexion à la base de données */
	String utilisateur = "postgres";
	String motDePasse = "123456789";
	Connection connexion = null;

	public JDBCPostgres( String utilisateur, String motDePasse) {

		 try {
			 	Class.forName("org.postgresql.Driver");
			 	Connection connexion = DriverManager.getConnection("jdbc:postgresql://localhost/pg_bomberman", utilisateur, motDePasse);
	            if (connexion == null) {	            
	                System.out.println("Failed to make connection!");
	            }

	            this.connexion=connexion;
	        } catch (SQLException e) {
	            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}
	public Connection getConnection() {
		return this.connexion;
	}
	
	public boolean mailExiste(String mail) {
		
		try {
			PreparedStatement ps = this.connexion.prepareStatement("SELECT count(*) FROM joueur WHERE email = ?");
			ps.setString(1, mail);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt(1)==0) {
					return false;
				}
				return true;
			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	public String hacheMDP(String motdepasse) {
		String password = motdepasse;

		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(password.getBytes());
		byte[]digest = md.digest();
		String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return myHash;
	}
	public void insererJoueur(Joueur j) {
		
		try {
			PreparedStatement ps = this.connexion.prepareStatement("INSERT INTO Joueur (email,motDePasse,pseudo,date_create,sexe,niveau,chemin_avatar) VALUES (?,?,?,?,?,?,?);");
			java.util.Date today = j.getDate_create();
			java.sql.Date sqlDateToday = new java.sql.Date(today.getTime());
			ps.setString(1, j.getEmail());
			ps.setString(2, hacheMDP(j.getMotDePasse()));
			ps.setString(3, j.getPseudo());
			ps.setObject(4, sqlDateToday);
			ps.setLong(5, j.getSexe());
			ps.setLong(6,j.getNiveau());
			ps.setString(7, j.getCheminAvatar());
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	
	public void ajouterAmi(int id_joueur1, int id_joueur2) {
		
		try {
			PreparedStatement ps = this.connexion.prepareStatement("INSERT INTO Ami(id_ami1,id_ami2) values (?,?)");
			
			ps.setLong(1, id_joueur1);
			ps.setLong(2, id_joueur2);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public boolean estAmi(int id_joueur1,int id_joueur2) {
		
		try {
			PreparedStatement ps = this.connexion.prepareStatement("SELECT id_ami1 FROM ami WHERE id_ami1 = ? AND id_ami2 = ?");
			ps.setLong(1, id_joueur1);
			ps.setLong(2, id_joueur2);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt(1)==0) {
					return false;
				}
				return true;
			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	public void modifierJoueur(String email,Joueur j) {
		
		try {
			PreparedStatement ps = this.connexion.prepareStatement("UPDATE Joueur SET pseudo = ? , motdepasse = ?, sexe = ? , chemin_avatar = ? WHERE email = ?;");
			ps.setString(1, j.getPseudo());
			ps.setString(2, hacheMDP(j.getMotDePasse()));
			ps.setLong(3, j.getSexe());
			ps.setString(4, j.getCheminAvatar());
			ps.setString(5, email);

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public ArrayList<Joueur> getClassement(){
		ArrayList<Joueur> classement = new ArrayList<>();
		try {
			PreparedStatement ps = this.connexion.prepareStatement("select id,pseudo,chemin_avatar,niveau,coalesce(sum(score),0) as score from joueur j left join historique h on j.id=h.id_joueur group by id,pseudo,chemin_avatar,niveau ORDER BY coalesce(sum(score),0) DESC;");

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Joueur j = new Joueur();
				j.setId(rs.getInt(1));
				j.setPseudo(rs.getString(2));
				j.setCheminAvatar(rs.getString(3));
				j.setNiveau(rs.getInt(4));
				j.setScore_max(rs.getInt(5));
				classement.add(j);
			}

			return classement;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean joueurExiste(String email, String mdp) {

		try {
			PreparedStatement ps = this.connexion.prepareStatement("SELECT count(*) FROM joueur WHERE email = ? AND motDePasse = ?");
			ps.setString(1, email);
			ps.setString(2, hacheMDP(mdp));

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt(1)==0) {
					return false;
				}
				return true;
			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public Joueur getJoueur(String email) {
		try {
			PreparedStatement ps = this.connexion.prepareStatement("SELECT id, email, motdepasse,pseudo,date_create, sexe, niveau,chemin_avatar, sum(case agagne when true then 1 else 0 end) as Victoire,sum(case agagne when false then 1 else 0 end) as Defaite,max(score) FROM joueur j LEFT JOIN historique h ON j.id=h.id_joueur WHERE email = ? GROUP BY id, email, motdepasse,pseudo,date_create, sexe, niveau,chemin_avatar");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Joueur j = new Joueur();
				j.setId(rs.getInt(1));
				j.setEmail(email);
				j.setMotDePasse(rs.getString(3));
				j.setPseudo(rs.getString(4));
				Date date = (Date) rs.getObject(5);
				j.setDate_create(date);
				j.setSexe(rs.getInt(6));
				j.setNiveau(rs.getInt(7));
				j.setCheminAvatar(rs.getString(8));
				j.setNbr_victoire(rs.getInt(9));
				j.setNbr_defaite(rs.getInt(10));
				j.setScore_max(rs.getInt(11));
				return j;
			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Joueur getJoueur(int id_joueur) {
		try {
			PreparedStatement ps = this.connexion.prepareStatement("SELECT id, email, motdepasse,pseudo,date_create, sexe, niveau,chemin_avatar, sum(case agagne when true then 1 else 0 end) as Victoire,sum(case agagne when false then 1 else 0 end) as Defaite,max(score) FROM joueur j LEFT JOIN historique h ON j.id=h.id_joueur WHERE id = ? GROUP BY id, email, motdepasse,pseudo,date_create, sexe, niveau,chemin_avatar");
			ps.setInt(1, id_joueur);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Joueur j = new Joueur();
				j.setId(rs.getInt(1));
				j.setEmail(rs.getString(2));
				j.setMotDePasse(rs.getString(3));
				j.setPseudo(rs.getString(4));
				Date date = (Date) rs.getObject(5);
				j.setDate_create(date);
				j.setSexe(rs.getInt(6));
				j.setNiveau(rs.getInt(7));
				j.setCheminAvatar(rs.getString(8));
				j.setNbr_victoire(rs.getInt(9));
				j.setNbr_defaite(rs.getInt(10));
				j.setScore_max(rs.getInt(11));
				return j;
			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Partie> getHistorique(int id_joueur){
		ArrayList<Partie> historique = new ArrayList<Partie>();
		
		try {
			PreparedStatement ps = 
					this.connexion.prepareStatement("select h2.id_partie, h2.id_joueur, pseudo,h2.date_partie, h2.type_partie, h2.score, h2.agagne from historique h1 JOIN historique h2 ON h1.id_partie=h2.id_partie JOIN Joueur j ON h2.id_joueur=j.id WHERE h1.id_joueur= ? ORDER BY h2.date_partie DESC, h2.id_partie DESC, h2.score DESC ;");
			ps.setInt(1, id_joueur);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Date date = (Date) rs.getObject(4);
				
				
				Partie partie = new Partie(
				rs.getInt(1),
				rs.getInt(2),
				rs.getString(3),
				date,
				rs.getInt(5),
				rs.getInt(6),
				rs.getBoolean(7)
				);
				
				historique.add(partie);
			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return historique;
		
		
	}
	
	public ArrayList<Partie> getReduceHistorique(int id_joueur){
		ArrayList<Partie> historique = new ArrayList<Partie>();
		try {
			PreparedStatement ps = 
					this.connexion.prepareStatement("select id_partie, id_joueur, date_partie, type_partie, score, agagne FROM historique WHERE id_joueur = ? ORDER BY date_partie DESC;");
			ps.setInt(1, id_joueur);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Date date = (Date) rs.getObject(3);
				
				
				Partie partie = new Partie(
				rs.getInt(1),
				rs.getInt(2),
				date,
				rs.getInt(4),
				rs.getInt(5),
				rs.getBoolean(6)
				);
				
				historique.add(partie);
			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return historique;
	}
	
	public void insererConnexion(String session_id, int id_joueur) {
		try {
			PreparedStatement ps = this.connexion.prepareStatement("INSERT INTO Connectes (id_session,id_joueur) VALUES (?,?);");
			ps.setString(1, session_id);
			ps.setInt(2, id_joueur);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String,Joueur> getJoueursConnectes(){
		Map<String,Joueur> liste_joueur = new HashMap<String,Joueur>();
		
		try {
			PreparedStatement ps = 
					this.connexion.prepareStatement("select id_session,id, email, motdepasse, pseudo, date_create, sexe, chemin_avatar, niveau from joueur j join connectes c on j.id=c.id_joueur");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Date date = (Date) rs.getObject(6);
				
				Joueur joueur = new Joueur();
				joueur.setId(rs.getInt(2));
				joueur.setEmail(rs.getString(3));
				joueur.setMotDePasse(rs.getString(4));
				joueur.setPseudo(rs.getString(5));
				joueur.setDate_create(date);
				joueur.setSexe(rs.getInt(7));
				joueur.setCheminAvatar(rs.getString(8));
				joueur.setNiveau(rs.getInt(9));
				
				liste_joueur.put(rs.getString(1), joueur);
						
			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return liste_joueur;
	}
	
	public void supprimerConnexion(int id_joueur) {
		
		try {
			PreparedStatement ps = this.connexion.prepareStatement("DELETE FROM Connectes WHERE id_joueur = ?;");
			ps.setInt(1, id_joueur);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean estConnecte(int id_joueur) {
		
		try {
			PreparedStatement ps = this.connexion.prepareStatement("SELECT count(*) FROM connectes WHERE id_joueur = ?");
			ps.setInt(1, id_joueur);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt(1) == 0 ) {
					return false;
				}
				return true;
			}
			



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Map<Joueur,Boolean> getListeAmi(int id_joueur){
		Map<Joueur,Boolean> liste_ami = new LinkedHashMap<Joueur,Boolean>();
		try {
			PreparedStatement ps = this.connexion.prepareStatement("SELECT id, pseudo, niveau, chemin_avatar, id_session FROM joueur j JOIN ami a ON j.id=a.id_ami2 LEFT JOIN  connectes c ON a.id_ami2=c.id_joueur WHERE id_ami1 = ? ORDER BY id_session ASC, pseudo ASC");
			ps.setInt(1, id_joueur);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Joueur j = new Joueur();
				j.setId(rs.getInt(1));
				j.setPseudo(rs.getString(2));
				j.setNiveau(rs.getInt(3));
				j.setCheminAvatar(rs.getString(4));
				if(rs.getString(5) == null) {
					liste_ami.put(j,false);

				}
				else {
					liste_ami.put(j,true);

				}
			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		return liste_ami;
		
	}
}	
