package Game;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import Connexion.Connexion;
import Serveur.Client;
import externals.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ViewCommand vc = new ViewCommand();

		//SimpleGame sg1 = new SimpleGame(50);
		/*JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);
		File selectedFile = jfc.getSelectedFile();

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			System.out.println(selectedFile.getAbsolutePath());
		}
		*/
		@SuppressWarnings("unused")
		Map map1;
		Client client = new Client("localhost",10000);
		Connexion c = new Connexion();
		
		
		
		try {
			c.setVisible(true);
			while(!c.getConnecte()) {}
			String mail_joueur = c.getMailJoueur();
			client.envoyerMessageServeur("getInformationJoueur<>"+mail_joueur);
			String donnee_joueurString = client.recupererMessageServeur();
			donnee_joueurString = client.decouper_message(donnee_joueurString, "<>").get(1);
			Hashtable<String,String> donnee_joueur = client.recuperer_data(donnee_joueurString);
			
			String pseudo_joueur = donnee_joueur.get("pseudo");
			String niveau_joueur = donnee_joueur.get("niveau");
			String scoreMax = donnee_joueur.get("score_max");
			String cheminAvatar = donnee_joueur.get("cheminAvatar");
			String idJoueur = donnee_joueur.get("idJoueur");
			System.out.println("ID DU JOUEUR TEST "+idJoueur);
			map1 = new Map("src/layouts/niveau2.lay");
			BombermanGame bg = new BombermanGame(1000,map1,client,idJoueur,pseudo_joueur,niveau_joueur,scoreMax,cheminAvatar);
			try {
				ControleurBombermanGame cbg = new ControleurBombermanGame(bg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		 
		

		/*
		SimpleGame sg1 = new SimpleGame(50);
		ControleurSimpleGame csg = new ControleurSimpleGame(sg1);
		*/
		
		/*
		sg1.init();
		sg1.run();
		sg1.step();
		*/
		
	}

}
