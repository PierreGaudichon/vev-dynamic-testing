package istic.fr.vev_dynamic_testing;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

public class ClassLogerTest extends TestCase {
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ClassLogerTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( ClassLogerTest.class );
    }

    /**
     * Test Constructor
     */
    public void testConstructorLog() {
        
    	Log l = new Log ("a","b");
    	
    	assertTrue( l.getType().equals("a") );
    }
	
}
