package model;
import java.io.*;

public class Game {
	
	private Player player;

	private Deck collection;
	
	private int wins;
	
	private int losses;
	
	private int draws;
	
	public Game() {
		player = null;
		collection = null;
		wins = 0;
		losses = 0;
		draws = 0;
	}
	
	public void generateCards(String attsPath, String cardsPath) throws IOException{
		collection = new Deck(Deck.TYPE_FULL);
		collection.loadDeck(cardsPath, attsPath);
	}
	
	public void setPlayer(String nn) {
		Player p = new Player(nn);
		player = p;
		Card given = collection.getRandomCard();
		player.getDeck().addCard(given);
		collection.deleteCard(given);
	}
	
	public void battle(Card player, Card system, String toFight, Player p) {
		int result = player.fight(system, toFight);
		if(result < 1) {
			collection.addCard(player);
			p.getDeck().deleteCard(player);
			wins ++;
		}else if(result > 1) {
			p.getDeck().addCard(system);
			collection.deleteCard(system);
			losses++;
		}else {
			draws++;
		}
	}
	
	public void saveGame() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("data/savegame.dat")));
		oos.writeObject(this);
		oos.close();
	}
	
	public Game loadGame() throws IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("data/savegame.dat")));
		Game r = (Game)ois.readObject();
		ois.close();
		return r;
	}
	
	public Deck getDeck() {
		return collection;
	}
	
	public int getWins() {
		return wins;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public int getDraws() {
		return draws;
	}
	
	public Player getPlayer() {
		return player;
	}
}
