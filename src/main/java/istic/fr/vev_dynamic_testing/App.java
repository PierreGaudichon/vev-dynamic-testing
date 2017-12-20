package istic.fr.vev_dynamic_testing;


import istic.fr.vev_dynamic_testing.loggers.ClassLogger;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Hello world!
 *
 */
public class App {

    public final static String TEST_PROJECT = "examples/Test1";
    public final static String TEST_CLASS = "istic.fr.prog_test.PointTestNot";
    public final static String MAIN_CLASS = "istic.fr.prog_test.Point";
    
    // ctClass qui représente le logger
    // private static CtClass logs = null;

    public static void buildProject() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("./build_test_project.sh");
        process.waitFor();
        // Runtime.getRuntime().exec("./clone_and_build.sh");
    }

    public static void modifyMain() throws NotFoundException, IOException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath("target/classes");
        pool.appendClassPath(TEST_PROJECT + "/target/classes");

        // on compile la classe log dans le projet à tester
        // et on ajoute l'import pour pouvoir l'utiliser dans les tests
        //CtClass logs = pool.get("istic.fr.vev_dynamic_testing.Logs");
        //logs.writeFile(TEST_PROJECT+"/target/classes");
        pool.importPackage("istic.fr.vev_dynamic_testing");
        pool.importPackage("istic.fr.vev_dynamic_testing.Log");

        System.out.println(pool.find("Logs"));

        CtClass cc = pool.get(MAIN_CLASS);
        // ClassLogger classLogger = new ClassLogger(cc, logs);
        ClassLogger classLogger = new ClassLogger(cc, null);
        classLogger.makeLogs();

        cc.writeFile(TEST_PROJECT + "/target/classes");
    }

    public static void runTest() throws MalformedURLException, ClassNotFoundException {
        ClassLoader classLoader = new URLClassLoader(new URL[]{
                new File(TEST_PROJECT + "/target/test-classes").toURL(),
                new File(TEST_PROJECT + "/target/classes").toURL()
        });
        Class cc = classLoader.loadClass(TEST_CLASS);
        JUnitCore runner = new JUnitCore();
        runner.addListener(new TextListener(System.out));
        runner.run(cc);
    }

    /*public static void runTest() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("cd examples/Test1 ; mvn surefire:test");
        process.waitFor();
    }*/

    public static void printReports() {
        Logs l = Logs.getInstance();
        Report r = new Report(l.getLogs());

        System.out.println("Number of execution of each block.");
        Report.print(r.nbBLockExectution());

        System.out.println("Sequence of method calls.");
        Report.print(r.methodCallSequence());
    }

    public static void main( String[] args ) throws NotFoundException, IOException, ClassNotFoundException, CannotCompileException, InterruptedException {
        // Reset logs.
    	Logs.getInstance().removeLogs();
    	// Recompile target project
        buildProject();
    	// first step : modify the class byte-code
        modifyMain();
        //On lance JUnit sur la class de test
        runTest();
        // on récupère les logs de l'exécution et on les affiche
        printReports();
    }

}