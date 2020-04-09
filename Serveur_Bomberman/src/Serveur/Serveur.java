package Serveur;
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


public class Serveur {
	
	public static void main(String[] argu) {
		int p; // le port d’écoute
		ServerSocket ecoute;
		BufferedReader entree;
		PrintWriter sortie;
		String nom; // la chaîne reçue
		String test;
		int nbr=0;
		Hashtable<Integer, ServeurThread> liste_serveurThread = new Hashtable<Integer, ServeurThread>();
		ArrayList<ServeurThread> liste_serveurT = new ArrayList<ServeurThread>();
		if (argu.length == 1) {
			try {
				p=Integer.parseInt(argu[0]); // on récupère le port
				ecoute = new ServerSocket(p); // on crée le serveur
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
		else { 
			System.out.println("syntaxe d’appel java servTexte port\n"); 
		} 
	}

	

}

