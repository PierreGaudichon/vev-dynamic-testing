package istic.fr.vev_dynamic_testing;


import javassist.*;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Hello world!
 *
 */
public class App {

    public final static String TEST_PROJECT = "src/test/resources/Test1";
    public final static String TEST_CLASS = "istic.fr.prog_test.PointTest";

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
   
    public static void main( String[] args ) throws NotFoundException, MalformedURLException, ClassNotFoundException {
    	// first step : modify the Junit code
    	//ClassPool pool = ClassPool.getDefault();
    	//pool.appendClassPath(TEST_PROJECT + "/target/test-classes/istic/fr/prog_test");
    	//CtClass cc = pool.get(TEST_CLASS);
    	// second step : run Junit
    	//runningStation(cc);

        // Réponse de Nicolas
        ClassLoader cl = new URLClassLoader(new URL[]{
                new File(TEST_PROJECT + "/target/test-classes").toURL(),
                new File(TEST_PROJECT + "/target/classes").toURL()
        });

        //On lance JUnit sur la class de test
        runningStation(cl.loadClass(TEST_CLASS));
    }
    
    public static void runningStation(Class cc) {
  	    JUnitCore runner = new JUnitCore();
  	    runner.addListener(new TextListener(System.out));
  	    runner.run(cc);
  	}
}
