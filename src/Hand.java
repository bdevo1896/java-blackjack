
/**
 * An instance of this class would be able to hold 'Card' objects in the cards list. It is possible to add
 * cards to it with the 'addCard' method. There are also methods for clearing and sorting the list of 
 * cards representing the hand.
 * @author Bryce DeVaughn
 *
 */
public abstract class Hand {

	protected Card[] cards;

	public Hand() {
		cards = new Card[10];
	}

	/**
	 * This method adds a specified card to the list of cards.
	 */
	public void addCard(Card c){

		for(int i = 0; i < cards.length; i++){
			if(cards[i] == null){
				cards[i] = c;
				break;
			}
		}
	}

	/**
	 * This method will remove a specified card.
	 */
	public Card removeCard(Card c){
		for(int i = 0; i < cards.length; i++){
			if(c == cards[i]){
				cards[i] = null;
				for(int k = cards.length-1; k > i; k--){
					cards[k-1] = cards[k];
				}
			}
		}
		
		return c;
	}



	/**
	 * This method removes all cards currently in the list. This method also returns a list of the cards
	 * removed.
	 */
	public Card[] clear(){

		Card[] rtnList = new Card[cards.length]; //This will be the list that is returned.

		/*
		 * This loop empties the list by replacing each element with null while placing those elemnts into the 'rtnList'.
		 */
		for(int i = 0; i < cards.length; i++){
			rtnList[i] = cards[i];
			cards[i] = null;
		}

		return rtnList;
	}

	/**
	 * This method will sort all cards, in the list, by the face value of them. (This method is a 
	 * modified Bubble Sort)
	 */
	public void sortByFaceValue(){

		for (int i = (cards.length - 1); i >= 0; i--){
			for (int j = 1; j <= i; j++){

				/*
				 * This is where the face values are compared to see which is larger.
				 */
				if (cards[j-i].getFaceNum() > cards[j].getFaceNum())
				{
					/*
					 * This is where the switch actually happens.
					 */
					Card temp = cards[j-i];
					cards[j-i] = cards[j];
					cards[j] = temp;
				} 
			} 
		} 
	}

	/**
	 * This method will sort all cards, in the list, by their ranks.(This method is a 
	 * modified Bubble Sort)
	 */
	public void sortByRank(){

		for (int i = (cards.length - 1); i >= 0; i--){
			for (int j = 1; j <= i; j++){

				/*
				 * The two side-by-side elements are compared and if one is returned, that tells the computer to switch the first element with the second.
				 */
				if (cards[j-i].compareTo(cards[j]) == 1)
				{
					/*
					 * This is where the switch actually happens.
					 */
					Card temp = cards[j-i];
					cards[j-i] = cards[j];
					cards[j] = temp;
				} 
			} 
		}
	}

	/**
	 * Returns the number of cards in the current hand.
	 */
	public int getNumOfCards(){
		int rtnValue = 0;

		for(Card c: cards){
			if(c != null)
				rtnValue++;
		}

		return rtnValue;
	}

	public Card[] getCards() {
		return cards;
	}
}
