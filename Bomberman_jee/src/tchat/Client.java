package tchat;

//package tchat;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

import java.io.PushbackInputStream ;
public class Client extends Thread {
	
	Socket so;
	//BufferedReader entree;
	PrintWriter sortie;
	String s; // le serveur
	int p; // le port de connexion
	String ch; // la chaîne envoyée
	int l; // et sa longueur reçue
	int test;
	boolean enCours = true;
	String serveur;
	int port;
	String nom;
	HttpServletResponse response;
	@SuppressWarnings("deprecation")
	public Client(String serveur, int port, String nom, HttpServletResponse response) {
		this.serveur=serveur;
		this.port=port;
		this.nom=nom;
		this.response=response;
		
	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try{// on connecte un socket
			
			so = new Socket(serveur, port);
			sortie = new PrintWriter(so.getOutputStream(), true);
			//entree = new BufferedReader(new InputStreamReader(so.getInputStream()));

			sortie.println(nom); // on écrit la chaîne et le newline dans le canal de sortie

				
			System.out.print("["+nom+"]: ");

				InputThread it = new InputThread(nom,so);
				/*
				*/
				OutputThread ot = new OutputThread(so);
					
	
				it.start();
				while(enCours) {
					BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));				
					String str = clavier.readLine();
					String[] split = str.split(" ");
					if(split[0].equals("/quitter")) {
						enCours=false;
						sortie.println("dc");
					}
					else if(split[0].equals("/chuchoter")) {
						if(split[1].equals("")) {
								System.out.println("erreur");
						}
						else {
								sortie.println(split[1]);
						}
					}
					else {
						sortie.println(str);
						 response.setContentType("text/html");
						 PrintWriter out = response.getWriter();
						 out.append("["+nom+"]: "+str+"<br>");
						 
					}

					}
					//new OutputThread(so).start();
				
				
				//entree.close();
				//sortie.close();
				//so.close(); // on ferme la connexion



			} catch(UnknownHostException e) {System.out.println(e);}
			catch (IOException e) {System.out.println("Aucun serveur n’est rattaché au port ");}
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

