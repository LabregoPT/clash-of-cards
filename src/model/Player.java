package model;

import java.io.Serializable;

/**
 * 
 * @author Andres Orozco && Jhon Mora
 *
 */
public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Deck deck;
	
	private String nickname;
	
	public Player(String nick) {
		nickname = nick;
		deck = new Deck(Deck.TYPE_PARTIAL);
		}
	
	public Deck getDeck() {
		return deck;
	}
	
	public Card selectCard(int i) {
		Card c1 = deck.getRandomCard();
		Card c2 = deck.getRandomCard();
		Card c3 = deck.getRandomCard();
		if( i == 0) {
			return c1;
		}else if(i == 1) {
			return c2;
		}else {
			return c3;
		}
	}
	
	public String getName() {
		return nickname;
	}
	
}

