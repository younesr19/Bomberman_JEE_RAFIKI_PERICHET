package Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Bomberman.externals.Agent;
import Bomberman.externals.AgentAction;
import Bomberman.externals.AgentAvecMouvement;
import Bomberman.externals.Map;
import Bomberman.externals.MapSimplifie;
import Metier.Joueur;
import Postgres.JDBCPostgres;


public class ServeurThread extends Thread {
	
	Socket so;
	String nom;
	BufferedReader entree;
	PrintWriter sortie;
	ObjectInputStream entree_objet;
	String messageEnvoye;
	String messageRecu;
	ArrayList<ServeurThread> liste_serveurT;
	MapSimplifie map_courante;
	int id;
	boolean enCours=true;
	public ServeurThread(Socket so,int id) {
		this.so = so;
		this.id = id;		
	}
	@Override
	public void run(){
		// TODO Auto-generated method stub
		try {
			entree = new BufferedReader(new InputStreamReader(so.getInputStream()));
			sortie = new PrintWriter(so.getOutputStream(), true);

			nom = entree.readLine(); // on lit ce qui arrive
			System.out.println("Connexion de "+nom);
			JDBCPostgres postgres = new JDBCPostgres("etudiant", "123456789");
			while(enCours) {
			    
				messageRecu = entree.readLine();
				ArrayList<String> messageRecu_split = decouper_message(messageRecu,"<>");
				String attribut = messageRecu_split.get(0);
				String data  = messageRecu_split.get(1);
				System.out.println("Bomberman -> "+attribut+"<>"+data );
				if(attribut.equals("getInformationJoueur")) {
					System.out.println("Reucpération du joueur dans la BDD ...");
					Joueur joueur = postgres.getJoueur(data);
					System.out.println(joueur.toString());
					envoyerMessageClientBomberman(joueur.toRequete());
					System.out.println("Les informations ont été envoyé au client bomberman !");
					
				}
				else if(attribut.equals("lancementPartie")) {
					System.out.println("Lancement d'une partie par "+data+" !");
				}
				else if(attribut.equals("dataMap")) {
					map_courante =  reconstruire_map(data);
				}
				else if(attribut.equals("verifieDeplacement")) {
					AgentAvecMouvement aam = (AgentAvecMouvement) reconstruire_AAM(data);
					if(isLegalMove(aam.getAgent(), aam.getAgentAction(), map_courante)) {
						System.out.println("Déplacement valide");
						envoyerMessageClientBomberman("true");
					}
					else {
						System.out.println("Deplacement invalide");
						envoyerMessageClientBomberman("false");

					}
				}
				else if (attribut.equals("resultatPartie")) {
					Hashtable<String,String> resultat_partie = recuperer_data(data);
					postgres.insererPartie(resultat_partie);
					envoyerMessageClientBomberman("partie enregistrée dans la base de données");
				}
				else {
					System.out.println(attribut+data);
				}
				
			}
		}catch (IOException e) { System.out.println("problème\n"+e); }
		super.run();
	}
	public String getMessageRecu() {
		return messageRecu;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (enCours ? 1231 : 1237);
		result = prime * result + ((entree == null) ? 0 : entree.hashCode());
		result = prime * result + id;
		result = prime * result + ((liste_serveurT == null) ? 0 : liste_serveurT.hashCode());
		result = prime * result + ((messageEnvoye == null) ? 0 : messageEnvoye.hashCode());
		result = prime * result + ((messageRecu == null) ? 0 : messageRecu.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((so == null) ? 0 : so.hashCode());
		result = prime * result + ((sortie == null) ? 0 : sortie.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServeurThread other = (ServeurThread) obj;
		if (enCours != other.enCours)
			return false;
		if (entree == null) {
			if (other.entree != null)
				return false;
		} else if (!entree.equals(other.entree))
			return false;
		if (id != other.id)
			return false;
		if (liste_serveurT == null) {
			if (other.liste_serveurT != null)
				return false;
		} else if (!liste_serveurT.equals(other.liste_serveurT))
			return false;
		if (messageEnvoye == null) {
			if (other.messageEnvoye != null)
				return false;
		} else if (!messageEnvoye.equals(other.messageEnvoye))
			return false;
		if (messageRecu == null) {
			if (other.messageRecu != null)
				return false;
		} else if (!messageRecu.equals(other.messageRecu))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (so == null) {
			if (other.so != null)
				return false;
		} else if (!so.equals(other.so))
			return false;
		if (sortie == null) {
			if (other.sortie != null)
				return false;
		} else if (!sortie.equals(other.sortie))
			return false;
		return true;
	}
	public void setServeurT(ArrayList<ServeurThread> liste_serveurT ) {
		this.liste_serveurT=liste_serveurT;
	}
	
	public void envoyerMessageClientBomberman(String message) {


		for(ServeurThread serveurThread : liste_serveurT) {
			if(serveurThread.nom.equals("ClientBomberman")) {
				serveurThread.sortie.println(message);				
				break;
			}
		}
	}
	public void envoyerMessageClientJEE(String message) {


		for(ServeurThread serveurThread : liste_serveurT) {
			if(serveurThread.nom.equals("ClientJEE")) {
				serveurThread.sortie.println("Message du client Bomberman : "+message);				
				break;
			}
		}
	}
	public ArrayList<String> decouper_message(String message,String delimiteur){
		
		String[] messageSplit = message.split(delimiteur);


		return new ArrayList<String>( Arrays.asList(messageSplit));


		
	}
	
	public boolean isLegalMove(Agent a, AgentAction agentAction,MapSimplifie _map) {
		switch (agentAction) {
		case MOVE_UP:
			return !(_map.getWalls()[a.getX()][a.getY()+1] ||_map.getStart_brokable_walls()[a.getX()][a.getY()+1] );
		case MOVE_DOWN:
			return !(_map.getWalls()[a.getX()][a.getY()-1] ||_map.getStart_brokable_walls()[a.getX()][a.getY()-1] );
		case MOVE_LEFT:
			return !(_map.getWalls()[a.getX()-1][a.getY()] ||_map.getStart_brokable_walls()[a.getX()-1][a.getY()] );
		case MOVE_RIGHT:
			return !(_map.getWalls()[a.getX()+1][a.getY()] ||_map.getStart_brokable_walls()[a.getX()+1][a.getY()] );
		case PUT_BOMB:
			return true;
		case STOP:
			return true;
		default:
			return false;
		}
		
	}
	public MapSimplifie reconstruire_map(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		MapSimplifie map = null;
		try {
			map = objectMapper.readValue(json,MapSimplifie.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;

	}
	public AgentAvecMouvement reconstruire_AAM(String json) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		AgentAvecMouvement objet_retour = null;
		try {
			objet_retour = objectMapper.readValue(json,AgentAvecMouvement.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objet_retour;

	}
	public Hashtable<String,String> recuperer_data(String message){
		
		Hashtable<String,String> data = new Hashtable<>();
		
		System.out.println(message);
		ArrayList<String> liste_data = decouper_message(message,",");
		ArrayList<String> literal;

		for(String donnee : liste_data) {
			literal = decouper_message(donnee,":");
			data.put(literal.get(0), literal.get(1));
		}
		
		
		return data;
		
		
		
	}
	public Socket getSocket() {
		return this.so;
	}
}
