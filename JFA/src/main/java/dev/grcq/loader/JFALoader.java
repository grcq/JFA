package dev.grcq.loader;

public class JFALoader extends ClassLoader {

    public Class<?> define(String clazz, byte[] bytes) {
        return defineClass(clazz, bytes, 0, bytes.length);
    }

}
