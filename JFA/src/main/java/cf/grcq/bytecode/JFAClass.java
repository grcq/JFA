package cf.grcq.bytecode;

import cf.grcq.loader.JFALoader;

import java.util.ArrayList;
import java.util.List;

public class JFAClass {

    private static JFALoader loader;

    private final Class<?> clazz;
    private final String packageName;
    private final String className;

    private final List<JFAMethod> methods;

    public JFAClass(Class<?> clazz) {
        this.clazz = clazz;

        this.packageName = clazz.getPackageName();
        this.className = clazz.getSimpleName();

        this.methods = new ArrayList<>();

        if (loader == null) loader = new JFALoader();
    }

    public JFAClass(String qualifiedName) {
        this(getClassByName(qualifiedName));
    }

    private static Class<?> getClassByName(String qualifiedName) {
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
        return new byte[0];
    }

    public Class<?> toClass() {
        return loader.define(packageName + "." + className, toBytes());
    }

}
