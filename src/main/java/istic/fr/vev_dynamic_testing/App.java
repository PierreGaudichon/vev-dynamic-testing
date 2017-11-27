package istic.fr.vev_dynamic_testing;


import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.analysis.ControlFlow;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;
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
import java.util.List;
import java.util.function.Consumer;

/**
 * Hello world!
 *
 */
public class App {

    public final static String TEST_PROJECT = "src/test/resources/Test1";
    public final static String TEST_CLASS = "istic.fr.prog_test.PointTest";
    public final static String MAIN_CLASS = "istic.fr.prog_test.Point";
    
    // ctClass qui représente le logger
    private static CtClass logs = null;

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
    
    public static String addLog(String log) {
    	String ret = "logs = Logs.getInstance(); logs.addLogs(\""+log+"\");";
    	return ret;
    }

    public static void modifyMain() throws NotFoundException, IOException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath(TEST_PROJECT + "/target/classes");
        
        // on compile la classe log dans le projet à tester
        // et on ajoute l'import pour pouvoir l'utiliser dans les tests
        logs = pool.get("istic.fr.vev_dynamic_testing.Logs");
    	//logs.writeFile(TEST_PROJECT+"/target/classes");
    	pool.importPackage("istic.fr.vev_dynamic_testing.Logs");
        
        CtClass cc = pool.get(MAIN_CLASS);
        ClassFile classFile = cc.getClassFile();

        //Arrays.asList(cc.getConstructors())
        //        .forEach(constructor -> addCallingName("constructor", constructor));
        Arrays.asList(cc.getDeclaredMethods()).forEach((CtMethod method) -> {
            new MethodLogger(cc, method).makeLogs();
        });

        cc.writeFile(TEST_PROJECT + "/target/classes");
    }


    public static void main( String[] args ) throws NotFoundException, IOException, ClassNotFoundException, CannotCompileException {
    	
    	Logs l = Logs.getInstance();
    	l.removeLogs();
    	l.addLogs("start");
    	
    	// first step : modify the class byte-code
        modifyMain();
        //On lance JUnit sur la class de test
        runTest(testProjectLoader().loadClass(TEST_CLASS));
        System.out.println("Done.");
        
        // on récupère les logs de l'exécution
        // et on les affiche
        l = Logs.getInstance();
        l.addLogs("end");
        System.out.println(l.getResultat());
    }

}