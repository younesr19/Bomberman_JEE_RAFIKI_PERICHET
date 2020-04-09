/**
 * 
 */
package tchat;

/**
 * @author etudiant
 *
 */
//package tchat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;

public class Serveur extends Thread{
	int p; // le port d’écoute
	ServerSocket ecoute;
	BufferedReader entree;
	PrintWriter sortie;
	String nom; // la chaîne reçue
	int nbr=0;
	int port;
	
	Hashtable<Integer, ServeurThread> liste_serveurThread = new Hashtable<Integer, ServeurThread>();
	public Serveur(int port) {
		this.port=port;
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		ArrayList<ServeurThread> liste_serveurT = new ArrayList<ServeurThread>();
		try {
			// on récupère le port
			ecoute = new ServerSocket(port); // on crée le serveur
			System.out.println("serveur mis en place ");
			while (true) {// le serveur va attendre qu’une connexion arrive
				Socket so = ecoute.accept();
				ServeurThread st = new ServeurThread(so,nbr);
				liste_serveurT.add(st);
				st.setServeurT(liste_serveurT);
				st.start();
				nbr++;	
			}
		} catch (IOException e) { System.out.println("problème\n"+e); }
	}

	

}
