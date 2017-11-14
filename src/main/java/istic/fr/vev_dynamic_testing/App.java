package istic.fr.vev_dynamic_testing;


import javassist.*;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

/**
 * Hello world!
 *
 */
public class App {

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
   
    public static void main( String[] args ) throws NotFoundException
    {
    	// first step : modify the Junit code
    	ClassPool pool = ClassPool.getDefault();
    	pool.appendClassPath("src/test/resources/Test1/target/test-classes/istic/fr/prog_test");
    	CtClass cc = pool.get("istic.fr.prog_test.PointTest");


    	// second step : run Junit
    	runningStation(cc);
    	 
    }
    
    public static void runningStation(CtClass cc) {
  	    JUnitCore runner = new JUnitCore();
  	    runner.addListener(new TextListener(System.out));
  	    runner.run(cc.getClass());
  	}
}
