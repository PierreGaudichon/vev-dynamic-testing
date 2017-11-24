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
    
    public static String addLog(String log) {
    	
    	String ret = "logs = Logs.getInstance(); logs.addLogs(\""+log+"\");";
    	return ret;
    }

    public static void addCallingNameUnsafe(String type, CtBehavior behavior) throws CannotCompileException {
    	behavior.addLocalVariable("logs", logs);
        behavior.insertBefore(addLog("TRACE "+type+" : "+behavior.getLongName()));
    }

    public static void addCallingName(String type, CtBehavior behavior) {
        try {
            addCallingNameUnsafe(type, behavior);
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
    }

    public static boolean isIfBLock(ControlFlow.Block block, CodeIterator iterator) {
        int startBlock = block.position();
        int endBlock = startBlock + block.length();
        for(int i = startBlock; i < endBlock; i++) {
            if(Mnemonic.OPCODE[iterator.byteAt(i)].contains("if")) {
                return true;
            }
        }
        return false;
    }

    public static Bytecode createTraceStatement(ControlFlow.Block block, ClassFile classFile, CtClass cc) throws CompileError {
        String print = "TRACE block : ("+block.position()+", "+block.length()+")";
        /*Bytecode bytes = new Bytecode(classFile.getConstPool());
        bytes.addPrintln(print);
        return bytes;*/
        Javac jv = new Javac(cc);
        jv.compileStmnt("Logs.getInstance().addLogs(\""+print+"\");");
        return jv.getBytecode();

    }

    public static ControlFlow.Block[] getBasicBlocks(CtMethod method) throws BadBytecode {
        return new ControlFlow(method).basicBlocks();
    }

    public static void inspectMethodUnsafe(CtMethod method, ClassFile classFile, CtClass cc) throws BadBytecode, CompileError {
        //System.out.println("--- " + method.getName());
        MethodInfo info = classFile.getMethod(method.getName());
        CodeIterator iterator = info.getCodeAttribute().iterator();
        for(int i = 0; i < getBasicBlocks(method).length; i++) {
            ControlFlow.Block block = getBasicBlocks(method)[i];
            if(!isIfBLock(block, iterator)) {
                iterator.insertAt(block.position(), createTraceStatement(block, classFile, cc).get());
            }
        }
    }

    public static void inspectMethod(CtMethod method, ClassFile classFile, CtClass cc) {
        try {
            inspectMethodUnsafe(method, classFile, cc);
        } catch (BadBytecode badBytecode) {
            badBytecode.printStackTrace();
        } catch (CompileError compileError) {
            compileError.printStackTrace();
        }
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
        Arrays.asList(cc.getDeclaredMethods())
                .forEach((CtMethod method) -> {
                    inspectMethod(method, classFile, cc);
                    addCallingName("method", method);
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