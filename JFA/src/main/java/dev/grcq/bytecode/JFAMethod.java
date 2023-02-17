package dev.grcq.bytecode;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JFAMethod {

    private final String name;
    private final Class<?> returnType;
    private final List<JFAModifier> modifiers;
    private final Map<String, Class<?>> parameters;

    public JFAMethod(String name, Class<?> returnType, JFAModifier... modifiers) {
        this.name = name;
        this.returnType = returnType;
        this.modifiers = Lists.newArrayList(modifiers);
        this.parameters = new HashMap<>();
    }

    public void addParameter(Class<?> clazz, String name) {
        parameters.put(name, clazz);
    }

    public void addParameter(String fqn, String name) {
        parameters.put(name, JFAClass.getClassByName(fqn));
    }

    public void removeParameter(String name) {
        parameters.remove(name);
    }

    public void removeParameter(Class<?> clazz) {
        for (Map.Entry<String, Class<?>> entry : parameters.entrySet()) {
            if (entry.getValue() == clazz) {
                parameters.remove(entry.getKey());
                break;
            }
        }
    }

    public JFAMethod(String name) {
        this(name, void.class);
    }

    public String getName() {
        return name;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public List<JFAModifier> getModifiers() {
        return modifiers;
    }

    public void addModifiers(JFAModifier... modifiers) {
        this.modifiers.addAll(Arrays.asList(modifiers));
    }

    public Map<String, Class<?>> getParameters() {
        return parameters;
    }
}
