package istic.fr.vev_dynamic_testing;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.ClassFile;

import java.util.Arrays;

public class ClassLogger {

    // ClassPool pool;
    CtClass cc;
    // ClassFile classFile;

    public ClassLogger(CtClass cc) {
        this.cc = cc;
        // classFile = cc.getClassFile();
    }

    private MethodLogger createMethodLogger(CtMethod method) {
        return new MethodLogger(cc, method);
    }

    public void makeLogs() {
        //Arrays.asList(cc.getConstructors())
        //        .forEach(constructor -> addCallingName("constructor", constructor));
        Arrays.asList(cc.getDeclaredMethods()).forEach((CtMethod method) -> {
            createMethodLogger(method).makeLogs();
        });
    }
}
