package Serveur;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.PushbackInputStream ;

public class Client {
	Socket so;
	PrintWriter sortie;
	@SuppressWarnings("deprecation")
	public Client(String serveur, int port) {
		
		try {
			so = new Socket(serveur,port);
			InputThread it = new InputThread("test",so);
			
			OutputThread ot = new OutputThread(so);
			sortie = new PrintWriter(so.getOutputStream(), true);
			sortie.println("ClientJEE");
			it.start();
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	/*public static void main(String[] argu) {
		Socket so;
		//BufferedReader entree;
		PrintWriter sortie;
		String s; // le serveur
		int p; // le port de connexion
		String ch; // la chaîne envoyée
		int l; // et sa longueur reçue
		int test;
		boolean enCours = true;
			if (argu.length == 3) { // on récupère les paramètres
			s=argu[0];
			p=Integer.parseInt(argu[1]);
			ch=argu[2];
			try{// on connecte un socket
				
				so = new Socket(s, p);
				sortie = new PrintWriter(so.getOutputStream(), true);
				//entree = new BufferedReader(new InputStreamReader(so.getInputStream()));

				sortie.println(ch); // on écrit la chaîne et le newline dans le canal de sortie

					InputThread it = new InputThread(ch,so);
				
					OutputThread ot = new OutputThread(so);
					
	
					it.start();
					while(enCours) {
						BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));				
						String str = clavier.readLine();
						//String[] split = str.split(" ");
						sortie.println(str);

					}
					//new OutputThread(so).start();
				
				
				//entree.close();
				//sortie.close();
				//so.close(); // on ferme la connexion



			} catch(UnknownHostException e) {System.out.println(e);}
			catch (IOException e) {System.out.println("Aucun serveur n’est rattaché au port ");}
		} 
		else {
			System.out.println("syntaxe d’appel java cliTexte serveur port chaine_de_caractères\n");
		} 
		
	}*/
	public void ouvrirConnexion(String serveur, int port) {
		try {
			Socket so = new Socket(serveur,port);
			InputThread it = new InputThread("test",so);
			
			OutputThread ot = new OutputThread(so);
			PrintWriter sortie = new PrintWriter(so.getOutputStream(), true);
			sortie.println("salut le boomer");
			it.start();
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void envoyerMessageServeur(String msg) {
		this.sortie.println(msg);
	}
	public void nouveauSocket(String s, int p) {
		
	}
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

}


