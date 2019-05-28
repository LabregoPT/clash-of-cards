package model;

import java.util.List;

/**
 * Class to represent each of the attributes in the game
 * @author Jhon Edward Mora - Juan Andres Orozco - Universidad ICESI
 */
public class Attribute implements Comparable<Attribute> {
	//Attributes
	/**The name of the attribute.*/
	private String name;
	
	/**The power of the attribute.*/
	private int power;
	
	//Relations
	/**List of attributes this attribute has an advantage on. Higher or equals than one, but less than 3.*/
	private List<Attribute> adv;
	/**List of attributes this attribute has a disadvantage on. Higher or equals than one, but less than 3.*/
	private List<Attribute> dadv;
	
	//Methods
	/**
	 * Constructor method.
	 * @param n the name of the attribute
	 * @param p the power of the attribute
	 */
	public Attribute(String n, int p) {
		name = n;
		power = p;
	}

	/**
	 * Sets the list of advantages to the one in the parameter.
	 * @param a the list of advantages
	 */
	public void setAdv(List<Attribute> a) {
		adv = a;
	}
	
	/**
	 * Sets the list of disadvantages to the one in the parameter.
	 * @param a the list of disadvantages
	 */
	public void setDadv(List<Attribute> a) {
		dadv = a;
	}
	
	/**
	 * Returns the name of the attribute.
	 * @return the name of the attribute.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the power of the attribute
	 * @return the power of the attribute
	 */
	public int getPower() {
		return power;
	}
	
	/**
	 * Returns the list of advantages
	 * @return the list of advantages
	 */
	public List<Attribute> getAdv(){
		return adv;
	}
	
	/**
	 * Returns the list of disadvantages
	 * @return the list of disadvantages
	 */
	public List<Attribute> getDadv(){
		return dadv;
	}
	
	/**
	 * Compares an attribute to another by substracting the argument's power to the power of this attribute.
	 * <b>If the argument's attribute is in disadvantage against this attribute, the argument's power will be reduced to its 75%.
	 * <b>If the argument's attribute is in advantage against this attribute, the power of this attribute will be reduced to its 75%.
	 * <b>Else the program only performs a substraction of both powers and returns it.
	 */
	public int compareTo(Attribute e) {
		int r = 0;
		if(adv.contains(e)) {
			r = (int) (power - (e.getPower()*0.75));
		}else if(dadv.contains(e)) {
			r = (int) ((power*0.75) - e.getPower());
		}else {
			r = power-e.getPower();
		}
		return r;
	}
}
