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
	
	/**
	 * The location of the image of this card.
	 */
	public String image;
	
	/**The attributes of this card. From 1 to 3 per card.*/
	private Attribute[] attributes;
	
	public Card(String n, String im, Attribute[] a) {
		name = n;
		image = im;
	}
	
	
	/**
	 * This method compares the card in a lexicographic order
	 */
	@Override
	public int compareTo(Card e) {
		return 0;
	}

}