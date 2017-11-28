package istic.fr.vev_dynamic_testing;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import org.junit.Before;

import istic.fr.vev_dynamic_testing.Log.IO;
import istic.fr.vev_dynamic_testing.Log.TYPE;

public class ClassLogerTest extends TestCase {
	
	/**
	 * méthode qui initialise le test
	 */
	@Before 
	public void initialize() {
		//empty= new ArrayList();
	}
	
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
        
    	Log l = new Log (IO.BEGIN,TYPE.METHOD,"b");
    	
    	assertTrue( l.getType().equals("a") );
    }
	
}
