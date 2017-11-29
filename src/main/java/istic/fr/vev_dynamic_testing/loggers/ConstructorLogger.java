package istic.fr.vev_dynamic_testing.loggers;

import istic.fr.vev_dynamic_testing.Log;
import istic.fr.vev_dynamic_testing.Logs;
import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.bytecode.ClassFile;

public class ConstructorLogger implements CtXLogger {

    private CtClass logs;
    private CtClass cc;
    private ClassFile classFile;
    private CtConstructor constructor;

    public ConstructorLogger(CtClass cc, CtConstructor constructor, CtClass logs) {
        this.cc = cc;
        this.logs = logs;
        this.classFile = cc.getClassFile();
        this.constructor = constructor;
    }

    public void makeLogs() {
        try {
            String name = constructor.getLongName();
            Logs.getInstance().addLogs(new Log(Log.IO.DECLARING, Log.TYPE.CONSTRUCTOR, name));
            constructor.insertBefore(new Log(Log.IO.BEGIN, Log.TYPE.CONSTRUCTOR, name).toStatement());
            constructor.insertAfter(new Log(Log.IO.END, Log.TYPE.CONSTRUCTOR, name).toStatement());
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
    }
}
