package estimation.test;

import estimation.model.Suit;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * SuitTest
 * 	able to perform suit comparison after the trump suit has been set.
 */
public class SuitTest {

    @Test
    public void SuitNameTest() {
        Suit suit1 = Suit.CLUBS;
        Suit suit2 = Suit.SPADES;
        Suit suit3 = Suit.HEARTS;
        Suit suit4 = Suit.DIAMONDS;
        assertEquals("Clubs",suit1.getName());
        assertEquals("Spades",suit2.getName());
        assertEquals("Hearts",suit3.getName());
        assertEquals("Diamonds",suit4.getName());
        System.out.println("Suit name Test is successful");
    }

    @Test
    public void SuitSymbolTest() {
        Suit suit1 = Suit.CLUBS;
        Suit suit2 = Suit.SPADES;
        Suit suit3 = Suit.HEARTS;
        Suit suit4 = Suit.DIAMONDS;
        assertEquals("c",suit1.getSymbol());
        assertEquals("s",suit2.getSymbol());
        assertEquals("h",suit3.getSymbol());
        assertEquals("d",suit4.getSymbol());
        System.out.println("Suit symbol Test is successful");
    }

    @Test
    public void SuitCompareToTest() {
        
        Suit suit1 = Suit.CLUBS;
        Suit suit2 = Suit.SPADES;
        Suit suit3 = Suit.HEARTS;
        Suit suit4 = Suit.DIAMONDS;
        //if diamonds is trump
        List<Suit> suitListWithoutTrump = new ArrayList<Suit>();
        suitListWithoutTrump.add(suit1);
        suitListWithoutTrump.add(suit3);
        suitListWithoutTrump.add(suit2);
        // ascending order : clubs, diamonds, hearts, spade
        assertTrue(suit2.compareTo(suit3,suitListWithoutTrump)>0);
        assertTrue(suit3.compareTo(suit1,suitListWithoutTrump)>0);
        assertTrue(suit1.compareTo(suit2,suitListWithoutTrump)<0);
        System.out.println("Suit compare Test is successful");
    }





    
}