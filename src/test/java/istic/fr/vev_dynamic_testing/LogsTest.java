package istic.fr.vev_dynamic_testing;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple Log.
 */
public class LogsTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public LogsTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( LogsTest.class );
    }

    /**
     * Test Constructor
     */
    public void testConstructorLog() {
        
    	Logs l = Logs.getInstance();
    	
    	assertTrue(true);
    }

}