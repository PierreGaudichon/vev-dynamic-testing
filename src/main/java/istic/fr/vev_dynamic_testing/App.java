package istic.fr.vev_dynamic_testing;


import javassist.*;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Hello world!
 *
 */
public class App {

    public final static String TEST_PROJECT = "src/test/resources/Test1";
    public final static String TEST_CLASS = "istic.fr.prog_test.PointTest";
    public final static String MAIN_CLASS = "istic.fr.prog_test.Point";

    private static class MyTranslator implements Translator {
        public void start(ClassPool pool) throws NotFoundException, CannotCompileException {
            System.out.println("starting with classpool");
        }
        public void onLoad(ClassPool pool, String className) {
            System.out.println(className);
        }
    }

    /*public static void main( String[] args ) throws Throwable {
        ClassPool pool = ClassPool.getDefault();
        Loader loader = new Loader(pool);
        Translator lopper = new MyTranslator();
        loader.addTranslator(pool, lopper);
        pool.appendClassPath("/test/resources");
        loader.run("istic.fr.prog_test.PointTest",args);
        PointTest
   }*/

    public static ClassLoader testProjectLoader() throws MalformedURLException {
        return new URLClassLoader(new URL[]{
                new File(TEST_PROJECT + "/target/test-classes").toURL(),
                new File(TEST_PROJECT + "/target/classes").toURL()
        });
    }

    public static void runTest(Class cc) {
        JUnitCore runner = new JUnitCore();
        runner.addListener(new TextListener(System.out));
        runner.run(cc);
    }

    public static void modifyMain() throws NotFoundException, IOException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath(TEST_PROJECT + "/target/classes");
        CtClass cc = pool.get(MAIN_CLASS);
        Arrays.asList(cc.getDeclaredMethods()).forEach(method -> {
            try {
                method.insertBefore("System.out.println(\"LOL\");");
                method.
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
        });
        cc.writeFile(TEST_PROJECT + "/target/classes");
    }

    public static void main( String[] args ) throws NotFoundException, IOException, ClassNotFoundException, CannotCompileException {
    	// first step : modify the class byte-code
        modifyMain();
        //On lance JUnit sur la class de test
        runTest(testProjectLoader().loadClass(TEST_CLASS));
        System.out.println("Done.");
    }

}
