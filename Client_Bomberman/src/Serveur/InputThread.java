package Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class InputThread extends Thread {
	
	BufferedReader entree;
	Socket so;
	String nom;
	boolean enCours;
	boolean messageArrivee = false;
	String chaine_arrivee; 
	public InputThread(String nom,Socket so) {
		this.so=so;
		this.nom=nom;
		this.enCours=true;
		try {
			
			entree = new BufferedReader(new InputStreamReader(so.getInputStream()));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			while(enCours) {
				String recu = entree.readLine();
				chaine_arrivee = recu;
				messageArrivee=true;

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
}
