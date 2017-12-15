package istic.fr.vev_dynamic_testing.loggers;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

public class MethodLogerTest extends TestCase {
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MethodLogerTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( MethodLogerTest.class );
    }

    /**
     * Test Constructor
     */
    public void testConstructorLog() {

        //MethodLogger m = new MethodLogger();
    }
	
}
