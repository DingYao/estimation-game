// estimationgame.model.Suit.java - John K. Estell - 8 May 2003
// last modified: 23 Febraury 2004
// Implementation of the "suit" value for a playing card.

package estimation.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Specification of the suit values for a standard deck of cards.
 */
public final class Suit implements Comparable {
    /**
     * The suit clubs.
     */
    public final static Suit CLUBS = new Suit("Clubs", "c");
    /**
     * The suit diamonds.
     */
    public final static Suit DIAMONDS = new Suit("Diamonds", "d");
    /**
     * The suit hearts.
     */
    public final static Suit HEARTS = new Suit("Hearts", "h");
    /**
     * The suit spades.
     */
    public final static Suit SPADES = new Suit("Spades", "s");
    /**
     * List of all suit values.  Primarily for use with iteration.
     */
    public final static java.util.List VALUES =
            Collections.unmodifiableList(
                    Arrays.asList(CLUBS, DIAMONDS, HEARTS, SPADES));
    private String name;
    private String symbol;


    // Constructor - declared private as only the predefined values should
    // be used by the client.
    private Suit(String nameValue, String symbolValue) {
        name = nameValue;
        symbol = symbolValue;
    }


    /**
     * Returns a description of this suit.
     *
     * @return the name of the suit.
     */
    public String getName() {
        return name;
    }


    /**
     * The symbol associated with this suit.  Returns the symbol, which
     * usually constitutes a single character, in the form of a string.
     * Symbol is used for the construction of the filenames of the card images.
     *
     * @return string containing the symbol for the suit.
     */
    public String getSymbol() {
        return symbol;
    }


    /**
     * Returns a description of this suit.
     *
     * @return the name of this suit.
     */
    public String toString() {
        return name;
    }


    /**
     * Compares the suits.  Used for the purpose of sorting cards in a hand or deck.
     *
     * @param otherSuitObject the other suit.
     * @param suitListWithoutTrump a list of suits excluding the trump suit
     * @return &lt; 0 if this suit is lower than the other suit, 0 if the suits
     * are the same, or &gt; 0 if this suit is higher than the other suit.
     */
    public int compareTo(Object otherSuitObject, List suitListWithoutTrump) {
        Suit otherSuit = (Suit) otherSuitObject;
        return suitListWithoutTrump.indexOf(this) - suitListWithoutTrump.indexOf(otherSuit);
    }

    public int compareTo(Object otherSuitObject) {
        Suit otherSuit = (Suit) otherSuitObject;
        return VALUES.indexOf(this) - VALUES.indexOf(otherSuit);
    }

}