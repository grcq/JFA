package dev.grcq;

import dev.grcq.bytecode.JFAClass;
import dev.grcq.bytecode.JFAMethod;
import dev.grcq.bytecode.JFAModifier;

public class Main {

    // For testing purposes
    public static void main(String[] args) {
        JFAClass jfaClass =new JFAClass(Main.class);

        JFAMethod jfaMethod = new JFAMethod("myNewMethod", void.class, JFAModifier.PRIVATE, JFAModifier.FINAL);
        jfaMethod.addParameter(String.class, "string");

        jfaClass.addMethod(jfaMethod);

        byte[] bytes = jfaClass.toBytes();
        System.out.println(new String(bytes));
    }

    public static void test(Main main, String test) {

    }

}
