package istic.fr.vev_dynamic_testing.loggers;

import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;

import java.util.Arrays;

public class ClassLogger implements CtXLogger {

    // ClassPool pool;
    CtClass cc;
    CtClass logs;
    // ClassFile classFile;

    public ClassLogger(CtClass cc, CtClass logs) {
        this.cc = cc;
        this.logs = logs;
        // classFile = cc.getClassFile();
    }

    private MethodLogger createMethodLogger(CtMethod method) {
        return new MethodLogger(cc, method, logs);
    }

    private CtXLogger createConstructorLogger(CtConstructor constructor) {
        return new ConstructorLogger(cc, constructor, logs);
    }

    public void makeLogs() {
        Arrays.asList(cc.getConstructors()).forEach(constructor -> {
            createConstructorLogger(constructor).makeLogs();
        });
        Arrays.asList(cc.getDeclaredMethods()).forEach((CtMethod method) -> {
            createMethodLogger(method).makeLogs();
        });
    }
}
