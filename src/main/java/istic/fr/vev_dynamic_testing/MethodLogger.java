package istic.fr.vev_dynamic_testing;

import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.analysis.ControlFlow;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;

import java.util.Arrays;
import java.util.List;

public class MethodLogger {

    private CtClass logs;
    private CtClass cc;
    private ClassFile classFile;
    private CtMethod method;
    private MethodInfo info;
    private CodeIterator iterator;

    public MethodLogger(CtClass cc, CtMethod method, CtClass logs) {
        this.cc = cc;
        this.logs = logs;
        this.classFile = cc.getClassFile();
        this.method = method;
        info = classFile.getMethod(method.getName());
        iterator = info.getCodeAttribute().iterator();
    }

    private List<ControlFlow.Block> getBlocks() throws BadBytecode {
        return Arrays.asList(new ControlFlow(method).basicBlocks());
    }

    private ControlFlow.Block getBlock(int i) throws BadBytecode {
        return getBlocks().get(i);
    }

    private boolean isIfBLock(ControlFlow.Block block) {
        int start = block.position();
        int end = start + block.length();
        for(int i = start; i < end; i++) {
            if(Mnemonic.OPCODE[iterator.byteAt(i)].contains("if")) {
                return true;
            }
        }
        return false;
    }

    private Bytecode getBytecode(String print) throws CompileError {
        // `Javac` is an internal part of Javassist that should probably not be used here.
        // I couldn't find a way to construct bytecode from string directly from Javassist.
        // The next three lines do exactly what we want though.
        Javac jv = new Javac(cc);
        jv.compileStmnt(print);
        return jv.getBytecode();
    }

    private void makeBlockLogs() throws BadBytecode, CompileError {
        for(int i = 0; i < getBlocks().size(); i++) {
            if(!isIfBLock(getBlock(i))) {
                String info = method.getLongName()+","+getBlock(i).position()+","+getBlock(i).length();
                String before = new Log(Log.IO.BEGIN, Log.TYPE.BLOCK, info).toStatement();
                String after = new Log(Log.IO.END, Log.TYPE.BLOCK, info).toStatement();
                iterator.insertAt(getBlock(i).position(), getBytecode(before).get());
                int position = getBlock(i).position() + getBlock(i).length() - 1;
                iterator.insertAt(position, getBytecode(after).get());
            }
        }
    }

    private void makeMethodLogs() throws CannotCompileException {
        method.insertBefore(new Log(Log.IO.BEGIN, Log.TYPE.METHOD, method.getLongName()).toStatement());
        method.insertAfter(new Log(Log.IO.END, Log.TYPE.METHOD, method.getLongName()).toStatement());
    }

    public void makeLogs() {
        try {
            makeBlockLogs();
            makeMethodLogs();
        } catch (BadBytecode badBytecode) {
            badBytecode.printStackTrace();
        } catch (CompileError compileError) {
            compileError.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
    }
}
