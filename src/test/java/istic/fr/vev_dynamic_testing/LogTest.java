package istic.fr.vev_dynamic_testing;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class LogTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public LogTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( AppTest.class );
    }

    /**
     * Test Constructor
     */
    public void testConstructorLog() {
        
    	Log l = new Log ("a","b");
    	
    	assertTrue( l.getType().equals("a") );
    }
}
