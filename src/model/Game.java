package model;
import java.io.*;
import java.util.*;

public class Game {
	
	private List<Player> players;

	private Deck collection;
	
	public Game() {
		players = new ArrayList<Player>();
		collection = new Deck(Deck.TYPE_FULL);
	}
	
	public void addPlayer(String nn) {
		Player p = new Player(nn);
		players.add(p);
	}
	
	public void battle(Player p, int i) {
		Card player = p.selectCard(i);
		Card system = collection.getRandomCard();
		String toFight = collection.getRandomAttribute();
		int result = player.fight(system, toFight);
		if(result < 1) {
			collection.addCard(player);
			p.getDeck().deleteCard(player);
		}else if(result > 1) {
			p.getDeck().addCard(system);
			collection.deleteCard(system);
		}
	}
	
	public void saveGame() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("data/players.dat")));
		oos.writeObject(players);
		oos.close();
		ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(new File("data/deck.dat")));
		oos1.writeObject(collection);
		oos1.close();
	}
}
