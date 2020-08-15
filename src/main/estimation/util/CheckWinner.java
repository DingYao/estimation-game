package estimation.util;

import estimation.model.Card;
import estimation.model.Suit;

import java.util.List;


/**
 * Class with static methods to check if player is a winner.
 */
public class CheckWinner {

    /**
     * Method to determine if player has won a trick.
     * @param card the player's card in the trick.
     * @param winningCard the round's number of tricks.
     * @param playedThusFar a list of cards played in the trick thus far.
     * @param trumpSuit the round's trump suit.
     * @return a boolean describing if player has won the trick.
     */
    public static boolean isTrickWinner(Card card, Card winningCard, List<Card> playedThusFar, Suit trumpSuit) {
        Card leadCard = (Card) playedThusFar.get(0);
        Suit leadSuit = leadCard.getSuit();
        if (card.getSuit().equals(trumpSuit) && winningCard.getSuit().equals(trumpSuit)) {
            return card.getRank().compareTo(winningCard.getRank()) > 0;
        } else if (card.getSuit().equals(trumpSuit)) {
            return true;
        } else if (card.getSuit().equals(leadSuit) && winningCard.getSuit().equals(leadSuit)) {
            return card.getRank().compareTo(winningCard.getRank()) > 0;
        }
        return false;
    }


    /**
     * Method to determine if player has won a round.
     * @param bid the player's bid.
     * @param tricksWon the player's number of tricks won in the round.
     * @return a boolean describing if player has won the round.
     */
    public static boolean isRoundWinner(int bid, int tricksWon) {
        return bid == tricksWon;
    }


    /**
     * Method to determine if any player has won the game.
     * @param playerScores a list of player's scores.
     * @return a boolean describing if any player has won the game.
     */
    public static boolean isGameWinner(List<Integer> playerScores) {
        for (int i = 0; i < playerScores.size(); i++) {
            if (playerScores.get(i) >= 100) {
                return true;
            }
        }
        return false;
    }

}