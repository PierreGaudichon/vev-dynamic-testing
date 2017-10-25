package istic.fr.vev_dynamic_testing;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.Loader;
import javassist.NotFoundException;
import javassist.Translator;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import istic.fr.prog_test.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// first step : modify the Junit code
    	
    	// second step : run Junit
    	runningStation();
    	 
    }
    
    public static void runningStation() {
  	    JUnitCore runner = new JUnitCore();
  	    runner.addListener(new TextListener(System.out));
  	    runner.run(PointTest.class);
  	}
}
