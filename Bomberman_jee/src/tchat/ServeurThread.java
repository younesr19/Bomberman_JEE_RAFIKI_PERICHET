package tchat;

//package tchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServeurThread extends Thread{
	
	Socket so;
	String nom;
	BufferedReader entree;
	PrintWriter sortie;
	String messageEnvoye;
	String messageRecu;
	ArrayList<ServeurThread> liste_serveurT;
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

			while(enCours) {
				messageRecu = entree.readLine();
				if(messageRecu.equals("dc")) {
					enCours=false;
					prevenirDeconnexion(nom);
					sortie.println("Au revoir "+nom+" !");
				}
				else {
					envoyerMessage(nom,"*",messageRecu);
				}
				//System.out.println(messageRecu);
				
				
			}
		}catch (IOException e) { System.out.println("problème\n"+e); }
		super.run();
	}
	public String getMessageRecu() {
		return messageRecu;
	}
	public void envoyerMessage(String expediteur,String destinataire,String message) {
		if(destinataire.equals("*")) {
			System.out.println("envoie à tout le monde de "+message+" de la part de "+id+"");
			for(ServeurThread serveurThread : liste_serveurT) {
				if(!serveurThread.nom.contentEquals(expediteur))
					serveurThread.sortie.println("["+expediteur+"]: "+message);
			}
		}
	}
	public void prevenirDeconnexion(String expediteur) {
		System.out.println("deconnexion de "+expediteur);
		for(ServeurThread serveurThread : liste_serveurT) {
			if(!serveurThread.nom.contentEquals(expediteur))
				serveurThread.sortie.println("Deconnexion de "+expediteur+" !");
		}
		
	}
	public void setServeurT(ArrayList<ServeurThread> liste_serveurT ) {
		this.liste_serveurT=liste_serveurT;
	}
	public static void envoyerBienvenu(PrintWriter sortie,String nom) {
		
		sortie.println("[serveur] Bienvenue sur le serveur du nombre inconnu "+nom+" !");
	}
	
}