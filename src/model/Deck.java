package model;
import java.io.*;
import java.util.*;

import data.*;
import data.LinkedList;

/**
 * Class to represent each of the decks in the game
 * @author Jhon Edward Mora / Juan Andres Orozco - Universidad ICESI
 */
public class Deck implements Serializable{
	
	private static final long serialVersionUID = 1L;

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
	
	/**The total of attributes in this deck.*/
	private List<Attribute> atts;
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
			atts = new ArrayList<Attribute>();
		}else {
			partial = new LinkedList();
		}
	}
	
	public void loadDeck(String cardsPath, String attPath) throws IOException, NumberFormatException, IndexOutOfBoundsException{
		BufferedReader attReader = new BufferedReader(new FileReader(new File(attPath)));
		BufferedReader cardReader = new BufferedReader(new FileReader(new File(cardsPath)));
		//Create attributes
		String attLine = attReader.readLine();
		while(attLine != null) {
			//#NAME / Advantage / Disadvantage
			if(!attLine.startsWith("#")) {
				String[] parts = attLine.trim().split("/");
				String name = parts[0];
				String adv = parts[1];
				String dadv = parts[2];
				Attribute att = new Attribute(name);
				Attribute aadv = new Attribute(adv);
				Attribute adadv = new Attribute(dadv);
				att.setAdv(aadv);
				att.setDadv(adadv);
				atts.add(att);
			}
			attLine = attReader.readLine();
		}
		//Create cards
		String cardLine = cardReader.readLine();
		while(cardLine != null) {
			if(!cardLine.startsWith("#")) {
				//#Name - Attribute 1 / Power - Attribute 2 / Power - Attribute 3 / Power
				String[] parts = cardLine.trim().split("-");
				//#Name-Attribute 1/Power-Attribute 2/Power-Attribute 3/Power
				String name = parts[0];
				Attribute[] atts = new Attribute[3];
				if(parts.length == 2) {
					String att1 = parts[1];
					String[] pts = att1.split("/");
					Attribute a = new Attribute(pts[0], Integer.parseInt(pts[1]));
					atts[0] = a;
				}if(parts.length == 3) {
					String att2 = parts[2];
					String[] pts = att2.split("/");
					Attribute a = new Attribute(pts[0], Integer.parseInt(pts[1]));
					atts[1] = a;
				}if(parts.length == 4) {
					String att3 = parts[3];
					String[] pts = att3.split("/");
					Attribute a = new Attribute(pts[0], Integer.parseInt(pts[1]));
					atts[2] = a;
				}
				Card c = new Card(name, "src/images/"+name+".jpg", atts);
				full.add(c);
			}
			cardLine = cardReader.readLine();
		}
		attReader.close();
		cardReader.close();
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
	
	public String getRandomAttribute() {
		Random rnd = new Random();
		String rt = atts.get(rnd.nextInt(atts.size())).getName();
		return rt;
	}
}
