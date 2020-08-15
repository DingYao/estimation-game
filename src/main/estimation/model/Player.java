package estimation.model;


/**
 * Represents the basic functionality of a Player.
 * The Player class keeps track of each Player's information for each round, such as the number of bids placed, score, and number of tricks won.
 */
public class Player {

    /**
     * Variable to store if the Player is a human.
     */
    private boolean isHuman = false;


    /**
     * Variable to store if the Player is a dealer.
     */
    private boolean isDealer = false;


    /**
     * The Player's hand.
     */
    private Hand playerHand = new Hand();


    /**
     * The Player's bid for the round.
     */
    private int bid = 0;


    /**
     * Variable to store the bids left to reach the original bid amount.
     */
    private int bidLeft = 0;


    /**
     * The Player's score for the round.
     */
    private int scoreRound = 0;


    /**
     * The Player's number of tricks won for the round.
     */
    private int tricksWon = 0;


    /**
     * Instantiate a new Player object.
     */
    public Player() {
    }


    /**
     * Returns the Player's human status for the round.
     * @return <code>true</code> if the Player is a human,
     * <code>false</code> if the Player is a computer.
     */
    public boolean getIsHuman() {
        return isHuman;
    }


    /**
     * Indicates that the Player is human for the round.
     */
    public void setIsHuman() {
        this.isHuman = !this.isHuman;
    }


    /**
     * Returns the Player's dealer status for the round.
     * @return <code>true</code> if Player is the dealer,
     * <code>false</code> if the Player is not the dealer.
     */
    public boolean getIsDealer() {
        return isDealer;
    }


    /**
     * Indicates that the Player is dealer for the round.
     */
    public void setIsDealer() {
        this.isDealer = !this.isDealer;
    }


    /**
     * Instantiate a new Player object.
     * @return the Hand of a Player.
     */
    public Hand getPlayerHand() {
        return playerHand;
    }


    /**
     * Returns the Player's bid for the round.
     * @return an integer describing the bid placed by the Player for the round.
     */
    public int getBid() {
        return bid;
    }


    /**
     * Sets the Player's bid for the round.
     * @param bid the bid placed by the Player for that round.
     */
    public void setBid(int bid) {
        this.bid = bid;
    }


    /**
     * Returns the Player's bids left to reach its original bid amount for the round.
     * @return <code>true</code> if the remaining bids is &gt; 0, <code>false</code> if remaining bids &lt; 0.
     */
    public boolean isPositiveBidLeft() {
        return bidLeft > 0;
    }


    /**
     * Sets the Player's bids left to reach its original bid amount for the round.
     */
    public void setBidLeft() {
        this.bidLeft = bid;
    }


    /**
     * Decreases the Player's bids left to reach its original bid amount for the round by 1.
     */
    public void decreaseBidLeft() {
        this.bidLeft -= 1;
    }


    /**
     * Return the Player's score for the round.
     * @return an integer describing the Player's score for the round.
     */
    public int getScoreRound() {
        return scoreRound;
    }


    /**
     * Increases the Player's score for the round.
     * Score is increased by 10 + the player's bid amount.
     */
    public void increaseScoreRound() {
        this.scoreRound += (10 + this.bid);
    }


    /**
     * Decreases the Player's score for the round.
     * Score is decreased by 10 - the player's bid amount.
     */
    public void decreaseScoreRound() {
        this.scoreRound -= (10 + this.bid);
    }


    /**
     * Return the Player's number of tricks won for the round.
     * @return an integer representing the number of tricks won
     * by a Player for the round.
     */
    public int getTricksWon() {
        return tricksWon;
    }


    /**
     * Increases the Player's number of tricks won for the round.
     */
    public void increaseTricksWon() {
        this.tricksWon += 1;
    }

}