package istic.fr.vev_dynamic_testing;

import javassist.*;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    /*public static void main( String[] args ) throws NotFoundException, CannotCompileException, IOException {
        System.out.println("Hello !");
    }*/

    private static class MyTranslator implements Translator {
        public void start(ClassPool pool) throws NotFoundException, CannotCompileException {
            System.out.println("starting with classpool");
        }
        public void onLoad(ClassPool pool, String className) {
            System.out.println(className);
        }
    }

    public static void main( String[] args ) throws Throwable
    {
        ClassPool pool = ClassPool.getDefault();
        Loader loader = new Loader(pool);
        Translator lopper = new MyTranslator();
        loader.addTranslator(pool, lopper);
        pool.appendClassPath("/test/resources");
        loader.run("istic.fr.prog_test.PointTest",args);
        PointTest
    }
}
