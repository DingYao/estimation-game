
package estimation.test;

import estimation.model.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * GameModelTest will test the following :
 * 	the number of tricks will be updated
 * 	the dealer will be rotated in a clock-wise manner
 * 	the winningPlayer can be set and retrieved
 * 	the total number of tricks for a specific round will be increased, decreased and retrieved
 * 	the number of rounds will be updated
 */
public class GameModelTest {
    
    @Test
    public void GameModelGetSetWinningPlayer() {
        GameModel GameModelTest = new GameModel();
        Player winner = new Player();
        GameModelTest.setWinningPlayer(winner);
        assertEquals(winner, GameModelTest.getWinningPlayer());
        System.out.println("Game Model get set Winning player Test is successful");
    }

    @Test
    public void GameModelTrickTracker() {
        GameModel GameModelTest = new GameModel();
        
        GameModelTest.increaseTrickTracker();
        GameModelTest.increaseTrickTracker();
        assertEquals(3, GameModelTest.getTrickTracker());
        GameModelTest.resetTrickTracker();
        assertEquals(1, GameModelTest.getTrickTracker());
        System.out.println("Game Model Trick Tracker Test is successful");
    }

    @Test
    public void GameModelNumberOfTricks() {
        GameModel GameModelTest = new GameModel();
        
        GameModelTest.increaseNumberOfTricks();
        GameModelTest.increaseNumberOfTricks();
        assertEquals(3, GameModelTest.getNumberOfTricks());
        GameModelTest.decreaseNumberOfTricks();
        assertEquals(2, GameModelTest.getNumberOfTricks());
        System.out.println("Game Model number of tricks Test is successful");
    }

    @Test
    public void GameModelRoundTracker() {
        GameModel GameModelTest = new GameModel();
        
        GameModelTest.increaseRoundTracker();
        GameModelTest.increaseRoundTracker();
        assertEquals(3, GameModelTest.getRoundTracker());
        System.out.println("Game Model Round Tracker Test is successful");
    }



    @Test
    public void GameModelDealerIndex(){
        GameModel GameModelTest = new GameModel();
        int dealerIndex = GameModelTest.getDealerIndex();
        if (dealerIndex == 3){
            GameModelTest.updateDealerIndex();
            assertEquals(0, GameModelTest.getDealerIndex());  
        }else{
            GameModelTest.updateDealerIndex();
            assertEquals(dealerIndex+1, GameModelTest.getDealerIndex()); 

        }
        System.out.println("Game Model dealer index Test is successful");
    }

}