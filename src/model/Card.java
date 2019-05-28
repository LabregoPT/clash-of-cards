package model;
/**
 * This class represent the single card
 * @author Andres Orozco && Jhon Edward Mora
 *
 */

public class Card implements Comparable<Card>{
	
	/**
	 * Represents the card name
	 */
	private String name;
	
	
	
	public Card(String n) {
		name = n;
	}
	
	
	/**
	 * This method compares the card in a lexicographic order
	 */
	@Override
	public int compareTo(Card e) {
		return 0;
	}

}