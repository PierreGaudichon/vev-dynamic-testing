package istic.fr.vev_dynamic_testing;

import javassist.Loader;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* ----------------------------------------------------------------------------
        BOILERPLATE
 --------------------------------------------------------------------------- */

/**
 * Unit test for simple App.
 */
public class ReportTest extends TestCase {

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
        System.out.println(result);
        List<String> expected = Arrays.asList("name", "name", "name");
        assertTrue(result.equals(expected));
    }


}
