package istic.fr.vev_dynamic_testing;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws NotFoundException
    {
    	// first step : modify the Junit code
    	ClassPool pool = ClassPool.getDefault();
    	CtClass cc = pool.get("src.test.resources.prog_test.PointTest");
    	
    	// second step : run Junit
    	runningStation(cc);
    	 
    }
    
    public static void runningStation(CtClass cc) {
  	    JUnitCore runner = new JUnitCore();
  	    runner.addListener(new TextListener(System.out));
  	    runner.run(cc.getClass());
  	}
}
