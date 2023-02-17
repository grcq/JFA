package dev.grcq.processing;

import dev.grcq.bytecode.JFAClass;
import dev.grcq.bytecode.JFAMethod;

import java.lang.annotation.Annotation;

public class TestHandler extends AnnotationByteCodeHandler<Test> {

    @Override
    public JFAClass process(Annotation anno, JFAClass jfaClass) {
        jfaClass.addMethod(new JFAMethod("myMethod"));

        System.out.println(jfaClass);
        System.out.println(anno.toString());

        return jfaClass;
    }

    @Override
    public String getFqdn() {
        return "dev.grcq.processing.Test";
    }
}
