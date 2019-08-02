import static org.junit.Assert.*;

import org.junit.Test;


public class DeckTests {

	@Test
	public void testGeneration() {
		Deck d = new Deck();
		
		assertEquals(52,d.getCardsLeft());
		assertEquals(false,d.isEmpty());
	}
	
	@Test
	public void testFillingOfCards(){
		
		/*
		 * These next four 'int' variables will count the number of each suit to see if the deck is made correctly.
		 */
		int clubs = 0;
		int diamonds = 0;
		int hearts = 0;
		int spades = 0;
		
		Deck d = new Deck();
		
		Card[] testList = d.fullDeck();
		
		for(int i = 0; i < 52; i++){
			
			switch(testList[i].getSuit()){
			case Clubs:
				clubs++;
				break;
			case Hearts:
				hearts++;
				break;
			case Diamonds:
				diamonds++;
				break;
			case Spades:
				spades++;
				break;
			}
		}
		
		assertEquals("Clubs: "+clubs+" Hearts: "+hearts+" Diamonds: "+diamonds+" Spades: "+spades,true,clubs == 13 && diamonds == 13 && hearts == 13 && spades == 13);
		
		
	}
	
	@Test
	public void testDealNextCard(){
		Deck d = new Deck();
		
		d.dealNextCard();
		
		assertEquals(null,d.getCards()[51]);
	}
	
	@Test
	public void testReturnCard(){
		Deck d = new Deck();
		
		Card c = d.dealNextCard();
				
		d.returnCard(c);
		
		assertEquals(c,d.getCards()[51]);
		
	}
	
	@Test
	public void testShuffle(){
		Deck d = new Deck();
		d.shuffle();
		
		Deck c = new Deck();
		Card[] testList = c.getCards();
		
		for(int i = 0; i < d.getCards().length; i++){
			assertTrue(testList[i] != d.getCards()[i]);
		}
	}

}
