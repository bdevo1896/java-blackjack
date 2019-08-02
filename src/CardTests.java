import static org.junit.Assert.*;

import org.junit.Test;


public class CardTests {

	@Test
	public void testCardGeneration() {
		
		Card c = new Card(Suit.Clubs,5);
		
		assertEquals(Suit.Clubs,c.getSuit());
		assertEquals(5,c.getFaceNum());
		assertEquals(13,c.getRank());
		assertEquals(1,c.getSuitNum());
		assertEquals("Clubs",c.getSuitString());
		assertEquals("5",c.getFaceString());
		assertEquals("5 of Clubs",c.toString());
	}
	
	@Test
	public void testIsAceTrue(){
		Card c = new Card(Suit.Clubs,1);
		
		assertEquals(true,c.isAce());
	}
	
	@Test
	public void testIsAceFalse(){
		Card c = new Card(Suit.Clubs,5);
		
		assertEquals(false,c.isAce());
	}
	
	@Test
	public void testIsFaceTrue(){
		Card ace = new Card(Suit.Clubs,1);
		Card jack = new Card(Suit.Clubs,11);
		Card queen = new Card(Suit.Clubs,12);
		Card king = new Card(Suit.Clubs,13);
		
		assertTrue(ace.isFace());
		assertTrue(jack.isFace());
		assertTrue(queen.isFace());
		assertTrue(king.isFace());
	}
	
	@Test
	public void testIsFaceFalse(){
		Card c = new Card(Suit.Clubs,8);
		
		assertFalse(c.isFace());
	}
	
	@Test
	public void testEqualsTrue(){
		Card c = new Card(Suit.Clubs, 5);
		Card b = new Card(Suit.Diamonds, 5);
		
		assertTrue(c.equals(b));
	}
	
	@Test
	public void testEqualsFalse(){
		Card c = new Card(Suit.Clubs, 5);
		Card b = new Card(Suit.Diamonds, 6);
		
		assertFalse(c.equals(b));
	}
	
	@Test
	public void testCompareToOne(){
		
		Card c = new Card(Suit.Clubs, 5);
		Card b = new Card(Suit.Diamonds, 6);
		
		assertEquals(1,b.compareTo(c));
	}
	
	@Test
	public void testCompareToTwo(){
		
		Card c = new Card(Suit.Clubs, 10);
		Card b = new Card(Suit.Diamonds, 2);
		
		assertEquals(-1,b.compareTo(c));
	}
	
	@Test
	public void testCompareToThree(){
		
		Card c = new Card(Suit.Clubs, 6);
		Card b = new Card(Suit.Clubs, 6);
		
		assertEquals(0,b.compareTo(c));
	}
}
