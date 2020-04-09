package tchat;

//package tchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class InputThread extends Thread {
	
	BufferedReader entree;
	Socket so;
	String nom;
	boolean enCours;
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
				if(recu.equals("Au revoir "+nom+" !")) {
					System.out.println("Au revoir "+nom+" !");
					enCours=false;
					so.close();
				}
				else {
					System.out.print("\n"+recu+"\n["+nom+"]: ");

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}