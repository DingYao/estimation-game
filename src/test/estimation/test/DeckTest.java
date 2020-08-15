package estimation.test;

import estimation.model.Card;
import estimation.model.Deck;
import estimation.model.Rank;
import estimation.model.Suit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * DeckTest will test the following:
 * 	able to add a card to the deck by calling addCard method.
 * 	able to deal the card by calling dealCard method
 * 	getNumberOfCardsRemaining method is able to return the remaining card in a deck.
 */
public class DeckTest {
   private Deck deckTest;

   @Before
   public void instantiateDeck(){
       deckTest = new Deck();
   }

   @Test
   public void getSizeOfDeck() {
       assertEquals(52, deckTest.getSizeOfDeck());
       System.out.println("Deck get size Test is successful");
   }

   @Test
   public void addCard(){
       Card twoOfSpade = new Card(Suit.SPADES, Rank.TWO);
       deckTest.addCard(twoOfSpade);
       assertEquals(53, deckTest.getSizeOfDeck());
       System.out.println("Deck add card Test is successful");
   }

   @Test
   public void dealCard() {
       assertEquals(52,deckTest.getSizeOfDeck());
       while (!deckTest.isEmpty()) {
           deckTest.dealCard();
       }
       assertNull(deckTest.dealCard());
       System.out.println("Deck deal card Test is successful");
   }

   @Test
   public void getNumberOfCardsRemaining() {
       assertEquals(52, deckTest.getSizeOfDeck());

       while (!deckTest.isEmpty()) {
           deckTest.dealCard();
           assertEquals(52, deckTest.getSizeOfDeck());
           while (!deckTest.isEmpty()) {
               deckTest.dealCard();
           }
           assertEquals(0, deckTest.getNumberOfCardsRemaining());
       }
       System.out.println("Deck get number of remaining card Test is successful");
   }

   @Test
   public void restoreDeck() {
       deckTest.restoreDeck();
       assertEquals(52, deckTest.getSizeOfDeck());
       System.out.println("Deck restore Test is successful");
   }
}
