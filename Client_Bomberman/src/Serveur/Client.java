package Serveur;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.PushbackInputStream ;

public class Client {
	Socket so;
	PrintWriter sortie;
	InputThread it;
	OutputThread ot;
	@SuppressWarnings("deprecation")
	public Client(String serveur, int port) {
		
		try {
			so = new Socket(serveur,port);
			it = new InputThread("test",so);
			ot = new OutputThread(so);
			sortie = new PrintWriter(so.getOutputStream(), true);
			sortie.println("ClientBomberman");
			
			//System.out.println(json);
			//Requete test_retour = objectMapper.readValue(json,Requete.class);
			//sortie.println("getInformationJoueur/younes.rafiki@hotmail.com");
			it.start();
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void ouvrirConnexion(String serveur, int port) {
		try {
			Socket so = new Socket(serveur,port);
			InputThread it = new InputThread("test",so);
			
			OutputThread ot = new OutputThread(so);
			PrintWriter sortie = new PrintWriter(so.getOutputStream(), true);
		  
		    //sortie.println("salut le boomer");
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
	public void envoyerObjetServeur(String attribut,Object objet) {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "";
		try {
			json = objectMapper.writeValueAsString(objet);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		envoyerMessageServeur(attribut+"<>"+json);
		
	}
	public String recupererMessageServeur() {
		while(it.messageArrivee==false) {
			System.out.println("Attente Message du serveur");
		}
		it.messageArrivee=false;
		return it.chaine_arrivee;
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

	public ArrayList<String> decouper_message(String message,String separateur){
		
		String[] messageSplit = message.split(separateur);


		return new ArrayList<String>( Arrays.asList(messageSplit));


		
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


