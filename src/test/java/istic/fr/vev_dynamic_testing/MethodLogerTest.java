package istic.fr.vev_dynamic_testing;

import istic.fr.vev_dynamic_testing.loggers.MethodLogger;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import istic.fr.vev_dynamic_testing.Log.IO;
import istic.fr.vev_dynamic_testing.Log.TYPE;

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
