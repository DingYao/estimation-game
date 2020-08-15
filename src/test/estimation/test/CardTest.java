package estimation.test;

import estimation.model.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CardTest will test the following:
 * 	isSameAs method can check whether two cards are identical.
 */
public class CardTest {

   @Test
   public void getSuit() {
       Card cardTest = new Card(Suit.SPADES, Rank.TWO);
       assertEquals(Suit.SPADES, cardTest.getSuit());
       System.out.println("Card get suit Test is successful");
   }

   @Test
   public void getRank() {
       Card twoOfSpade = new Card(Suit.SPADES, Rank.TWO);
       assertEquals(Rank.TWO, twoOfSpade.getRank());
       System.out.println("Card get rank Test is successful");
   }

    @Test
    public void equals() {
        Card twoOfSpade = new Card(Suit.SPADES, Rank.TWO);
        Card twoOfSpade1 = new Card(Suit.SPADES, Rank.TWO);
        assertTrue(twoOfSpade.isSameAs(twoOfSpade1));
        System.out.println("Card equals Test is successful");
    }
}