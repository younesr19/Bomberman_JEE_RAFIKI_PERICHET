package Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class OutputThread extends Thread{

	Socket so;
	PrintWriter sortie;
	public OutputThread(Socket so) {
		this.so=so;
		try {
			sortie = new PrintWriter(so.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public void run() {

		BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));				
		String str;
		try {
			str = clavier.readLine();
			System.out.println(str);
			sortie.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
