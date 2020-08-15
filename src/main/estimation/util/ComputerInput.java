package estimation.util;

import estimation.model.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Class with static methods to handle computer players' actions.
 */
public class ComputerInput {

    /**
     * Method that handles computer logic for placing a bid.
     * @param hand the player's hand.
     * @param numberOfTricks the round's number of tricks.
     * @param totalBidsThusFar the total of all the bids already placed by players in the trick.
     * @param trumpSuit the trump suit of a round.
     * @param isDealer <code>true</code> if the computer is the dealer,
     *                 <code>false</code> if the computer is not the dealer.
     * @return an integer representing the player's bid.
     */
    public static int placeBid(Hand hand, int numberOfTricks, int totalBidsThusFar, Suit trumpSuit, boolean isDealer) {
        int returnBid;
        List<Integer> possibleBids = getPossibleBids(totalBidsThusFar, numberOfTricks, isDealer);
        int highRankingTracker = getNumberOfHighRankingCards(hand, trumpSuit);

        // Get medianBidPosition.
        int medianBidPosition;
        if (possibleBids.size() % 2 == 0) {
            medianBidPosition = possibleBids.size() / 2 - 1;
        } else {
            medianBidPosition = possibleBids.size() / 2;
        }

        // Get returnBid.
        // If num <= 25%: Pick the possible bid 2 places to the left (or 0).
        if (highRankingTracker <= hand.getNumberOfCards() / 4.0) {
            returnBid = (Integer) possibleBids.get(Math.max(0, medianBidPosition - 2));
        // If 25 < num <= 50%: Pick the possible bid 1 places to the left (or 0).
        } else if (highRankingTracker <= hand.getNumberOfCards() / 2.0) {
            returnBid = (Integer) possibleBids.get(Math.max(0, medianBidPosition - 1));
        // If 75 < num <= 100%: Pick the possible bid 2 places to the right (or max possible).
        } else if (highRankingTracker > hand.getNumberOfCards() * 0.75) {
            returnBid = (Integer) possibleBids.get(Math.min(medianBidPosition + 2, hand.getNumberOfCards() - 1));
        // 50 < num <= 75%: Pick the possible bid 1 places to the right.
        } else {
            returnBid = (Integer) possibleBids.get(Math.min(medianBidPosition + 1, hand.getNumberOfCards() - 1));
        }
        return returnBid;
    }


    /**
     * Method that determines the possible bids that the player can make.
     * @param totalBidsThusFar the total of all the bids already placed by players in the trick.
     * @param numberOfTricks the round's number of tricks.
     * @param isDealer <code>true</code> if the computer is the dealer,
     *                 <code>false</code> if the computer is not the dealer.
     * @return a list of integers with possible bids that the player can make.
     */
    public static List<Integer> getPossibleBids(int totalBidsThusFar, int numberOfTricks, boolean isDealer) {
        List listToReturn = new ArrayList<Integer>();
        for (int i = 0; i <= numberOfTricks; i++) {
            listToReturn.add(i);
        }
        if (isDealer) {
            int bidNotPossible = numberOfTricks - totalBidsThusFar;
            if (bidNotPossible > 0) {
                listToReturn.remove(bidNotPossible);
            }
        }
        return listToReturn;
    }


    /**
     * Method to determine the number of high ranking cards, including trump suit cards,
     * in the player's hand.
     * @param hand the player's hand.
     * @param trumpSuit the round's trump suit.
     * @return an integer representing the number of high ranking cards,
     * including trump suit cards, in the player's hand.
     */
    public static int getNumberOfHighRankingCards(Hand hand, Suit trumpSuit) {
        int highRankingTracker = 0;
        for (int i = 0; i < hand.getNumberOfCards(); i++) {
            Card card = hand.getCard(i);
            if (card.getSuit().equals(trumpSuit) || Rank.HIGH_RANKING_VALUES.contains(card.getRank())) {
                highRankingTracker += 1;
            }
        }
        return highRankingTracker;
    }


    /**
     * Method that handles computer logic for playing a card if it is not the first player in a trick.
     * @param hand the player's hand.
     * @param trumpSuit the round's trump suit.
     * @param playedThusFar a list of cards already played in the trick.
     * @param isPositiveBidLeft <code>true</code> if the number of tricks to win left
     *                          to reach the player's bid is &gt; 0,
     *                          <code>false</code> if remaining tricks to win &lt; 0.
     * @return the player's selected card.
     */
    public static Card playCard(Hand hand, Suit trumpSuit, List playedThusFar, boolean isPositiveBidLeft) {
        Card cardToReturn;

        // Get highest ranked card played thus far.
        Card leadCard = (Card) playedThusFar.get(0);
        Suit leadSuit = leadCard.getSuit();
        Card highestCard = null;
        if (checkTrump(hand, trumpSuit)) {
            highestCard = getHighestSuitCardPlayed(playedThusFar, trumpSuit);
        }

        if (highestCard == null) {
            highestCard = getHighestSuitCardPlayed(playedThusFar, leadSuit);
        }

        // Check if bids predicted is positive.
        if (isPositiveBidLeft) {
            // Pick the lowest-ranked card of the lead suit whose rank is higher than that is played.
            cardToReturn = getSuitCardComparedToPlayed(hand, highestCard, leadSuit, true);

            // Pick the lowest-ranked card of the trump suit whose rank is higher than that is played.
            if (cardToReturn == null) {
                cardToReturn = getSuitCardComparedToPlayed(hand, highestCard, trumpSuit, true);
            }

            // If there is no choice, pick the lowest-ranked card of the lead suit.
            if (cardToReturn == null) {
                cardToReturn = getSuitCard(hand, leadSuit, false);
            }

            // If there is no choice, pick the lowest-ranked card of the trump suit.
            if (cardToReturn == null) {
                cardToReturn = getSuitCard(hand, trumpSuit, false);
            }

            // If there is no choice, pick the lowest-ranked card of another suit (in ascending order).
            if (cardToReturn == null) {
                cardToReturn = getRankedCard(hand, trumpSuit, false);
            }
        } else {
            // Pick a card from the lead suit.
            // The rank is smaller than the highest rank that is played so far, but is the largest rank card on hand.
            cardToReturn = getSuitCardComparedToPlayed(hand, highestCard, leadSuit, false);

            // Pick the card of the trump suit that is lower in rank than that is played,
            // but is the largest rank card on hand.
            if (cardToReturn == null) {
                cardToReturn = getSuitCardComparedToPlayed(hand, highestCard, trumpSuit, false);
            }

            // If there is no choice, pick the highest rank card of the trump suit.
            if (cardToReturn == null) {
                cardToReturn = getSuitCard(hand, trumpSuit, true);
            }

            // If there is no choice, pick the highest rank card of the lead suit.
            if (cardToReturn == null) {
                cardToReturn = getSuitCard(hand, leadSuit, true);
            }

            // If no card of trump/lead suit available, pick the highest ranked card of another suit
            // in the ascending order.
            if (cardToReturn == null) {
                cardToReturn = getRankedCard(hand, trumpSuit, true);
            }
        }
        return cardToReturn;
    }


    /**
     * Method that handles computer logic for playing a card if it is the first player in a trick.
     * @param hand the player's hand.
     * @param trumpSuit the round's trump suit.
     * @param isPositiveBidLeft <code>true</code> if the number of tricks to win left
     *                          to reach the player's bid is &gt; 0,
     *                          <code>false</code> if remaining tricks to win &lt; 0.
     * @return the player's selected card.
     */
    public static Card playFirstCard(Hand hand, Suit trumpSuit, boolean isPositiveBidLeft) {
        Card cardToReturn;

        if (isPositiveBidLeft) {
            // Get highest non-trump card.
            cardToReturn = getRankedCard(hand, trumpSuit, true);

            // Get highest trump card.
            if (cardToReturn == null) {
                cardToReturn = getSuitCard(hand, trumpSuit, true);
            }
        } else {
            // Get lowest non-trump card.
            cardToReturn = getRankedCard(hand, trumpSuit, false);

            // Get lowest trump card.
            if (cardToReturn == null) {
                cardToReturn = getSuitCard(hand, trumpSuit, false);
            }
        }
        return cardToReturn;
    }


    /**
     * Method to determine if the player's hand have trump suit cards.
     * @param hand the player's hand.
     * @param trumpSuit the round's trump suit.
     * @return
     */
    private static boolean checkTrump (Hand hand, Suit trumpSuit) {
        for (int i = 0; i < hand.getNumberOfCards(); i++) {
            Card card = hand.getCard(i);
            if (card.getSuit().equals(trumpSuit)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Method that returns a card that is not from a round's trump suit from the player's hand.
     * @param hand the player's hand.
     * @param trumpSuit the round's trump suit.
     * @param getHighest boolean describing if the highest ranking eligible card should be returned.
     * @return the player's selected card.
     */
    public static Card getRankedCard(Hand hand, Suit trumpSuit, boolean getHighest) {
        Card cardToReturn = null;
        for (int i = 0; i < hand.getNumberOfCards(); i++) {
            Card card = hand.getCard(i);
            if (!card.getSuit().equals(trumpSuit)) {
                if (cardToReturn == null) {
                    cardToReturn = card;
                } else if (getHighest && card.compareTo(cardToReturn) > 0) {
                    cardToReturn = card;
                } else if (!getHighest && card.compareTo(cardToReturn) < 0) {
                    cardToReturn = card;
                }
            }
        }
        return cardToReturn;
    }


    /**
     * Method that returns a card from a suit from the player's hand.
     * @param hand the player's hand.
     * @param suit the suit.
     * @param getHighest boolean describing if the highest ranking eligible card should be returned.
     * @return the player's selected card.
     */
    public static Card getSuitCard(Hand hand, Suit suit, boolean getHighest) {
        Card cardToReturn = null;
        for (int i = 0; i < hand.getNumberOfCards(); i++) {
            Card card = hand.getCard(i);
            if (card.getSuit().equals(suit)) {
                if (cardToReturn == null) {
                    cardToReturn = card;
                } else if (getHighest && card.getRank().compareTo(cardToReturn.getRank()) > 0) {
                    cardToReturn = card;
                } else if (!getHighest && card.getRank().compareTo(cardToReturn.getRank()) < 0) {
                    cardToReturn = card;
                }
            }
        }
        return cardToReturn;
    }


    /**
     * Method that returns a card from a suit from the player's hand.
     * @param hand the player's hand.
     * @param highestCard the highest card among cards already played in the trick.
     * @param suit the suit.
     * @param compareHigherPlayedLowestHand boolean describing if a card higher than what has been played,
     *                                      but the lowest among these cards, should be returned.
     * @return the player's selected card.
     */
    public static Card getSuitCardComparedToPlayed(Hand hand, Card highestCard, Suit suit, boolean compareHigherPlayedLowestHand) {
        Card cardToReturn = null;
        for (int i = 0; i < hand.getNumberOfCards(); i++) {
            Card card = hand.getCard(i);
            if (card.getSuit().equals(suit)) {
                if (cardToReturn == null) {
                    cardToReturn = card;
                } else if (compareHigherPlayedLowestHand && card.getRank().compareTo(highestCard.getRank()) > 0 && card.getRank().compareTo(cardToReturn.getRank()) < 0) {
                    cardToReturn = card;
                } else if (!compareHigherPlayedLowestHand && card.getRank().compareTo(highestCard.getRank()) < 0 && card.getRank().compareTo(cardToReturn.getRank()) > 0) {
                    cardToReturn = card;
                }
            }
        }
        return cardToReturn;
    }


    /**
     * Method that returns a card from a suit from the player's hand.
     * @param playedThusFar a list of cards already played in the trick.
     * @param suit the suit.
     * @return the player's selected card.
     */
    public static Card getHighestSuitCardPlayed(List<Card> playedThusFar, Suit suit) {
        Card cardToReturn = null;
        for (int i = 0; i < playedThusFar.size(); i++) {
            Card card = playedThusFar.get(i);
            if (card.getSuit().equals(suit)) {
                if (cardToReturn == null) {
                    cardToReturn = card;
                } else if (card.getRank().compareTo(cardToReturn.getRank()) > 0) {
                    cardToReturn = card;
                }
            }
        }
        return cardToReturn;
    }

}