package istic.fr.vev_dynamic_testing;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

/* ----------------------------------------------------------------------------
        BOILERPLATE
 --------------------------------------------------------------------------- */

/**
 * Unit test for simple App.
 */
public class ReportTest extends TestCase {

    // https://stackoverflow.com/a/1119559/3765413
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ReportTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( ReportTest.class );
    }

    /* ------------------------------------------------------------------------
        nbBlockExecution
     ----------------------------------------------------------------------- */

    /**
     * Test nbBlockExecution : Empty
     */
    public void testNbBLockExectutionEmpty() {
        Report report = new Report(Arrays.asList());
        Map<String, Integer> result = report.nbBLockExectution();
        assertTrue(result.isEmpty());
    }

    /**
     * Test nbBlockExecution : declaring only
     */
    public void testNbBLockExectutionDeclaringOnly() {
        Report report = new Report(Arrays.asList(
                new Log(Log.IO.DECLARING, Log.TYPE.BLOCK, "name")
        ));
        Map<String, Integer> result = report.nbBLockExectution();
        assertEquals((int) result.get("name"), 0);
    }

    /**
     * Test nbBlockExecution : several calls
     */
    public void testNbBLockExectutionDeclaringOnlyCalls() {
        Report report = new Report(Arrays.asList(
                new Log(Log.IO.DECLARING, Log.TYPE.BLOCK, "name"),
                new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name"),
                new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name"),
                new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name")
        ));
        Map<String, Integer> result = report.nbBLockExectution();
        assertEquals((int) result.get("name"), 3);
    }

    /**
     * Test nbBlockExecution : useless noise - 1
     */
    public void testNbBLockExectutionDeclaringNoise1() {
        Report report = new Report(Arrays.asList(
                new Log(Log.IO.DECLARING, Log.TYPE.BLOCK, "name"),
                new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name"),
                new Log(Log.IO.DECLARING, Log.TYPE.METHOD, "name"),
                new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name"),
                new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name")
        ));
        Map<String, Integer> result = report.nbBLockExectution();
        assertEquals((int) result.get("name"), 3);
    }

    /**
     * Test nbBlockExecution : useless noise - 2
     */
    public void testNbBLockExectutionDeclaringNoise2() {
        Report report = new Report(Arrays.asList(
                new Log(Log.IO.DECLARING, Log.TYPE.BLOCK, "name"),
                new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name"),
                new Log(Log.IO.DECLARING, Log.TYPE.BLOCK, "name-2"),
                new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name"),
                new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name")
        ));
        Map<String, Integer> result = report.nbBLockExectution();
        assertEquals((int) result.get("name"), 3);
    }

    /**
     * Test nbBlockExecution : useless noise - 3
     */
    public void testNbBLockExectutionDeclaringNoise3() {
        Report report = new Report(Arrays.asList(
                new Log(Log.IO.DECLARING, Log.TYPE.BLOCK, "name"),
                new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name"),
                new Log(Log.IO.BEGIN, Log.TYPE.METHOD, "name-2"),
                new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name"),
                new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, "name")
        ));
        Map<String, Integer> result = report.nbBLockExectution();
        assertEquals((int) result.get("name"), 3);
    }

    /* ------------------------------------------------------------------------
        methodCallSequence
     ----------------------------------------------------------------------- */

    public void testMethodCallSequenceEmpty() {
        Report report = new Report(Arrays.asList());
        List<String> result = report.methodCallSequence();
        List<String> expected = Arrays.asList();
        assertTrue(result.equals(expected));
    }

    public void testMethodCallSequenceBasic() {
        Report report = new Report(Arrays.asList(
                new Log(Log.IO.CALLING, Log.TYPE.METHOD, "name")
        ));
        List<String> result = report.methodCallSequence();
        List<String> expected = Arrays.asList("name");
        assertTrue(result.equals(expected));
    }

    public void testMethodCallSequenceMultiple() {
        Report report = new Report(Arrays.asList(
                new Log(Log.IO.CALLING, Log.TYPE.METHOD, "name"),
                new Log(Log.IO.CALLING, Log.TYPE.METHOD, "name"),
                new Log(Log.IO.CALLING, Log.TYPE.METHOD, "name")
        ));
        List<String> result = report.methodCallSequence();
        List<String> expected = Arrays.asList("name", "name", "name");
        assertTrue(result.equals(expected));
    }

    /* ------------------------------------------------------------------------
        methodCallSequence
     ----------------------------------------------------------------------- */

    public void testPrintMap() {
        System.setOut(new PrintStream(outContent));
        Report.print(new HashMap<String, Integer>(){{
            put("a", 1);
            put("b", 2);
        }});
        assertEquals("a\n\t1\nb\n\t2\n", outContent.toString());
    }

    public void testPrintList() {
        System.setOut(new PrintStream(outContent));
        Report.print(Arrays.asList("a", "b"));
        assertEquals("a\nb\n", outContent.toString());
    }
}
