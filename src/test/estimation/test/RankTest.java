package estimation.test;

import estimation.model.Rank;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * RankTest
 * 	able to perform rank comparison accurately.
 */
public class RankTest {

    @Test
    public void RankCompareToTest() {
        Rank ace = Rank.ACE;
        Rank five = Rank.FIVE;
        Rank jack = Rank.JACK;

        assertTrue(ace.compareTo(five)>0);
        assertTrue(five.compareTo(jack)<0);
        assertTrue(jack.compareTo(ace)<0);
        
        System.out.println("Rank compareTo Test is successful");
    }

    

}