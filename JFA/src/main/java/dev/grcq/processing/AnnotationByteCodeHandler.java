package dev.grcq.processing;

import dev.grcq.bytecode.JFAClass;

import java.lang.annotation.Annotation;

public abstract class AnnotationByteCodeHandler<T> {

    abstract public JFAClass process(Annotation anno, JFAClass jfaClass);

    public abstract String getFqdn();

}
