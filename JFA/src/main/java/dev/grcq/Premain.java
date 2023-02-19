package dev.grcq;

import dev.grcq.bytecode.JFAClass;
import dev.grcq.processing.AnnotationByteCodeHandler;

import java.lang.annotation.Annotation;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import dev.grcq.utils.ProcessingUtils;

public class Premain {

    public static void premain(String args, Instrumentation inst) {
        System.out.println("a");
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                for (AnnotationByteCodeHandler<?> handler : ProcessingUtils.getAllByteCodeHandlers()) {
                    System.out.println("b");

                    Class<? extends Annotation> anno = null;
                    try {
                        anno = (Class<? extends Annotation>) Class.forName(handler.getFqdn());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return classfileBuffer;
                    }

                    System.out.println(anno);

                    if (classBeingRedefined.isAnnotationPresent(anno)) {
                        JFAClass clazz = new JFAClass(classBeingRedefined);

                        try {
                            Annotation annotation = anno.newInstance();
                            clazz = handler.process(annotation, clazz);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                        return clazz.toBytes();
                    }
                }
                return new byte[0];
            }
        });
    }

}
