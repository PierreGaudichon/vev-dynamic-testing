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

    public void testToStatement() {
        Log l = new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name");
        String expected = "Logs.getInstance().addLogs(\"BEGIN\", \"BLOCK\", \"name\");";
        assertEquals(expected, l.toStatement());
    }

    public void testIsBeginBlock() {
        Log l1 = new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name");
        assertTrue(l1.isBeginBlock());
        Log l2 = new Log(Log.IO.END, Log.TYPE.BLOCK, "name");
        assertFalse(l2.isBeginBlock());
    }

    public void testIsBeginMethod() {
        Log l1 = new Log(Log.IO.BEGIN, Log.TYPE.METHOD, "name");
        assertTrue(l1.isBeginMethod());
        Log l2 = new Log(Log.IO.END, Log.TYPE.METHOD, "name");
        assertFalse(l2.isBeginMethod());
        Log l3 = new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name");
        assertFalse(l3.isBeginMethod());
    }

    public void testIsDeclaringBlock() {
        Log l1 = new Log(Log.IO.DECLARING, Log.TYPE.BLOCK, "name");
        assertTrue(l1.isDeclaringBlock());
        Log l2 = new Log(Log.IO.END, Log.TYPE.BLOCK, "name");
        assertFalse(l2.isDeclaringBlock());
    }

    public void testIsCallingMethod() {
        Log l1 = new Log(Log.IO.CALLING, Log.TYPE.METHOD, "name");
        assertTrue(l1.isCallingMethod());
        Log l2 = new Log(Log.IO.CALLING, Log.TYPE.BLOCK, "name");
        assertFalse(l2.isCallingMethod());
        Log l3 = new Log(Log.IO.END, Log.TYPE.METHOD, "name");
        assertFalse(l3.isCallingMethod());
    }
}
