/**
 * An instance of this class will inherit the features of the 'Hand' class. Along with those features,
 * the methods getValue, isSoft, and isBust are available. The method 'getValue' will return the current 
 * value of the hand. The method 'isSoft' will return true if the hand has an ace within, therefore, 
 * making it a "soft" hand. The method 'isBust' will return true if the hand's value exceeds 21.
 * @author Bryce DeVaughn
 *
 */
public class BlackJackHand extends Hand {

	public BlackJackHand() {
		super();
	}

	/**
	 * This method calculates the current value of the hand (List of cards).
	 */
	public int getValue(){
		int rtnValue = 0;

		for(Card c: cards){
			if(c != null ){
				if(c.isAce()){
					rtnValue += 1;
				} else if(c.isFace()){
					rtnValue+=10;
				}else{
					rtnValue += c.getFaceNum();
				}
			}
		}

		return rtnValue;
	}

	/**
	 * This method returns true if the hand is a "soft" hand.
	 */
	public boolean isSoft(){
		boolean soft = false;

		for(Card c: cards){
			if(c != null && c.isAce())
				soft = true;
		}

		return soft;
	}

	/**
	 * This method returns true if the hand is a "bust".
	 */
	public boolean isBust(){
		boolean bust = false;

		int val = getValue();

		if(val > 21){
			bust = true;
		}

		return bust;
	}
}
