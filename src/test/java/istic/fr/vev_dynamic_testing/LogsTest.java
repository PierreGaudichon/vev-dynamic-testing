package istic.fr.vev_dynamic_testing;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import istic.fr.vev_dynamic_testing.Log.IO;
import istic.fr.vev_dynamic_testing.Log.TYPE;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple Log.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    
    /***
     * test length
     */
    public void testA_Length() {
    	
    	Logs l = Logs.getInstance();
    	int i = l.getLogs().size();
    	l.addLogs("BEGIN","BLOCK", "b");
    	int j = l.getLogs().size();
    	assertTrue( i == 0 && j == 1 );
    	
    }
    
    /***
     * test length
     */
    public void testB_Remove() {
    	
    	Logs l = Logs.getInstance();
    	l.addLogs("BEGIN","BLOCK","message");
    	int i = l.getLogs().size();
    	l.removeLogs();
    	int j = l.getLogs().size();
    	assertTrue( i > 0 && j == 0 );
    	
    }
    
    public void testC_Lol() {
    	assertTrue(true);
    }

}