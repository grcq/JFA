package dev.grcq.bytecode;

import dev.grcq.loader.JFALoader;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JFAClass {

    private static JFALoader loader;

    private final Class<?> clazz;
    private final String packageName;
    private final String className;

    private final List<JFAMethod> methods;
    private byte[] bytes = null;

    public JFAClass(Class<?> clazz) {
        this.clazz = clazz;

        this.packageName = clazz.getPackage().getName();
        this.className = clazz.getSimpleName();

        this.methods = new ArrayList<>();

        if (loader == null) loader = new JFALoader();

        for (Method method : clazz.getDeclaredMethods()) {
            JFAMethod jfaMethod = new JFAMethod(method.getName(), method.getReturnType(), JFAModifier.PUBLIC, JFAModifier.STATIC);
            for (int x = 0; x < method.getParameterCount(); x++) {
                jfaMethod.addParameter(method.getParameterTypes()[x], method.getParameters()[x].getName());
            }

            addMethod(jfaMethod);
        }
    }

    public JFAClass(String qualifiedName) {
        this(getClassByName(qualifiedName));
    }

    protected static Class<?> getClassByName(String qualifiedName) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(qualifiedName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clazz;
    }

    public void addMethod(JFAMethod method) {
        methods.add(method);
    }

    public void addMethods(JFAMethod... methods) {
        for (JFAMethod method : methods) {
            addMethod(method);
        }
    }

    public byte[] toBytes() {
        this.buildBytes();
        return bytes;
    }

    public Class<?> toClass() {
        return loader.define(packageName + "." + className, toBytes());
    }

    private void buildBytes() {
        StringBuilder str = new StringBuilder("package " + packageName + ";\n\npublic class " + className + "{\n");
        for (JFAMethod method : methods) {
            str.append("    ");
            for (JFAModifier modifier : method.getModifiers()) {
                str.append(modifier.toString())
                        .append(" ");
            }

            str.append(method.getReturnType().getName())
                    .append(" ")
                    .append(method.getName()).append("(");

            int i = 0;
            for (Map.Entry<String, Class<?>> entry : method.getParameters().entrySet()) {
                i++;

                str.append(entry.getValue().getName())
                        .append(" ")
                        .append(entry.getKey());
                if (i < method.getParameters().size()) str.append(", ");
            }

            str.append(") {\n");
            // TODO: Add code
            str.append("        \n}");
        }

        str.append("\n}");
        bytes = str.toString().getBytes();
    }

}
