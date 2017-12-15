package istic.fr.vev_dynamic_testing.loggers;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import org.junit.Before;

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

    }
	
}
