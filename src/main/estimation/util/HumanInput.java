package estimation.util;

import estimation.exception.IllegalMoveException;
import estimation.model.Card;
import estimation.model.Hand;
import estimation.model.Player;
import estimation.model.Suit;

import javax.swing.*;
import java.util.List;


/**
 * Class with static methods to handle the human player's actions.
 */
public class HumanInput {

    /**
     * Method that check if player's bid is legal.
     * @param tempInt the player's bid.
     * @param bidNotPossible the bid that the player cannot make.
     * @param dealerIndex the index of a player who is also a dealer.
     * @return a boolean indicating that the player's bid is legal.
     * @throws IllegalMoveException if player's bid is not legal.
     */
    public static boolean isLegalBid(int tempInt, int bidNotPossible, int dealerIndex) throws IllegalMoveException {
        if (tempInt < 0 || tempInt == bidNotPossible) {
            if (dealerIndex == 0) {
                throw new IllegalMoveException("You didn't enter a valid bid. As you are the dealer, you are prohibited from bidding " + bidNotPossible + ".",
                        "Game Rule Violation");
            } else {
                throw new IllegalMoveException("You didn't enter a valid bid.", "Game Rule Violation");
            }
        }
        return true;
    }


    /**
     * Method that handles human input for placing a bid.
     * @param numberOfTricks the round's number of tricks.
     * @param totalBidsThusFar the total of all the bids already placed by players in the trick.
     * @param dealerIndex the index of a player who is also a dealer.
     * @return an integer representing the player's bid.
     */
    public static int placeBid(int numberOfTricks, int totalBidsThusFar, int dealerIndex) {
        int bidNotPossible = -1;

        if (dealerIndex == 0) {
            bidNotPossible = numberOfTricks - totalBidsThusFar;
        }

        int tempInt = 99999;
        boolean validChecker = false;
        while (!validChecker) {
            try {
                String tempString;
                if (dealerIndex == 0 && bidNotPossible > 0) {
                    tempString = JOptionPane.showInputDialog(new JFrame(), "You are the dealer. You are prohibited from bidding " + bidNotPossible + ". Enter your bid:", "");
                } else if (dealerIndex == 0) {
                    tempString = JOptionPane.showInputDialog(new JFrame(), "You are the dealer. You can place any bid. Enter your bid:", "");
                } else {
                    tempString = JOptionPane.showInputDialog(new JFrame(), "Enter your bid:", "");
                }
                tempInt = Integer.parseInt(tempString);
                if (isLegalBid(tempInt, bidNotPossible, dealerIndex)) {
                    validChecker = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(new JFrame(), "You didn't enter an integer. Please enter an integer.", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IllegalMoveException e) {
                JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), e.getTitle(),
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return tempInt;
    }


    /**
     * Method that prompts human input for playing a card.
     * @param hand the player's hand.
     * @return the player's selected card.
     */
    public static int promptPlayerCardInput(Hand hand) {
        ImageIcon[] options = new ImageIcon[hand.getNumberOfCards()];
        for (int i = 0; i < hand.getNumberOfCards(); i++) {
            options[i] = hand.getCard(i).getCardImage();
        }
        int cardIndex = JOptionPane.showOptionDialog(new JFrame(), null, "Select a Card", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,  null, options, null);
        return cardIndex;
    }


    /**
     * Method that check if player's move is legal.
     * @param playedThusFar a list of cards already played in the trick.
     * @param card the player's selected card.
     * @param leadExists a boolean indicating if lead suit card(s) exist in the player's hand.
     * @param trumpExists a boolean indicating if trump suit card(s) exist in the player's hand.
     * @param leadSuit the trick's lead suit.
     * @param trumpSuit the round's trump suit.
     * @return a boolean indicating that the player's move is legal.
     * @throws IllegalMoveException if player's move is not legal.
     */
    public static boolean isLegalMove(List<Card> playedThusFar, Card card, boolean leadExists, boolean trumpExists, Suit leadSuit, Suit trumpSuit) throws IllegalMoveException {
        if (leadExists && trumpExists) {
            if (!card.getSuit().equals(leadSuit) && !card.getSuit().equals(trumpSuit)){
                throw new IllegalMoveException("You have both lead suit and trump suit cards.\nPlease select either a lead suit or trump suit card.",
                        "Game Rule Violation");
            }
        } else if (leadExists) {
            if (!card.getSuit().equals(leadSuit)){
                throw new IllegalMoveException("You have lead suit card(s).\nPlease select a lead suit card.", "Game Rule Violation");
            }
        } else if (trumpExists) {
            if (!card.getSuit().equals(trumpSuit) && playedThusFar.size() > 0) {
                throw new IllegalMoveException("You have trump suit card(s).\nPlease select a trump suit card.", "Game Rule Violation");
            }
        }
        return true;
    }


    /**
     * Method that handles human input for playing a card.
     * Includes error checking for negative/non-integers.
     * Ensures that the player does not play a non-lead suit card if he has a lead suit card.
     * If there is no lead suit card in the player's hand, ensures that the player does not play a non-trump suit card if he has a trump suit card.
     * @param player a Player object representing the human player.
     * @param playedThusFar a list of cards already played in the trick.
     * @param trumpSuit the round's trump suit.
     * @return the player's selected card.
     */
    public static Card playCard(Player player, List playedThusFar, Suit trumpSuit) {
        Hand playerHand = player.getPlayerHand();
        Suit leadSuit = null;

        // Check for lead and trump suit cards in the player's hand.
        boolean leadExists = false;
        boolean trumpExists = false;
        for (int i = 0; i < playerHand.getNumberOfCards(); i++) {
            Card card = (Card) playerHand.getCard(i);
            if (playedThusFar.size() > 0) {
                Card leadCard = (Card) playedThusFar.get(0);
                leadSuit = leadCard.getSuit();
                if (card.getSuit().equals(leadSuit)) {
                    leadExists = true;
                }
            }
            if (card.getSuit().equals(trumpSuit)) {
                trumpExists = true;
            }
        }

        // Prompt player to select card.
        int tempInt = 99999;
        boolean validChecker = false;
        while (!validChecker) {
            tempInt = promptPlayerCardInput(playerHand);
            Card card = playerHand.getCard(tempInt);
            try {
                validChecker = isLegalMove(playedThusFar, card, leadExists, trumpExists, leadSuit, trumpSuit);
            } catch (IllegalMoveException e) {
                JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), e.getTitle(),
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return playerHand.getCard(tempInt);
    }

}