package istic.fr.vev_dynamic_testing;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName ) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        new App();
        assertTrue(true  == true);
    }

    public void testMain() throws IOException, ClassNotFoundException, CannotCompileException, InterruptedException, NotFoundException {
        String expected = "\n" +
                "OK (4 tests)\n" +
                "\n" +
                "Number of execution of each block.\n" +
                "istic.fr.prog_test.Point.notTestedYet(),36,2\n" +
                "\t0\n" +
                "istic.fr.prog_test.Point.getX(),0,5\n" +
                "\t6\n" +
                "istic.fr.prog_test.Point.setY(int),0,6\n" +
                "\t7\n" +
                "istic.fr.prog_test.Point.isOrigin(),14,2\n" +
                "\t1\n" +
                "istic.fr.prog_test.Point.isOrigin(),40,2\n" +
                "\t1\n" +
                "istic.fr.prog_test.Point.getY(),0,5\n" +
                "\t5\n" +
                "istic.fr.prog_test.Point.setX(int),0,6\n" +
                "\t7\n" +
                "istic.fr.prog_test.Point.notTestedYet(),10,2\n" +
                "\t0\n" +
                "istic.fr.prog_test.Point.distance(istic.fr.prog_test.Point),0,47\n" +
                "\t1\n" +
                "Sequence of method calls.\n" +
                "istic.fr.prog_test.Point.setX(int)\n" +
                "istic.fr.prog_test.Point.setY(int)\n" +
                "istic.fr.prog_test.Point.setX(int)\n" +
                "istic.fr.prog_test.Point.setY(int)\n" +
                "istic.fr.prog_test.Point.isOrigin()\n" +
                "istic.fr.prog_test.Point.getX()\n" +
                "istic.fr.prog_test.Point.getY()\n" +
                "istic.fr.prog_test.Point.isOrigin()\n" +
                "istic.fr.prog_test.Point.getX()\n" +
                "istic.fr.prog_test.Point.setX(int)\n" +
                "istic.fr.prog_test.Point.setY(int)\n" +
                "istic.fr.prog_test.Point.setX(int)\n" +
                "istic.fr.prog_test.Point.setY(int)\n" +
                "istic.fr.prog_test.Point.distance(istic.fr.prog_test.Point)\n" +
                "istic.fr.prog_test.Point.getX()\n" +
                "istic.fr.prog_test.Point.getX()\n" +
                "istic.fr.prog_test.Point.getY()\n" +
                "istic.fr.prog_test.Point.getY()\n" +
                "istic.fr.prog_test.Point.setX(int)\n" +
                "istic.fr.prog_test.Point.setY(int)\n" +
                "istic.fr.prog_test.Point.setX(int)\n" +
                "istic.fr.prog_test.Point.setY(int)\n" +
                "istic.fr.prog_test.Point.getX()\n" +
                "istic.fr.prog_test.Point.getY()\n" +
                "istic.fr.prog_test.Point.setX(int)\n" +
                "istic.fr.prog_test.Point.setY(int)\n" +
                "istic.fr.prog_test.Point.getX()\n" +
                "istic.fr.prog_test.Point.getY()";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true, "utf-8");
        System.setOut(ps);
        App.main(new String[0]);
        String[] content = new String(baos.toByteArray(), StandardCharsets.UTF_8).split("\n");
        String trimmed = String.join("\n", Arrays.copyOfRange(content, 2, content.length));
        ps.close();
        assertEquals(expected, trimmed);
    }
}
