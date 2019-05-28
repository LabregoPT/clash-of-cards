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
	 * Represent the attack points as an integer number
	 */
	private int attack;
	/**
	 * Represent the defense points as an integer number
	 */
	private int defense;
	/**
	 * Contructor Method
	 * @param n Represent the card name
	 * @param a Represent the attack points
	 * @param d Represent the defense points
	 */
	public Card(String n,int a,int d) {
		name = n;
		attack=a;
		defense=d;
	}
	/**
	 * This method compare the card in a lexicographic order
	 */

	@Override
	public int compareTo(Card e) {
		return 0;
	}

}