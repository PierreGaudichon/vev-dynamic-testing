package istic.fr.vev_dynamic_testing;


import javassist.*;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.Mnemonic;
import javassist.bytecode.analysis.ControlFlow;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sun.management.MethodInfo;

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
    
    private static CtClass logs = null;

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
        //runner.addListener(new TextListener(System.out));
        runner.run(cc);
    }
    
    public static String sop(String log) {
    	
    	String ret = "logs = Logs.getInstance(); logs.addLogs(\""+log+"\");";
    	System.out.println(ret);
    	
    	return ret;
    }

    public static void addCallingNameUnsafe(String type, CtBehavior behavior) throws CannotCompileException {
    	behavior.addLocalVariable("logs", logs);
        behavior.insertBefore(sop("TRACE "+type+" : "+behavior.getLongName()));
    }

    public static void addCallingName(String type, CtBehavior behavior) {
        try {
            addCallingNameUnsafe(type, behavior);
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
    }

    public static void inspectMethodUnsafe(CtMethod method) throws BadBytecode {
        System.out.println("---");
        ControlFlow cf = new ControlFlow(method);
        Arrays.asList(cf.basicBlocks()).forEach(block -> {
            System.out.println(block.toString());
            for (int i = 0; i < block.exits(); i++) {

            }
        });
    }

    /*public static void inspectMethodUnsafe(CtMethod method) {
        method.getBody
    }*/

    public static void inspectMethod(CtMethod method) {
        try {
            inspectMethodUnsafe(method);
        } catch (BadBytecode badBytecode) {
            badBytecode.printStackTrace();
        }
    }

    private static void wrapMethod(CtMethod method) {
        
    }


    public static void modifyMain() throws NotFoundException, IOException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath(TEST_PROJECT + "/target/classes");
        
        // on compile la classe log dans le projet à tester
        // et on ajoute l'import pour pouvoir l'utiliser dans les tests
        logs = pool.get("istic.fr.vev_dynamic_testing.Logs");
    	logs.writeFile(TEST_PROJECT+"/target/classes");
    	pool.importPackage("istic.fr.vev_dynamic_testing.Logs");
        
        CtClass cc = pool.get(MAIN_CLASS);
        List<javassist.bytecode.MethodInfo> methods = cc.getClassFile().getMethods();
        methods.forEach(method -> {
            //System.out.println("---");
            CodeIterator iterator = method.getCodeAttribute().iterator();
            while(iterator.hasNext()) {
                int index = 0;
                try {
                    index = iterator.next();
                } catch (BadBytecode badBytecode) {
                    badBytecode.printStackTrace();
                }
                int op = iterator.byteAt(index);
                //System.out.println(Mnemonic.OPCODE[op]);
            }
        });
        //Arrays.asList(cc.getConstructors())
        //        .forEach(constructor -> addCallingName("constructor", constructor));
        Arrays.asList(cc.getDeclaredMethods())
                .forEach((CtMethod method) -> {
                    //wrapMethod(method);
                    //inspectMethod(method);
                    addCallingName("method", method);
                });
        cc.writeFile(TEST_PROJECT + "/target/classes");
    }


    public static void main( String[] args ) throws NotFoundException, IOException, ClassNotFoundException, CannotCompileException {
    	// first step : modify the class byte-code
        modifyMain();
        //On lance JUnit sur la class de test
        runTest(testProjectLoader().loadClass(TEST_CLASS));
        System.out.println("Done.");
        
        // on récupère les logs de l'exécution
        // et on les affiche
        Logs l = Logs.getInstance();
        System.out.println(l.getResultat());
    }

}