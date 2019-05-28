package model;
/**
 * This class represent the single card
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
	}
	
	public Attribute[] getAttributes() {
		return attributes;
	}
	
	public int fight(Card enemy, String attribute) {
		int r = 0;
		if(hasAttribute(attribute) && enemy.hasAttribute(attribute)) {
			r = getAttribute(attribute).compareTo(enemy.getAttribute(attribute));
		}else if(hasAttribute(attribute) && !enemy.hasAttribute(attribute)) {
			r = 1;
		}else {
			r = -1;
		}
		return r;
	}
	
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