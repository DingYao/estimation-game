package estimation.controller;

import estimation.gui.GameView;
import estimation.model.Card;
import estimation.model.GameModel;
import estimation.model.Hand;
import estimation.model.Player;
import estimation.util.ComputerInput;
import estimation.util.HumanInput;
import estimation.util.CheckWinner;

import javax.swing.*;
import java.util.List;


/**
 * Specification of the controller of an Estimation game.
 */
public class GameController {

    /**
     * Variable to store the game's graphical user interface.
     */
    private GameView view;
    /**
     * Variable to store the game's model.
     */
    private GameModel model;


    /**
     * Instantiate a new GameController object.
     * @param model a GameModel object representing the game's model.
     * @param view a GameView object representing the game's user interface.
     */
    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }


    /**
     * Method to place players' bid each round.
     */
    public void placeBids() {
        int bidsThusFar = 0;
        int playerIndexTracker = model.getDealerIndex() + 1;

        // Get player bids.
        for (int i = 0; i < model.getPlayerList().size(); i++) {
            if (playerIndexTracker > model.getPlayerList().size() - 1) {
                playerIndexTracker = 0;
            }
            Player player = model.getPlayerList().get(playerIndexTracker);
            int playerBid;
            if (player.getIsHuman()) {
                playerBid = HumanInput.placeBid(model.getNumberOfTricks(), bidsThusFar, model.getDealerIndex());
            } else {
                playerBid = ComputerInput.placeBid(player.getPlayerHand(), model.getNumberOfTricks(), bidsThusFar, model.getTrumpSuit(), player.getIsDealer());
            }
            player.setBid(playerBid);
            player.setBidLeft();
            bidsThusFar += playerBid;
            playerIndexTracker += 1;
            view.updateView();
        }

        view.updateView();
    }


    /**
     * Method to play all tricks each round.
     */
    public void playAllTricks() {
        for (int i = 0; i < model.getNumberOfTricks(); i++) {
            playTrick();
        }
    }


    /**
     * Method to play a trick in a round.
     */
    public void playTrick() {
        List<Player> playerList = model.getPlayerList();
        Player winningPlayer = model.getWinningPlayer();
        Card winningCard = null;
        int playerIndexTracker;
        if (model.getTrickTracker() == 1) {
            if (model.getDealerIndex() + 1 > playerList.size()) {
                playerIndexTracker = 0;
            } else {
                playerIndexTracker = model.getDealerIndex() + 1;
            }
        } else {
            playerIndexTracker = playerList.indexOf(winningPlayer);
        }

        // Get player cards.
        for (int i = 0; i < playerList.size(); i++) {
            if (playerIndexTracker > playerList.size() - 1) {
                playerIndexTracker = 0;
            }
            Player player = playerList.get(playerIndexTracker);
            Hand hand = player.getPlayerHand();
            Card playerCard;
            if (player.getIsHuman()) {
                playerCard = HumanInput.playCard(player, model.getPlayedThusFar(), model.getTrumpSuit());
            } else {
                if (i == 0) {
                    playerCard = ComputerInput.playFirstCard(hand, model.getTrumpSuit(), player.isPositiveBidLeft());
                } else {
                    playerCard = ComputerInput.playCard(hand, model.getTrumpSuit(), model.getPlayedThusFar(), player.isPositiveBidLeft());
                }
            }
            hand.removeCard(playerCard);
            model.getPlayedThusFar().add(playerCard);

            // Check if the player is the potential trick winner.
            if (winningCard == null) {
                winningCard = playerCard;
                winningPlayer = player;
            } else if (CheckWinner.isTrickWinner(playerCard, winningCard, model.getPlayedThusFar(), model.getTrumpSuit())) {
                winningCard = playerCard;
                winningPlayer = player;
            }

            playerIndexTracker += 1;
            view.updateView();
        }

        winningPlayer.increaseTricksWon();
        winningPlayer.decreaseBidLeft();
        model.setWinningPlayer(winningPlayer);
        model.increaseTrickTracker();
        model.resetPlayedThusFar();
        view.updateView();
    }


    /**
     * Method to update scores at the end of each round.
     */
    public void settleScores() {
        List playerList = model.getPlayerList();

        for (int i = 0; i < playerList.size(); i++) {
            Player player = (Player) playerList.get(i);
            if (CheckWinner.isRoundWinner(player.getBid(), player.getTricksWon())) {
                player.increaseScoreRound();
            } else {
                player.decreaseScoreRound();
            }
            int playerScore = model.getPlayerScoreList().get(i);
            model.getPlayerScoreList().set(i, playerScore + player.getScoreRound());
        }

        view.updateView();
    }


    /**
     * Method to end the game.
     * It makes clear to the player that the game has ended,
     * by disabling buttons and modifying text.
     * @param startGameButton the user interface's 'Start Round' button.
     */
    public void endGame(JButton startGameButton) {
        int winnerPlayerIndex = 0;
        int winnerPlayerScore = 0;
        for (int i = 0; i < model.getPlayerScoreList().size(); i++) {
            if (model.getPlayerScoreList().get(i) > winnerPlayerScore) {
                winnerPlayerScore = model.getPlayerScoreList().get(i);
                winnerPlayerIndex = i;
            }
        }
        if (model.getPlayerList().get(winnerPlayerIndex).getIsHuman()) {
            JOptionPane.showMessageDialog(new JFrame(), "Player 1 won!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Computer " + winnerPlayerIndex + " won!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
        startGameButton.setEnabled(false);
        startGameButton.setText("Game Over");
    }


    /**
     * Method to prepare the game's model for the next round.
     */
    public void resetRound() {
        // Reset playerList.
        model.resetPlayerList();

        // Reset gameDeck.
        model.resetDeck();

        // Adjust numberOfTricks for next round.
        if (model.getRoundTracker() >= 6) {
            model.decreaseNumberOfTricks();
        } else {
            model.increaseNumberOfTricks();
        }

        // Reset trickTracker.
        model.resetTrickTracker();

        // Reallocate cards.
        model.reAllocateCards();

        // Update trump suit.
        model.updateTrumpSuit();

        // Set new dealer.
        model.updateDealerIndex();

        // Increase round tracker.
        model.increaseRoundTracker();

        view.resetPlayerCardLabelMap();
        view.updateView();
    }


    /**
     * Method to initialise the game's user interface.
     */
    public void initView() {
        view.resetPlayerCardLabelMap();
        view.updateView();
        JButton startGameButton = view.getStartRoundButton();
        startGameButton.addActionListener(e -> {
            startGameButton.setEnabled(false);
            Runnable runRound = () -> {
                if (!CheckWinner.isGameWinner(model.getPlayerScoreList())) {
                    startGameButton.setText("Round " + model.getRoundTracker() + " Started");
                    placeBids();
                    playAllTricks();
                    settleScores();
                    resetRound();
                    if (CheckWinner.isGameWinner(model.getPlayerScoreList()) || model.getRoundTracker() > 11) {
                        endGame(startGameButton);
                        view.disableTextLabels();
                    } else {
                        startGameButton.setEnabled(true);
                        startGameButton.setText("Start Round " + model.getRoundTracker());
                    }
                }
            };
            new Thread(runRound).start();
        });
    }

}