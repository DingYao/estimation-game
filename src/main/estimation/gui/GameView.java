package estimation.gui;

import estimation.model.Card;
import estimation.model.GameModel;
import estimation.model.Hand;
import estimation.model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.*;


/**
 * Specification of the user interface of an Estimation game.
 */
public class GameView {

    /**
     * Variable to store the user interface's associated game model.
     */
    private GameModel model;
    /**
     * Variable to store the user interface's content panel.
     */
    private JPanel contentPanel;
    /**
     * Variable to store the user interface's 'Start Round' button.
     */
    private JButton startRoundButton;

    /**
     * The human player's first card label in the user interface.
     */
    private JLabel playerCard1;
    /**
     * The human player's second card label in the user interface.
     */
    private JLabel playerCard2;
    /**
     * The human player's third card label in the user interface.
     */
    private JLabel playerCard3;
    /**
     * The human player's fourth card label in the user interface.
     */
    private JLabel playerCard4;
    /**
     * The human player's fifth card label in the user interface.
     */
    private JLabel playerCard5;
    /**
     * The human player's sixth card label in the user interface.
     */
    private JLabel playerCard6;
    /**
     * The first computer player's first card label in the user interface.
     */
    private JLabel computer1Card1;
    /**
     * The first computer player's second card label in the user interface.
     */
    private JLabel computer1Card2;
    /**
     * The first computer player's third card label in the user interface.
     */
    private JLabel computer1Card3;
    /**
     * The first computer player's fourth card label in the user interface.
     */
    private JLabel computer1Card4;
    /**
     * The first computer player's fifth card label in the user interface.
     */
    private JLabel computer1Card5;
    /**
     * The first computer player's sixth card label in the user interface.
     */
    private JLabel computer1Card6;
    /**
     * The second computer player's first card label in the user interface.
     */
    private JLabel computer2Card1;
    /**
     * The second computer player's second card label in the user interface.
     */
    private JLabel computer2Card2;
    /**
     * The second computer player's third card label in the user interface.
     */
    private JLabel computer2Card3;
    /**
     * The second computer player's fourth card label in the user interface.
     */
    private JLabel computer2Card4;
    /**
     * The second computer player's fifth card label in the user interface.
     */
    private JLabel computer2Card5;
    /**
     * The second computer player's sixth card label in the user interface.
     */
    private JLabel computer2Card6;
    /**
     * The third computer player's first card label in the user interface.
     */
    private JLabel computer3Card1;
    /**
     * The third computer player's second card label in the user interface.
     */
    private JLabel computer3Card2;
    /**
     * The third computer player's third card label in the user interface.
     */
    private JLabel computer3Card3;
    /**
     * The third computer player's fourth card label in the user interface.
     */
    private JLabel computer3Card4;
    /**
     * The third computer player's fifth card label in the user interface.
     */
    private JLabel computer3Card5;
    /**
     * The third computer player's sixth card label in the user interface.
     */
    private JLabel computer3Card6;
    /**
     * List to store the players' card labels.
     */
    private List<JLabel> cardLabelList = new ArrayList<JLabel>(Arrays.asList(playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6,
            computer1Card1, computer1Card2, computer1Card3, computer1Card4, computer1Card5, computer1Card6,
            computer2Card1, computer2Card2, computer2Card3, computer2Card4, computer2Card5, computer2Card6,
            computer3Card1, computer3Card2, computer3Card3, computer3Card4, computer3Card5, computer3Card6));
    /**
     * Map of players and their card labels in the user interface.
     */
    private static Map<Player, List<JLabel>> playerCardLabelMap = new HashMap<Player, List<JLabel>>();

    /**
     * The trick's first played card label in the user interface.
     */
    private JLabel playedCard1;
    /**
     * The trick's second played card label in the user interface.
     */
    private JLabel playedCard2;
    /**
     * The trick's third played card label in the user interface.
     */
    private JLabel playedCard3;
    /**
     * The trick's fourth played card label in the user interface.
     */
    private JLabel playedCard4;
    /**
     * List to store the played cards' labels.
     */
    private List<JLabel> playedCardLabelList = new ArrayList<JLabel>(Arrays.asList(playedCard1,
            playedCard2, playedCard3, playedCard4));

    /**
     * The human player's text label in the user interface.
     */
    private JLabel player1;
    /**
     * The first computer player's text label in the user interface.
     */
    private JLabel computer1;
    /**
     * The second computer player's text label in the user interface.
     */
    private JLabel computer2;
    /**
     * The third computer player's text label in the user interface.
     */
    private JLabel computer3;
    /**
     * List to store the players' text labels.
     */
    private List<JLabel> playerLabelList = new ArrayList<JLabel>(Arrays.asList(player1, computer1, computer2, computer3));

    /**
     * The round trick's text label in the user interface.
     */
    private JLabel trickTrackerLabel;
    /**
     * The trump suit's text label in the user interface.
     */
    private JLabel trumpSuitLabel;
    /**
     * The lead suit's text label in the user interface.
     */
    private JLabel leadSuitLabel;
    /**
     * The dealer's text label in the user interface.
     */
    private JLabel dealerLabel;


    /**
     * Instantiate a new GameView object.
     * @param model a GameModel object representing the game's model.
     */
    public GameView(GameModel model) {
        this.model = model;
        JFrame frame = new JFrame("Estimation by G1T09, IS442 AY19/20 Semester 1");
        frame.setLayout(new FlowLayout());
        // get the screen size as a java dimension
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // set the frame height and width
        frame.setSize(new Dimension((int) screenSize.getWidth() * 2 / 3, (int) screenSize.getHeight() * 2 / 3));
        frame.setContentPane(this.contentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    /**
     * Method to set a card image to a label.
     * @param jLabel the label to set a card image.
     * @param card the card which image is to be set to the label.
     * @param isHuman a boolean describing if the label is owned by a human player, or the central
     *                deck displaying the cards played in a trick.
     */
    public void setCardImage(JLabel jLabel, Card card, boolean isHuman){
        if (isHuman) {
            jLabel.setIcon(card.getCardImage());
        } else {
            BufferedImage img = null;
            try {
                img = ImageIO.read(getClass().getResource("/images/cards/back.gif"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ImageIcon icon = new ImageIcon(img);
            jLabel.setIcon(icon);
        }
        jLabel.setVisible(true);
    }


    /**
     * Method to update the game's user interface.
     * Called at the end of every trick and round.
     */
    public void updateView(){
        // Update playerCardLabelLists.
        for(int i = 0; i < model.getPlayerList().size(); i++) {
            Player player = model.getPlayerList().get(i);
            List<JLabel> playerCardLabelList = this.playerCardLabelMap.get(player);
            Hand hand = player.getPlayerHand();
            for (int j = 0; j < hand.getNumberOfCards(); j++) {
                setCardImage(playerCardLabelList.get(j), hand.getCard(j), player.getIsHuman());
            }
            for (int j = hand.getNumberOfCards(); j < 6; j++) {
                playerCardLabelList.get(j).setVisible(false);
            }
            this.trickTrackerLabel.setText("Playing Round " + model.getRoundTracker() + ", Trick " + model.getTrickTracker());
        }

        // Update playedCardLabelList.
        for (int i = 0; i < model.getPlayedThusFar().size(); i++) {
            setCardImage(playedCardLabelList.get(i), model.getPlayedThusFar().get(i), true);
        }
        for (int i = model.getPlayedThusFar().size(); i < 4; i++) {
            playedCardLabelList.get(i).setVisible(false);
        }

        // Update player bid, round score, and total score.
        for (int i = 0; i < model.getPlayerList().size(); i++) {
            Player player = model.getPlayerList().get(i);
            JLabel playerLabel = this.playerLabelList.get(i);
            List<Integer> playerScoreList = model.getPlayerScoreList();
            if (player.getIsHuman()) {
                playerLabel.setText("Player 1: Bids Placed = " + player.getBid() + ", Tricks Won = " + player.getTricksWon() + ", Total Score = " + playerScoreList.get(i));
            } else {
                playerLabel.setText("Computer " + i + ": Bids Placed = " + player.getBid() + ", Tricks Won = " + player.getTricksWon() + ", Total Score = " + playerScoreList.get(i));
            }
        }

        // Update dealerLabel.
        if (model.getDealerIndex() == 0) {
            this.dealerLabel.setText("Round's Dealer = Player 1");
        } else {
            this.dealerLabel.setText("Round's Dealer = Computer " + model.getDealerIndex());
        }

        // Update trumpSuitLabel.
        this.trumpSuitLabel.setText("Round's Trump Suit = " + model.getTrumpSuit());

        // Update leadSuitLabel.
        if (model.getPlayedThusFar().size() == 0) {
            this.leadSuitLabel.setText("No Lead Suit");
        } else {
            this.leadSuitLabel.setText("Trick's Lead Suit = " + model.getPlayedThusFar().get(0).getSuit());
        }
    }


    /**
     * Method to disable the user interface's center text labels.
     * Called at the game's end.
     */
    public void disableTextLabels() {
        this.trickTrackerLabel.setVisible(false);
        this.dealerLabel.setVisible(false);
        this.trumpSuitLabel.setVisible(false);
        this.leadSuitLabel.setVisible(false);
    }


    /**
     * Method to retrieve the user's interface 'Start Round' button.
     * @return a JButton object representing the user's interface 'Start Round' button.
     */
    public JButton getStartRoundButton() {
        return this.startRoundButton;
    }


    /**
     * Method to retrieve the user interface's card labels.
     * @return a list of jLabel objects each representing a card label in the user interface.
     */
    public List<JLabel> getCardLabelList() {
        return this.cardLabelList;
    }


    /**
     * Method to retrieve the user interface's card labels mapped to players.
     * @return a map of players and their card labels in the user interface.
     */
    public Map<Player, List<JLabel>> getPlayerCardLabelMap() {
        return this.playerCardLabelMap;
    }


    /**
     * Method to reset the map of players and their card labels in the user interface.
     * Called at the end of a round when new player objects are instantiated
     * in preparation for the next round, if any.
     */
    public void resetPlayerCardLabelMap() {
        int cardLabelIndex = 0;
        for(int i = 0; i < model.getPlayerList().size(); i++) {
            ArrayList<JLabel> playerCardLabelList = new ArrayList<>();
            Player player = model.getPlayerList().get(i);
            for (int j = 0; j < 6; j++) {
                if (this.playerCardLabelMap.containsKey(player)) {
                    this.playerCardLabelMap.get(player).add(this.cardLabelList.get(cardLabelIndex));
                } else {
                    playerCardLabelList.add(this.cardLabelList.get(cardLabelIndex));
                    this.playerCardLabelMap.put(player, playerCardLabelList);
                }
                cardLabelIndex++;
            }
        }
    }

}