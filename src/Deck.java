import java.util.ArrayList;
import java.util.Random;


/**
 * An instance of this class will hold an array of cards (with 52 cards). This class will also hold 
 * methods to shuffle and distribute cards to an area where it's needed.
 * @author Bryce DeVaughn
 *
 */
public class Deck {
	
	private Card[] cards;
	private ArrayList<Card> removedCards;
	

	public Deck() {
		
		cards = fullDeck();
		removedCards  = new ArrayList<Card>();
	}
	
	/**
	 * This method returns a full, regular deck of cards. The 'rtnArray'  is the actual array that is
	 * created and filled.
	 */
	public Card[] fullDeck(){
		
		Card[] rtnArray = new Card[52];
		
		int cardNum = 0; //This number will be used to place the cards into the 'cards' array correctly.
		
		for(int i = 0; i < 4; i++){
			Suit suitType = null; //This 'Suit' variable will be used to fill in the 'Suit' type for every card that is made.
			
			switch(i){
			
			case 0:
				suitType = Suit.Clubs;
				break;
			case 1:
				suitType = Suit.Diamonds;
				break;
			case 2:
				suitType = Suit.Hearts;
				break;
			case 3:
				suitType = Suit.Spades;
				break;
			}
			
			for(int k = 0; k < 13; k++){
				rtnArray[cardNum] = new Card(suitType,k+1);
				cardNum++;
			}
			
		}
		
		return rtnArray;
	}
	
	/**
	 * This method will shuffle the deck of cards.
	 */
	public void shuffle(){
		
		Random rand = new Random();
		
		for(int i = 0; i < 4; i++){//Shuffles the deck 4 times just to be sure.
			
			for(int k = 0; k < 52; k++){
				int cardOne = rand.nextInt(52);
				int cardTwo = rand.nextInt(52);
				
				Card c = cards[cardOne]; //This variable will be used to refernece to the 'Card' at the index of 'cardOne'.
				
				/*
				 * These statements actually switch the two cards.
				 */
				cards[cardOne]  = cards[cardTwo];
				cards[cardTwo] = c;
			}
			
		}
	}
	
	/**
	 * This method will return a the 'Card' at the front of the deck (at 0 index). The card "dealt" will
	 * be also be referenced in the 'removedCards' ArrayList, to keep track and return to the deck 
	 * later.
	 */
	public Card dealNextCard(){
		
		Card rtnCard = null;
		
		for(int i = 0; i < cards.length; i++){
			Card c = cards[i];
			
			if(c != null){
				rtnCard = c;
				for(int k = 1; k < cards.length; k++){
					cards[k-1] = cards[k];
				}
				cards[51] = null;
				
				removedCards.add(c);
				break;
			}
		}
		
		return rtnCard;
	}
	
	/**
	 * This method returns a card to the deck, if it's not already in the deck.
	 */
	public void returnCard(Card c){
		
		boolean isOutOfDeck = false;//This variable will be used to make sure that 'c' is out of the deck.
		
		/*
		 * This loop will check to see if the 'c' is in the removedCards list, to make sure that it isn't in the deck currently.
		 */
		for(int i = 0; i < removedCards.size(); i++){
			if(c == removedCards.get(i)){
				isOutOfDeck = true;
			}
		}
		
		if(isOutOfDeck){
			/*
			 * This loop will look for the first open spot in the back of the deck.
			 */
			for(int i = 0; i < cards.length; i++){
				if(cards[i] == null){
					cards[i] = c;
					removedCards.remove(c); 
				}
			}
		}
	}
	
	/**
	 * This method returns the number of cards left in the currnet deck.
	 * @return
	 */
	public int getCardsLeft(){
		
		int numOfCards = 0;
		
		for(int i = 0; i < 52; i++){
			if(cards[i] != null)
				numOfCards++;
		}
		
		return numOfCards;
	}
	
	/**
	 * This method will check to see if the 'cards' array is empty.
	 * @return
	 */
	public boolean isEmpty(){
		
		boolean empty = true;
		
		for(int i = 0; i < 52; i++){
			if(cards[i] != null){
				empty = false;
				break;
			}
		}
		
		return empty;
		
	}

	public Card[] getCards() {
		return cards;
	}

	public ArrayList<Card> getRemovedCards() {
		return removedCards;
	}
	
	
}
