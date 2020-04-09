package Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import Serveur.Client;
import externals.AgentAction;
import externals.InfoAgent;
import externals.InfoBomb;
import externals.Map;
import externals.MapSimplifie;
import externals.StateBomb;
class coordonnee {
	int _x;
	int _y;
	coordonnee(int x, int y){
		_x=x;
		_y=y;
	}
}
public class BombermanGame extends Game {
	Map _map;
	Map _mapdebut;
	ArrayList<Agent> _listeAB;
	ArrayList<InfoBomb> _listeBombe;
	boolean[][]	 _listeMurDestruct;
	int _score;
	String _uniqueID;
	String pseudo;
	String niveau;
	String scoreMax;
	String cheminAvatar;
	String idJoueur;
	Client client;
	public BombermanGame(int maxtour, Map map,Client client,String idJoueur,String pseudo, String niveau, String scoreMax,String cheminAvatar) {
		super(maxtour);
		// TODO Auto-generated constructor stub
		_map=map;
		_mapdebut=map;
		_listeAB = new ArrayList<Agent>();
		_listeBombe = new ArrayList<InfoBomb>();
		_listeMurDestruct = _map.getStart_brokable_walls();
		_uniqueID = UUID.randomUUID().toString();
		_score = 0;
		this.pseudo=pseudo;
		this.niveau=niveau;
		this.scoreMax=scoreMax;
		this.cheminAvatar=cheminAvatar;
		this.client=client;
		this.idJoueur=idJoueur;
	}
	
	

	@Override
	public void initializeGame() {
		// TODO Auto-generated method stub
		FabriqueBomberman fb = new FabriqueBomberman();
		FabriqueRajion fr = new FabriqueRajion();
		FabriqueBird fbb = new FabriqueBird();
		FabriqueEnnemi fe = new FabriqueEnnemi();
		_score = 0;
		_uniqueID = UUID.randomUUID().toString();
		for (InfoAgent ia : _map.getStart_agents()) {
			if(ia.getType()=='B') {
				Agent ab =  fb.creerAgent(ia.getX(), ia.getY(), ia.getAgentAction(), 
						ia.getColor(), ia.isInvincible(), ia.isSick());
				_listeAB.add(ab);
			}
			else if (ia.getType()=='R') {
				Agent ab =  fr.creerAgent(ia.getX(), ia.getY(), ia.getAgentAction(), 
						ia.getColor(), ia.isInvincible(), ia.isSick());
				_listeAB.add(ab);
			}
			else if (ia.getType()=='V') {
				Agent ab =  fbb.creerAgent(ia.getX(), ia.getY(), ia.getAgentAction(), 
						ia.getColor(), ia.isInvincible(), ia.isSick());
				_listeAB.add(ab);
			}
			else if (ia.getType()=='E') {
				Agent ab =  fe.creerAgent(ia.getX(), ia.getY(), ia.getAgentAction(), 
						ia.getColor(), ia.isInvincible(), ia.isSick());
				_listeAB.add(ab);
			}
		}
	}

	@Override
	public boolean gameContinue() {
		// TODO Auto-generated method stub
		for (Agent a : _listeAB) {
			if(a instanceof AgentBomberman) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		_listeAB.clear();
		_listeBombe.clear();
		_listeMurDestruct=_mapdebut.getStart_brokable_walls();
		_map=_mapdebut;
		System.out.println("game over, Score : "+_score);
		returnData();
	}
	
	public void returnData() {
		// _score de disponible, _map.getFilename() pour le nom de la map ?
		int idPartie =  (int)(Math.random() * 100000)+15000;
		System.out.println("ID DU JOUEUR "+idJoueur);
		System.out.println("SCORE "+_score);
		System.out.println("ID PARTIE "+idPartie);
		client.envoyerMessageServeur("resultatPartie<>idJoueur:"+idJoueur+",score:"+_score+",idPartie:"+idPartie+",nomMap:"+_map.getFilename());
	}

	@Override
	public void takeTurn() {
		maj_bomb();
		// TODO Auto-generated method stub
		int n;
		AgentBomberman b = null;
		_score += 10;
		for(Agent ab : _listeAB) {
			if(ab instanceof AgentBomberman) {
				b=(AgentBomberman) ab;
			}
		}
		MapSimplifie mapsimplife = new MapSimplifie(getMap().getFilename(), getMap().getSizeX(), getMap().getSizeY(), getMap().get_walls(), getMap().getStart_brokable_walls());
		client.envoyerObjetServeur("dataMap",mapsimplife);
		AgentAvecMouvement aam = null;
		boolean reponse;
		for (Agent ab : _listeAB) {
	        n = (int)(Math.random() * 6);
	        if(ab instanceof AgentBomberman) {
		        switch (n) {
				case 0:
					 aam = new AgentAvecMouvement((AgentBomberman)ab,AgentAction.MOVE_UP);
					client.envoyerObjetServeur("verifieDeplacement", aam);
					 reponse =Boolean.valueOf(client.recupererMessageServeur());
					System.out.println(reponse);
					if(reponse) {
						moveAgent(ab,AgentAction.MOVE_UP);
					}
					//if(isLegalMove(ab,AgentAction.MOVE_UP)) {moveAgent(ab,AgentAction.MOVE_UP);}
					break;
				case 1: 
					 aam = new AgentAvecMouvement((AgentBomberman)ab,AgentAction.MOVE_DOWN);
					client.envoyerObjetServeur("verifieDeplacement", aam);
					 reponse =Boolean.valueOf(client.recupererMessageServeur());
					System.out.println(reponse);
					if(reponse) {
						moveAgent(ab,AgentAction.MOVE_DOWN);
					}					break;
				case 2:
					 aam = new AgentAvecMouvement((AgentBomberman)ab,AgentAction.MOVE_LEFT);
					client.envoyerObjetServeur("verifieDeplacement", aam);
					 reponse =Boolean.valueOf(client.recupererMessageServeur());
					System.out.println(reponse);
					if(reponse) {
						moveAgent(ab,AgentAction.MOVE_LEFT);
					}					break;
				case 3:
					 aam = new AgentAvecMouvement((AgentBomberman)ab,AgentAction.MOVE_RIGHT);
					client.envoyerObjetServeur("verifieDeplacement", aam);
					 reponse =Boolean.valueOf(client.recupererMessageServeur());
					System.out.println(reponse);
					if(reponse) {
						moveAgent(ab,AgentAction.MOVE_RIGHT);
					}					break;
				case 4: 
					if(isLegalMove(ab,AgentAction.STOP)) {moveAgent(ab,AgentAction.STOP);}
				case 5:
					if(peut_poserBombe()) {
						_listeBombe.add(new InfoBomb(ab.getX(), ab.getY(),2,StateBomb.Step1,(AgentBomberman)ab));
					}
				default:
					break;
				}
	        }
	        else {
	        	AgentAction move = strategieEnnemis(ab, b);
	        	if(isLegalMove(ab,move)){moveAgent(ab,move);}
	        }
		}
		for (Iterator<Agent> iterator = _listeAB.iterator(); iterator.hasNext();) {
			Agent agent = (Agent) iterator.next();
			if(agent instanceof AgentBomberman) {
				if(ennemiSurBomberman((AgentBomberman)agent, _listeAB)) {
					iterator.remove();
				}
			}
		}


	}
	public InfoAgent agentBtoInfoA(AgentBomberman ab) {
		InfoAgent ia = new InfoAgent(ab.getX(),ab.getY(),ab.getAgentAction(),'B',
				ab.getColor(),ab.isInvincible(),ab.isSick());
		return ia;
	}
	public InfoAgent agentRtoInfoA(AgentRajion ar) {
		InfoAgent ia = new InfoAgent(ar.getX(),ar.getY(),ar.getAgentAction(),'R',
				ar.getColor(),ar.isInvincible(),ar.isSick());
		return ia;
	}
	public InfoAgent agentBitoInfoA(AgentBird abi) {
		InfoAgent ia = new InfoAgent(abi.getX(),abi.getY(),abi.getAgentAction(),'V',
				abi.getColor(),abi.isInvincible(),abi.isSick());
		return ia;
	}
	public InfoAgent agentEtoInfoA(AgentEnnemi ae) {
		InfoAgent ia = new InfoAgent(ae.getX(),ae.getY(),ae.getAgentAction(),'E',
				ae.getColor(),ae.isInvincible(),ae.isSick());
		return ia;
	}
	
	
	public boolean isLegalMove(Agent a, AgentAction aa) {
		switch (aa) {
		case MOVE_UP:
			return !(_map.get_walls()[a.getX()][a.getY()+1] ||_map.getStart_brokable_walls()[a.getX()][a.getY()+1] );
		case MOVE_DOWN:
			return !(_map.get_walls()[a.getX()][a.getY()-1] ||_map.getStart_brokable_walls()[a.getX()][a.getY()-1] );
		case MOVE_LEFT:
			return !(_map.get_walls()[a.getX()-1][a.getY()] ||_map.getStart_brokable_walls()[a.getX()-1][a.getY()] );
		case MOVE_RIGHT:
			return !(_map.get_walls()[a.getX()+1][a.getY()] ||_map.getStart_brokable_walls()[a.getX()+1][a.getY()] );
		case PUT_BOMB:
			return true;
		case STOP:
			return true;
		default:
			return false;
		}
		
	}
	public void moveAgent(Agent a, AgentAction aa) {
		switch (aa) {
		case MOVE_UP:
			a.setY(a.getY()+1);
			break;
		case MOVE_DOWN:
			a.setY(a.getY()-1);
			break;
		case MOVE_LEFT:
			a.setX(a.getX()-1);
			break;
		case MOVE_RIGHT:
			a.setX(a.getX()+1);
			break;
		case STOP:
			break;
		default:
			break;
		}
	}
	
	public boolean ennemiSurBomberman(AgentBomberman ab, ArrayList<Agent> listeAgent) {
		
		for (Agent ennemi : listeAgent) {
			if(ab!=ennemi) {
				if(ab.getX()==ennemi.getX() && ab.getY()==ennemi.getY()) {
					return true;
				}
			}
		}
		

		return false;
		
	}
	public int distance(Agent a, Agent b) {
		return Math.abs(a.getX()-b.getX())+Math.abs(a.getY()-b.getY());
	}
	public int distance(int xa, int ya, int xb, int yb) {
		return Math.abs(xa-xb)+Math.abs(ya-yb);
	}
	public ArrayList<coordonnee> voisins(Agent a){
		ArrayList<coordonnee> liste_coordonnee = new ArrayList<coordonnee>();
		
		if(!isLegalMove(a, AgentAction.MOVE_LEFT)) {
			liste_coordonnee.add(new coordonnee(a.getX()-1,a.getY()));
		}
		if(!isLegalMove(a, AgentAction.MOVE_RIGHT)) {
			liste_coordonnee.add(new coordonnee(a.getX()+1,a.getY()));
		}
		if(!isLegalMove(a, AgentAction.MOVE_UP)) {
			liste_coordonnee.add(new coordonnee(a.getX(),a.getY()+1));
		}
		if(!isLegalMove(a, AgentAction.MOVE_DOWN)) {
			liste_coordonnee.add(new coordonnee(a.getX(),a.getY()-1));
		}
		return liste_coordonnee;
	}
	
	public ArrayList<AgentAction> liste_AA_dispo(Agent a){
		ArrayList<AgentAction> liste_aa_dispo = new ArrayList<AgentAction>();
		
		if(!isLegalMove(a, AgentAction.MOVE_LEFT)) {
			liste_aa_dispo.add(AgentAction.MOVE_LEFT);
		}
		if(!isLegalMove(a, AgentAction.MOVE_RIGHT)) {
			liste_aa_dispo.add(AgentAction.MOVE_RIGHT);		
		}
		if(!isLegalMove(a, AgentAction.MOVE_UP)) {
			liste_aa_dispo.add(AgentAction.MOVE_UP);		
		}
		if(!isLegalMove(a, AgentAction.MOVE_DOWN)) {
			liste_aa_dispo.add(AgentAction.MOVE_DOWN);
		}
		return liste_aa_dispo;
	}
	public AgentAction strategieEnnemis(Agent ennemi, AgentBomberman ab) {
		class cout{
			int distance_parc;
			int distance_esti;
		}
		int distance = 10000;
		AgentAction ac = AgentAction.STOP;
		
		if(isLegalMove(ennemi, AgentAction.MOVE_RIGHT)) {
			if(distance(ennemi.getX()+1,ennemi.getY(),ab.getX(),ab.getY())<distance) {
				distance = distance(ennemi.getX()+1,ennemi.getY(),ab.getX(),ab.getY());
				ac=AgentAction.MOVE_RIGHT;
			}
		}
		if(isLegalMove(ennemi, AgentAction.MOVE_LEFT)) {
			if(distance(ennemi.getX()-1,ennemi.getY(),ab.getX(),ab.getY())<distance) {
				distance= distance(ennemi.getX()-1,ennemi.getY(),ab.getX(),ab.getY());
				ac=AgentAction.MOVE_LEFT;
			}
		}
		if(isLegalMove(ennemi, AgentAction.MOVE_UP)) {
			if(distance(ennemi.getX(),ennemi.getY()+1,ab.getX(),ab.getY())<distance) {
				distance= distance(ennemi.getX(),ennemi.getY()+1,ab.getX(),ab.getY());
				ac=AgentAction.MOVE_UP;
			}
		}	
		if(isLegalMove(ennemi, AgentAction.MOVE_DOWN)) {
			if(distance(ennemi.getX(),ennemi.getY()-1,ab.getX(),ab.getY())<distance) {
				distance= distance(ennemi.getX(),ennemi.getY()-1,ab.getX(),ab.getY());
				ac=AgentAction.MOVE_DOWN;
			}
		}
		return ac;
	}
	public void maj_bomb() {
		for (Iterator<InfoBomb> iterator = _listeBombe.iterator(); iterator.hasNext();) {
			InfoBomb bombe = (InfoBomb) iterator.next();
			
			if(bombe.getStateBomb()==StateBomb.Boom) {
				for(int i=1; i<bombe.getRange()+1;i++) {
					if(est_murDestruct(bombe.getX()+1*i,bombe.getY())) {
						_score += 20;
						_listeMurDestruct[bombe.getX()+1*i][bombe.getY()]=false;
					}
					if(est_murDestruct(bombe.getX(),bombe.getY()+1*i)) {
						_score += 20;
						_listeMurDestruct[bombe.getX()][bombe.getY()+1*i]=false;
					}
					if(est_murDestruct(bombe.getX()-1*i,bombe.getY())) {
						_score += 20;
						_listeMurDestruct[bombe.getX()-1*i][bombe.getY()]=false;
					}
					if(est_murDestruct(bombe.getX(),bombe.getY()-1*i)) {
						_score += 20;
						_listeMurDestruct[bombe.getX()][bombe.getY()-1*i]=false;
					}
				}
				iterator.remove();
			}
			else
				bombe.nextState();
			}	
	
	}
	public boolean est_murDestruct(int x, int y) {
		if((x<0 || x>=_map.getSizeX()) || (y<0 || y>=_map.getSizeY())) {

			return false;
		}

		return _listeMurDestruct[x][y];
	}
	
	public boolean peut_poserBombe() {
		if(_listeBombe.size()<_listeAB.size()) {
			return true;
		}
		else {
			return false;
		}
	}
	public Map getMap() {return _map;}
	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * @return the niveau
	 */
	public String getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau the niveau to set
	 */
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	/**
	 * @return the classement
	 */
	public String getScoreMax() {
		return scoreMax;
	}

	/**
	 * @param classement the classement to set
	 */
	public void setClassement(String scoremax) {
		this.scoreMax = scoremax;
	}
	
	/**
	 * @return the cheminAvatar
	 */
	public String getCheminAvatar() {
		return cheminAvatar;
	}



	/**
	 * @param cheminAvatar the cheminAvatar to set
	 */
	public void setCheminAvatar(String cheminAvatar) {
		this.cheminAvatar = cheminAvatar;
	}



	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}



	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}



	/**
	 * @return the idJoueur
	 */
	public String getIdJoueur() {
		return idJoueur;
	}



	/**
	 * @param idJoueur the idJoueur to set
	 */
	public void setIdJoueur(String idJoueur) {
		this.idJoueur = idJoueur;
	}



	public ArrayList<Agent> getListeAgent(){return _listeAB;}
	public ArrayList<InfoBomb> getListeBombe(){return _listeBombe;}
}