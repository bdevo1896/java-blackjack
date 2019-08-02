import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * An instance of this class will allow for a Black Jack card to be made. Each of these instances will
 * have a reference/handle to an enumerator called to Suit and an 'int' called face, to represent the 
 * face value of the card.
 * @author Bryce DeVaughn
 *
 */
public class Card implements Comparable<Card>{

	private int face;
	private Suit suit;
	private static final int ACE = 1, JACK = 11,QUEEN = 12, KING = 13;
	private Image img;

	public Card(Suit suit, int face){
		this.suit = suit;
		this.face = face;
		try {
			img = ImageIO.read(new File("cards//"+getSuitString()+face+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will return the rank of the card by converting the Suit enumeration value to its actual 
	 * suit value. Then it will calculate a value based off of the face value, and sum the suit value with 
	 * it.
	 * @return
	 */
	public int getRank(){
		int rank = 0;
		int suitValue = 0;

		/*
		 * This 'switch' statement will assign values to 'suitValue' based off of the enumeration value.
		 */
		switch(suit){
		case Clubs:
			suitValue = 1;
			break;
		case Hearts:
			suitValue = 3;
			break;
		case Diamonds:
			suitValue = 2;
			break;
		case Spades:
			suitValue = 4;
			break;

		}

		/*
		 * This 'if' block will check to see if the card is an ace or not, then calculate the rank.
		 */
		if(face != ACE){
			rank = (face-2)*4+suitValue;
		} else{
			rank = 48 + suitValue;
		}

		return rank;
	}


	/**
	 * This method will compare the ranks of another card to this one. Based on the ranks, a number will 
	 * be returned. 1 for this card having a higher rank, 0 for the ranks being the same, and -1 for this 
	 * card's rank to be lower than the other card.
	 */
	@Override
	public int compareTo(Card o) {

		if(this.getRank() > o.getRank()){
			return 1;
		}
		else if(this.getRank() < o.getRank()){
			return -1;
		}
		else{
			return 0;
		}
	}

	/**
	 * This method will return the associated number with the suit. 1 for Clubs,2 for Diamonds, 3 for 
	 * Hearts, and 4 for Spades.
	 * @return
	 */
	public int getSuitNum(){

		int suitValue = 0;

		/*
		 * This 'switch' statement will assign values to 'suitValue' based off of the enumeration value.
		 */
		switch(suit){
		case Clubs:
			suitValue = 1;
			break;
		case Hearts:
			suitValue = 3;
			break;
		case Diamonds:
			suitValue = 2;
			break;
		case Spades:
			suitValue = 4;
			break;

		}

		return suitValue;
	}

	/**
	 * This method will return the "String" form of the Suit enumeration.
	 * @return
	 */
	public String getSuitString(){
		String rtnStr = "";

		/*
		 * This 'switch' statement will assign values to 'suitValue' based off of the enumeration value.
		 */
		switch(suit){
		case Clubs:
			rtnStr = "Clubs";
			break;
		case Hearts:
			rtnStr = "Hearts";
			break;
		case Diamonds:
			rtnStr = "Diamonds";
			break;
		case Spades:
			rtnStr = "Spades";
			break;

		}

		return rtnStr;
	}

	/**
	 * This returns the current face value as a String.
	 * @return
	 */
	public String getFaceString(){
		String rtnStr = "";

		/*
		 * This 'switch' statement will compare the 'face' value to the constants. The default case will be
		 * "Not a face.". 
		 */
		switch(face){
		case ACE:
			rtnStr = "Ace";
			break;
		case JACK:
			rtnStr = "Jack";
			break;
		case QUEEN:
			rtnStr = "Queen";
			break;
		case KING:
			rtnStr = "King";
			break;
		default:
			rtnStr = ""+face;
			break;
		}
		return rtnStr;
	}

	/**
	 * This will return a String representation of the current card.
	 */
	public String toString(){
		String rtnStr = "";

		/*
		 * This 'switch' statement will compare the 'face' value to the constants. The default case will be
		 * "Not a face.". 
		 */
		switch(face){
		case ACE:
			rtnStr = "Ace of "+this.getSuitString();
			break;
		case JACK:
			rtnStr = "Jack of "+this.getSuitString();
			break;
		case QUEEN:
			rtnStr = "Queen of "+this.getSuitString();
			break;
		case KING:
			rtnStr = "King of "+this.getSuitString();
			break;
		default:
			rtnStr = face+" of "+this.getSuitString();
			break;
		}
		return rtnStr;
	}

	/**
	 * This method checks to see if the face value of this card to another is the same.
	 * @return
	 */
	public boolean equals(Card other){
		boolean rtn = false;

		if(face == other.getFaceNum()){
			rtn = true;
		}

		return rtn;
	}

	/**
	 * This method checks to see if the current card is an Ace.
	 * @return
	 */
	public boolean isAce(){
		boolean rtn = false;

		if(face == ACE){
			rtn = true;
		}

		return rtn;
	}

	/**
	 * This method will check if the current card is a face card.
	 * @return
	 */
	public boolean isFace(){
		boolean rtn = false;

		/*
		 * This 'switch' will compare the 'face' value to every constant; will change 'rtn' to true if
		 * any of the cases are met.
		 */
		switch(face){
		case ACE:
			rtn = true;
			break;
		case JACK:
			rtn = true;
			break;
		case QUEEN:
			rtn = true;
			break;
		case KING:
			rtn = true;
			break;
		}
		
		return rtn;
	}

	public int getFaceNum() {
		return face;
	}

	public Suit getSuit() {
		return suit;
	}

	public Image getImg() {
		return img;
	}

	
}
