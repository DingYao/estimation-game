package estimation.test;

import estimation.model.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * PlayerTest
 * 	able to set and check a player’s type (human/computer)
 * 	able to calculate the player’s score according to his bid after one round
 * 	able to update and check a player’s total number of tricks he has won.
 */

public class PlayerTest { 

    @Test
    public void PlayerTypeTest() {
        Player player1 = new Player();
        player1.setIsHuman();
        Player player2 = new Player();
        assertEquals(true,player1.getIsHuman());
        assertEquals(false,player2.getIsHuman());
        System.out.println("Player type Test is successful");
    }

    @Test
    public void PlayerDealerTest() {
        Player player1 = new Player();
        player1.setIsDealer();
        Player player2 = new Player();
        assertEquals(true,player1.getIsDealer());
        assertEquals(false,player2.getIsDealer());
        System.out.println("Player dealer Test is successful");
    }

    @Test
    public void PlayerScoreRoundTest() {
        Player player1 = new Player();
        player1.setBid(1);
        Player player2 = new Player();
        player2.setBid(1);
        player2.increaseScoreRound();
        Player player3 = new Player();
        player3.setBid(2);
        player3.increaseScoreRound();
        player3.setBid(1);
        player3.decreaseScoreRound();
        assertEquals(0,player1.getScoreRound());
        assertEquals(11,player2.getScoreRound());
        assertEquals(1,player3.getScoreRound());
        System.out.println("Player score per round Test is successful");
    }

    @Test
    public void PlayerTricksWonTest() {
        Player player1 = new Player();
        Player player2 = new Player();
        player2.increaseTricksWon();
        assertEquals(0,player1.getTricksWon());
        assertEquals(1,player2.getTricksWon());
        System.out.println("Player tricks won test is successful");
    }


    
}

