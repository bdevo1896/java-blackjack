import static org.junit.Assert.*;

import org.junit.Test;


public class HandTests {

	@Test
	public void testGeneration() {

		BlackJackHand h = new BlackJackHand();

		assertEquals(0,h.getNumOfCards());
		assertEquals(0,h.getValue());
		assertEquals(false,h.isBust());
		assertEquals(false,h.isSoft());
	}

	@Test
	public void testAddCard(){
		BlackJackHand h = new BlackJackHand();

		h.addCard(new Card(Suit.Clubs,2));

		assertEquals(1,h.getNumOfCards());
	}

	@Test
	public void testRemoveCard(){
		BlackJackHand h = new BlackJackHand();
		Card c = new Card(Suit.Clubs,2);
		h.addCard(c);
		h.removeCard(c);
		
		boolean cardRemoved = true;
		
		Card[] cards = h.getCards();
		
		for(Card testC: cards){
			if(c == testC){
				cardRemoved = false;
			}
		}
		
		assertTrue(cardRemoved);
	}
	
	@Test
	public void testBustTrue(){
		BlackJackHand h = new BlackJackHand();
		Card[] cards = new Card[]{new Card(Suit.Clubs,13),new Card(Suit.Diamonds,13),new Card(Suit.Hearts,5)};
		
		for(Card c: cards){
			h.addCard(c);
		}
		
		assertTrue(h.isBust());
	}
	
	@Test
	public void testBustFalse(){
		BlackJackHand h = new BlackJackHand();
		Card[] cards = new Card[]{new Card(Suit.Clubs,5),new Card(Suit.Diamonds,5)};
		
		for(Card c: cards){
			h.addCard(c);
		}
		
		assertFalse(h.isBust());
		
		h.addCard(new Card(Suit.Clubs,11));
		
		assertFalse(h.isBust());
	}
	
	@Test
	public void testSoftTrue(){
		BlackJackHand h = new BlackJackHand();
		Card[] cards = new Card[]{new Card(Suit.Clubs,1),new Card(Suit.Diamonds,5)};
		
		for(Card c: cards){
			h.addCard(c);
		}
		
		assertTrue(h.isSoft());
	}
	
	@Test
	public void testSoftFalse(){
		BlackJackHand h = new BlackJackHand();
		Card[] cards = new Card[]{new Card(Suit.Clubs,5),new Card(Suit.Diamonds,5)};
		
		for(Card c: cards){
			h.addCard(c);
		}
		
		assertFalse(h.isSoft());
	}

}
