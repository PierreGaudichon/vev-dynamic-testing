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
        return new TestSuite( LogTest.class );
    }

    /**
     * Test Constructor
     */
    public void testConstructorLog() {
        
    	Log l = new Log (Log.IO.BEGIN, Log.TYPE.METHOD, "b");

    	assertTrue(l.getIo().equals(Log.IO.BEGIN));
    	assertTrue(l.getType().equals(Log.TYPE.METHOD));
    	assertTrue(l.getMessage().equals("b"));
    }
}
