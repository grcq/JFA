package cf.grcq.bytecode;

public class JFAMethod {

    private final String name;
    private final Class<?> returnType;

    public JFAMethod(String name, Class<?> returnType) {
        this.name = name;
        this.returnType = returnType;
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
}
