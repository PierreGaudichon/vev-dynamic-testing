package istic.fr.vev_dynamic_testing;


import istic.fr.vev_dynamic_testing.loggers.ClassLogger;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {

    // Test1 - Point
    public final static String TEST_PROJECT = "examples/Test1";
    public final static String PACKAGE_NAME = "istic.fr.prog_test";
    public final static String BUILD_COMMAND = "./build_test_project.sh";

    /*// commons-cli
    public final static String TEST_PROJECT = "examples/commons-cli";
    public final static String PACKAGE_NAME = "org.apache.commons.cli";
    public final static String BUILD_COMMAND = "./clone_and_build.sh";
    */
    //TEST
    // ctClass qui représente le logger
    // private static CtClass logs = null;

    public static List<String> getJavaClassNames(String folderPath) {
        File folder = new File(folderPath);
        String[] extentions = new String[] { "java" };
        return FileUtils.listFiles(folder, extentions, true).stream()
                .map(file -> FilenameUtils.removeExtension(file.getName()))
                .collect(Collectors.toList());
    }


    public static void buildProject() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(BUILD_COMMAND);
        process.waitFor();
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

        for(String name : getJavaClassNames(TEST_PROJECT + "/src/main")) {
            CtClass cc = pool.get(PACKAGE_NAME + "." + name);
            new ClassLogger(cc, null).makeLogs();
            cc.writeFile(TEST_PROJECT + "/target/classes");
        }
    }


    public static void runTest() throws MalformedURLException, ClassNotFoundException {
        ClassLoader classLoader = new URLClassLoader(new URL[]{
                new File(TEST_PROJECT + "/target/test-classes").toURL(),
                new File(TEST_PROJECT + "/target/classes").toURL()
        }, ClassLoader.getSystemClassLoader());
        JUnitCore runner = new JUnitCore();
        runner.addListener(new TextListener(System.out));
        for(String name : getJavaClassNames(TEST_PROJECT + "/src/test")) {
            runner.run(classLoader.loadClass(PACKAGE_NAME + "." + name));
        }
    }


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