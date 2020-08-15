package estimation.model;

import java.util.*;


/**
 * Specification of the model of an Estimation game.
 * The model contains the game's substantial data.
 */
public class GameModel {

    /**
     * List to store the game players' scores.
     */
    private List<Integer> playerScoreList = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
    /**
     * Integer to track the game's round number.
     */
    private int roundTracker = 1;
    /**
     * List to store the game's players for a round.
     */
    private List<Player> playerList = new ArrayList<Player>(Arrays.asList(new Player(),
            new Player(), new Player(), new Player()));
    /**
     * Variable to store a round's trump suit.
     */
    private Suit trumpSuit;
    /**
     * Integer to store the index of a player who is also a dealer.
     */
    private int dealerIndex;
    /**
     * Integer to store the number of tricks in a round.
     */
    private int numberOfTricks = 1;
    /**
     * Integer to store the trick being played in a round.
     */
    private int trickTracker = 1;
    /**
     * List of cards played in a trick thus far.
     */
    private List<Card> playedThusFar = new ArrayList<Card>();
    /**
     * Variable to store a trick's winning player.
     */
    private Player winningPlayer;
    /**
     * Variable to store the game's deck.
     */
    private Deck gameDeck = new Deck();


    /**
     * Instantiate a new GameModel object.
     */
    public GameModel() {
        resetDeck();
        reAllocateCards();
        updateTrumpSuit();

        Deck tempDeck = new Deck();
        tempDeck.shuffle();
        Card highestCard = null;
        int highestPlayerIndex = 0;

        // Each player is given one card each.
        for (int i = 0; i < this.playerList.size(); i++) {
            if (highestCard == null) {
                highestCard = tempDeck.dealCard();
            } else {
                Card tempCard = tempDeck.dealCard();

                // The player with the highest rank card (irrespective of the suit)
                // is the dealer of the first round.
                if (tempCard.getRank().compareTo(highestCard.getRank()) > 0) {
                    highestCard = tempCard;
                    highestPlayerIndex = i;
                } else if (tempCard.getRank().equals(Rank.ACE) && tempCard.getRank().compareTo(highestCard.getRank()) == 0) {

                    // Use the suit to break a tie if there are 2 players with the Ace card.
                    if (tempCard.getSuit().compareTo(highestCard.getSuit()) > 0) {
                        highestPlayerIndex = i;
                        highestCard = tempCard;
                    }
                }
            }
        }

        this.dealerIndex = highestPlayerIndex;
        playerList.get(highestPlayerIndex).setIsDealer();
        System.out.println("Player " + (this.dealerIndex + 1) + " drew the highest Rank/Suit (if Ace) card.");
        System.out.println("Player " + (this.dealerIndex + 1) + " is the dealer.");

        playerList.get(0).setIsHuman();
    }


    /**
     * Method to retrieve the game players' scores.
     * @return a list with the game players' scores.
     */
    public List<Integer> getPlayerScoreList() {
        return this.playerScoreList;
    }


    /**
     * Method to reset the game's deck.
     * Restores the deck to the full 52 cards and then shuffles the cards.
     */
    public void resetDeck() {
        this.gameDeck.restoreDeck();
        this.gameDeck.shuffle();
    }


    /**
     * Method to serve cards to players in each round.
     */
    public void reAllocateCards() {
        int playerIndexTracker = this.dealerIndex;
        for (int i = 0; i < this.playerList.size(); i++) {
            if (playerIndexTracker > this.playerList.size() - 1) {
                playerIndexTracker = 0;
            }
            Player currentPlayer = this.playerList.get(playerIndexTracker);
            for (int j = 0; j < this.numberOfTricks; j++) {
                currentPlayer.getPlayerHand().addCard(this.gameDeck.dealCard());
            }
            playerIndexTracker += 1;
        }
    }


    /**
     * Method to retrieve the game's round being played.
     * @return an integer representing the game's round being played.
     */
    public int getRoundTracker() {
        return this.roundTracker;
    }


    /**
     * Method to increase the game's round being played by 1.
     */
    public void increaseRoundTracker() {
        this.roundTracker += 1;
    }


    /**
     * Method to retrieve a round's players.
     * @return a list with the round's players.
     */
    public List<Player> getPlayerList() {
        return this.playerList;
    }


    /**
     * Method to reset the game's players at the end of each round.
     */
    public void resetPlayerList() {
        this.playerList = new ArrayList<Player>(Arrays.asList(new Player(),
                new Player(), new Player(), new Player()));
        this.playerList.get(0).setIsHuman();
    }


    /**
     * Method to retrieve a round's trump suit.
     * @return the round's trump suit.
     */
    public Suit getTrumpSuit() {
        return this.trumpSuit;
    }


    /**
     * Method to update a round's trump suit.
     * In accordance with game rules, called after players' cards
     * have been served.
     */
    public void updateTrumpSuit() {
        this.trumpSuit = this.gameDeck.dealCard().getSuit();
    }


    /**
     * Method to retrieve the index of a player who is also a dealer.
     * @return an integer representing the index of a player who is also a dealer.
     */
    public int getDealerIndex() {
        return this.dealerIndex;
    }


    /**
     * Method to update the index of a player who is also a dealer.
     * Changes the dealer to the player to the original dealer's left.
     */
    public void updateDealerIndex() {
        this.playerList.get(this.dealerIndex).setIsDealer();
        this.dealerIndex += 1;
        if (this.dealerIndex > this.playerList.size() - 1) {
            this.dealerIndex = 0;
        }
        this.playerList.get(this.dealerIndex).setIsDealer();
    }


    /**
     * Method to retrieve a round's number of tricks.
     * @return an integer representing the number of tricks in the round.
     */
    public int getNumberOfTricks() {
        return this.numberOfTricks;
    }


    /**
     * Method to increase a round's number of tricks by 1.
     */
    public void increaseNumberOfTricks() {
        this.numberOfTricks += 1;
    }


    /**
     * Method to decrease a round's number of tricks by 1.
     */
    public void decreaseNumberOfTricks() {
        this.numberOfTricks -= 1;
    }


    /**
     * Method to retrieve the trick being played in a round.
     * @return an integer representing the trick being played in a round.
     */
    public int getTrickTracker() {
        return this.trickTracker;
    }


    /**
     * Method to reset the trick being played in a round to 1,
     * in preperation for the next round.
     * Called at the end of each round.
     */
    public void resetTrickTracker() {
        this.trickTracker = 1;
    }


    /**
     * Method to increase the trick being played in a round by 1,
     * in preperation for the next trick.
     * Called at the end of each trick.
     */
    public void increaseTrickTracker() {
        this.trickTracker += 1;
    }


    /**
     * Method to retrieve the winning player in the previous trick.
     * @return the winning player's Player object.
     */
    public Player getWinningPlayer() {
        return this.winningPlayer;
    }


    /**
     * Method to set the winning player in a trick,
     * in preperation for the next trick.
     * @param winningPlayer the winning player's Player object.
     */
    public void setWinningPlayer(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }


    /**
     * Method to retrieve the list of cards already played in the trick.
     * @return a list of cards played in a trick thus far.
     */
    public List<Card> getPlayedThusFar() {
        return this.playedThusFar;
    }


    /**
     * Method to reset the list of cards already played in the trick,
     * in preperation for the next trick.
     */
    public void resetPlayedThusFar() { this.playedThusFar = new ArrayList<Card>(); }

}