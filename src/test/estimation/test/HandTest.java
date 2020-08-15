package estimation.test;

import estimation.model.Card;
import estimation.model.Hand;
import estimation.model.Rank;
import estimation.model.Suit;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * HandTest will test the following :
 * 	cards will be able to be add to hand object
 * 	able to remove a card from a hand by both index and the card itself.
 */
public class HandTest {

    @Test
    public void HandGetSetTest() {
        Card card1 = new Card(Suit.SPADES, Rank.TWO);
        Card card2 = new Card(Suit.CLUBS, Rank.ACE);
        Card card3 = new Card(Suit.DIAMONDS, Rank.THREE);
        Hand hand = new Hand();
        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);
        assertTrue(card1.isSameAs(hand.getCard(0)));
        assertTrue(card2.isSameAs(hand.getCard(1)));
        assertTrue(card3.isSameAs(hand.getCard(2)));
        System.out.println("Hand GetSet Test is successful");
    }

    @Test
    public void HandRemoveTest() {
        Card card1 = new Card(Suit.SPADES, Rank.TWO);
        Card card2 = new Card(Suit.CLUBS, Rank.ACE);
        Card card3 = new Card(Suit.DIAMONDS, Rank.THREE);
        Card card4 = new Card(Suit.HEARTS, Rank.FOUR);
        Hand hand = new Hand();
        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);
        hand.addCard(card4);
        //remove card2 by index
        hand.removeCard(1);
        //remove card4 by card 
        hand.removeCard(card4);
        assertTrue(card1.isSameAs(hand.getCard(0)));
        assertTrue(card3.isSameAs(hand.getCard(1)));
        System.out.println("Hand remove Test is successful");
    }


    
}