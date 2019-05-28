package model;
import java.io.*;
import java.util.*;

import data.*;
import data.LinkedList;

/**
 * Class to represent each of the decks in the game
 * @author Jhon Edward Mora / Juan Andres Orozco - Universidad ICESI
 */
public class Deck {
	//Constant fields
	/**To indicate that the deck is of the type full deck.*/
	public final static int TYPE_FULL = 0;
	
	/**To indicate that the deck is of the type full deck.*/
	public final static int TYPE_PARTIAL = 1;
	
	//Attributes
	/**The type of this deck.*/
	private int type;
	
	//Relations
	/**Data structure for when the deck is of type full deck.*/
	private BinarySearchTree full;
	
	/**Data structure for when the deck is of type partial deck.*/
	private LinkedList partial;
	//Methods
	/**
	 * Constructor method. Initialises a new instance of this class. 
	 * @param t the type of this deck.
	 */
	public Deck(int t) {
		type = t;
		if(type == TYPE_FULL) {
			full = new BinarySearchTree(new Comparator<Card>() {
				@Override
				public int compare(Card o1, Card o2) {
					return o1.getName().compareTo(o2.getName());
				}
				
			});
		}else {
			partial = new LinkedList();
		}
	}
	
	public void loadDeck(String filePath) throws IOException{
		BufferedReader bf = new BufferedReader(new FileReader(new File(filePath)));
		String line = bf.readLine();
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		ArrayList<Card> cards = new ArrayList<Card>();
		while(line != null) {
			if(!line.startsWith("#")) {
				if(line.startsWith("ATTRIBUTES:")) {
					String[] parts = line.split("-");
					//ATTRIBUTES: Fire - Wind / Water
					String name = parts[0].split(" ")[1];
					String[] weaknesses = parts[1].trim().split("/")[1].split(",");
					String[] advantages = parts[1].trim().split("/")[0].split(",");
					Attribute a1 = new Attribute(name, -1);
					attributes.add(a1);
				}
				if(line.startsWith("CARDS:")) {
					
				}
			}
			line = bf.readLine();
		}
		bf.close();
	}
	
	public void addCard(Card c) {
		if(type == TYPE_FULL) {
			full.add(c);
		}else {
			partial.add(c);
		}
	}
	
	public Card getRandomCard() {
		if(type == TYPE_FULL) {
			return full.getRandom();
		}else {
			Random rnd = new Random();
			int r = rnd.nextInt(partial.size());
			return partial.get(r);
		}
	}
	
	public void deleteCard(Card c) {
		if(type == TYPE_FULL) {
			full.delete(c);
		}else {
			partial.delete(c);
		}
	}
	
}
