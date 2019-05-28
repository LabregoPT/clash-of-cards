package model;
/**
 * This class represent each of the cards of the game.
 * @author Andres Orozco && Jhon Edward Mora
 *
 */
public class Card{
	
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
	
	/**
	 * Constructor method.
	 * @param n the name of the card
	 * @param im the location of the image of this card.
	 * @param a the attributes of this card.
	 */
	public Card(String n, String im, Attribute[] a) {
		name = n;
		image = im;
		attributes = a;
	}
	
	/**
	 * Returns the attributes of this card.
	 * @return the attributes of this card.
	 */
	public Attribute[] getAttributes() {
		return attributes;
	}
	
	/**
	 * Makes two cards fight by comparing their attributes. Returns a positive number if this card wins the fight, a negative if it loses and 0 if its a draw.
	 * <br>If both this card and the enemy card have the searched attribute, returns the result of the comparison of them.
	 * <br>If this card has the attribute and the other doesn't, immediately wins the match.
	 * <br>If this card doesn't have the attribute and the enemy does, immediately loses the match.
	 * <br>If none of the cards have the attribute, its a draw.
	 * @param enemy the enemy card to have a match with.
	 * @param attribute the attribute that will rule the match.
	 * @return a positive number if this card wins, negative if it loses, and 0 if its a draw.
	 */
	public int fight(Card enemy, String attribute) {
		int r = 0;
		if(hasAttribute(attribute) && enemy.hasAttribute(attribute)) {
			r = getAttribute(attribute).compareTo(enemy.getAttribute(attribute));
		}else if(hasAttribute(attribute) && !enemy.hasAttribute(attribute)) {
			r = 1;
		}else if(!hasAttribute(attribute) && enemy.hasAttribute(attribute)){
			r = -1;
		}
		return r;
	}
	
	/**
	 * Returns the name of this card.
	 * @return the name of this card.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Determines whether a card has an attribute or not. 
	 * @param attribute attribute to be searched.
	 * @return true if any of the three attributes has a name that matches with the searched attribute, false if not.
	 */
	public boolean hasAttribute(String attribute) {
		if(attributes[0].getName().equals(attribute)) {
			return true;
		}else if(attributes[1] != null) {
			if(attributes[1].getName().equals(attribute)) {
				return true;
			}else {
				return false;
			}
		}else if(attributes[2] != null) {
			if(attributes[2].getName().equals(attribute)) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
	/**
	 * Gets the attribute matching the given name. May return null.
	 * @param attribute the searched attribute name.
	 * @return an attribute with a name matching the searched attribute name.
	 */
	public Attribute getAttribute(String attribute) {
		Attribute toR = null;
		if(attributes[0].getName().equals(attribute)) {
			toR = attributes[0];
		}else {
			if(attributes[1] != null) {
				if(attributes[1].getName().equals(attribute)) {
					toR = attributes[1];
				}else {
					if(attributes[2] != null) {
						if(attributes[2].getName().equals(attribute)) {
							toR = attributes[2];
						}
					}
				}
			}else {
				if(attributes[2] != null) {
					if(attributes[2].getName().equals(attribute)) {
						toR = attributes[2];
					}
				}
			}
		}
		return toR;
	}
	
}