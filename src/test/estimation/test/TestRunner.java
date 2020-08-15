package estimation.test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * TestRunner will run all the test cases and output the success and failure message.
 */

public class TestRunner {
    public static void main(String[] args){
        Result result = JUnitCore.runClasses(AllTests.class);
        for (Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
