package istic.fr.vev_dynamic_testing.loggers;

import java.io.IOException;

import java.io.*;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.MethodInfo;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

public class MethodLogerTest extends TestCase {
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MethodLogerTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( MethodLogerTest.class );
    }

    /**
     * Test Constructor
     */
    public void testConstructorLog() {

        //MethodLogger m = new MethodLogger();
    }
    
    public void testMethod() throws CannotCompileException, NotFoundException, IOException {
    	
    	// on créé le pool et on récupère la classe
    	ClassPool pool = ClassPool.getDefault();
    	
    	CtClass logs = pool.get("istic.fr.vev_dynamic_testing.Logs");
    	pool.importPackage("istic.fr.vev_dynamic_testing.Logs");
    	pool.importPackage("istic.fr.vev_dynamic_testing.Log");
    	CtClass STRING = pool.get("java.lang.String");
    			
    	CtClass pointClass = pool.makeClass("Point");
    	CtField xField = new CtField(CtClass.intType,"X",pointClass);
    	CtField yField = new CtField(CtClass.intType,"Y",pointClass);
    	pointClass.addField(xField);
    	pointClass.addField(yField);
    	//pointClass.addConstructor(new CtConstructor(null, pointClass));
    	pointClass.addMethod(CtNewMethod.getter("getX", xField));
        
    	ClassLogger classLogger = new ClassLogger(pointClass, logs);
        classLogger.makeLogs();
        
        pointClass.writeFile();
    }

     
    public static void compare2File(String file1, String file2) {

    		byte buffer[] = new byte[50];
    		try {
    			FileInputStream fis = new FileInputStream(file1);
    			ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
    			int i = fis.read(buffer);
    			while (i!=-1) {
    				int a = bais.read();
    				while (a != -1) {
    					System.out.print((char) a);
    					a = bais.read();
    				}
    				i = fis.read(buffer);
    				bais.reset();
    			}
    			bais.close();
    			fis.close();
    		} catch (FileNotFoundException e){
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    }

}
