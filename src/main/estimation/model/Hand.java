// estimationgame.model.Hand.java - John K. Estell - 8 May 2003
// last modified: 23 Febraury 2004
// Implementation of a abstract hand of playing cards.  
// Uses the estimationgame.model.Card class.  Requires subclass for specifying
// the specifics of what constitutes the evaluation of a hand 
// for the game being implemented.

package estimation.model;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Represents the basic functionality of a hand of cards.
 * Extensions of this class will provide the
 * definition of what constitutes a hand for that game and how hands are compared
 * to one another by overriding the <code>compareTo</code> method.
 *
 * @author John K. Estell
 * @version 1.0
 */
public class Hand implements Comparable {

    private java.util.List hand = new ArrayList();


    /**
     * Adds a card to this hand.
     *
     * @param card card to be added to the current hand.
     */
    public void addCard(Card card) {
        hand.add(card);
    }


    /**
     * Obtains the card stored at the specified location in the hand.  Does not
     * remove the card from the hand.
     *
     * @param index position of card to be accessed.
     * @return the card of interest, or the null reference if the index is out of
     * bounds.
     */
    public Card getCard(int index) {
        return (Card) hand.get(index);
    }


    /**
     * Removes the specified card from the current hand.
     *
     * @param card the card to be removed.
     * @return the card removed from the hand, or null if the card
     * was not present in the hand.
     */
    public Card removeCard(Card card) {
        int index = hand.indexOf(card);
        if (index < 0)
            return null;
        else
            return (Card) hand.remove(index);
    }


    /**
     * Removes the card at the specified index from the hand.
     *
     * @param index poisition of the card to be removed.
     * @return the card removed from the hand, or the null reference if
     * the index is out of bounds.
     */
    public Card removeCard(int index) {
        return (Card) hand.remove(index);
    }


    /**
     * The number of cards held in the hand.
     *
     * @return number of cards currently held in the hand.
     */
    public int getNumberOfCards() {
        return hand.size();
    }


    /**
     * Compares two hands.
     *
     * @param otherHandObject the hand being compared.
     * @return &lt; 0 if this hand is less than the other hand, 0 if the two hands are
     * the same, or &gt; 0 if this hand is greater then the other hand.
     */
    public int compareTo(Object otherHandObject) {
        Hand otherHand = (Hand) otherHandObject;
        return evaluateHand() - otherHand.evaluateHand();
    }


    /**
     * Evaluates the hand.  Must be defined in the subclass that implements the hand
     * for the game being written by the client programmer.
     *
     * @return an integer corresponding to the rating of the hand.
     */
    public int evaluateHand() {
        return 0;
    }


    /**
     * Returns a description of the hand.
     *
     * @return a list of cards held in the hand.
     */
    public String toString() {
        return hand.toString();
    }

}