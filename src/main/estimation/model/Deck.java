// estimationgame.model.Deck.java - John K. Estell - 8 May 2003
// last modified: 23 Febraury 2004
// Implementation of a deck of playing cards.  Uses the estimationgame.model.Card class.

package estimation.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Represents a deck of playing cards.  In order to have maximum flexibility,
 * this class does not implement a standard deck of playing cards; it only
 * provides the functionality of a deck of cards.  The client programmer must
 * instantiate a estimationgame.model.Deck object, then populate it with the set of playing cards
 * appropriate for the card game being implemented.  This allows for proper
 * implementation of card games such as pinochle (a 48-card deck containing
 * only aces, nines, tens, jacks, queens, and kings in all four suits, with
 * each card present twice in the deck) or casino-style blackjack (where six
 * standard decks are used for a game).
 *
 * @author John K. Estell
 * @version 1.0
 */
public class Deck {
    private List<Card> deck = new ArrayList<Card>();
    private int index;
    public static final List<String> cardAddresses = new ArrayList<>(Arrays.asList(
            "/images/cards/2c.gif","/images/cards/3c.gif",
            "/images/cards/4c.gif","/images/cards/5c.gif","/images/cards/6c.gif",
            "/images/cards/7c.gif","/images/cards/8c.gif","/images/cards/9c.gif",
            "/images/cards/tc.gif","/images/cards/jc.gif","/images/cards/qc.gif",
            "/images/cards/kc.gif","/images/cards/ac.gif","/images/cards/2d.gif",
            "/images/cards/3d.gif","/images/cards/4d.gif","/images/cards/5d.gif",
            "/images/cards/6d.gif","/images/cards/7d.gif","/images/cards/8d.gif",
            "/images/cards/9d.gif","/images/cards/td.gif","/images/cards/jd.gif",
            "/images/cards/qd.gif","/images/cards/kd.gif","/images/cards/ad.gif",
            "/images/cards/2h.gif","/images/cards/3h.gif","/images/cards/4h.gif",
            "/images/cards/5h.gif","/images/cards/6h.gif","/images/cards/7h.gif",
            "/images/cards/8h.gif","/images/cards/9h.gif","/images/cards/th.gif",
            "/images/cards/jh.gif","/images/cards/qh.gif","/images/cards/kh.gif",
            "/images/cards/ah.gif","/images/cards/2s.gif","/images/cards/3s.gif",
            "/images/cards/4s.gif","/images/cards/5s.gif","/images/cards/6s.gif",
            "/images/cards/7s.gif","/images/cards/8s.gif","/images/cards/9s.gif",
            "/images/cards/ts.gif","/images/cards/js.gif","/images/cards/qs.gif",
            "/images/cards/ks.gif","/images/cards/as.gif"));


    /**
     * Creates an empty deck of cards.
     */
    public Deck() {
        int cardCounter = 0;
        for (Object suitObject : Suit.VALUES) {
            Suit suit = (Suit) suitObject;
            for (Object rankObject : Rank.VALUES) {
                Rank rank = (Rank) rankObject;
                ImageIcon image = new ImageIcon(getClass().getResource(cardAddresses.get(cardCounter)));
                Card c = new Card(suit, rank, image);
                deck.add(c);
                cardCounter += 1;
            }
        }
        this.index = 0;
    }

    /**
     * Adds a card to the deck.
     *
     * @param card card to be added to the deck.
     */
    public void addCard(Card card) {
        deck.add(card);
    }


    /**
     * The size of a deck of cards.
     *
     * @return the number of cards present in the full deck.
     */
    public int getSizeOfDeck() {
        return deck.size();
    }


    /**
     * The number of cards left in the deck.
     *
     * @return the number of cards left to be dealt from the deck.
     */
    public int getNumberOfCardsRemaining() {
        return deck.size() - index;
    }


    /**
     * Deal one card from the deck.
     *
     * @return a card from the deck, or the null reference if there
     * are no cards left in the deck.
     */
    public Card dealCard() {
        if (index >= deck.size())
            return null;
        else
            return (Card) deck.get(index++);
    }


    /**
     * Shuffles the cards present in the deck.
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }


    /**
     * Looks for an empty deck.
     *
     * @return <code>true</code> if there are no cards left to be dealt from the deck.
     */
    public boolean isEmpty() {
        return index >= deck.size();
    }


    /**
     * Restores the deck to "full deck" status.
     */
    public void restoreDeck() {
        index = 0;
    }

}