package reflection;

public class Type {
    private Class<?> type;
    private String name;

    public Type(Class<?> t, String n) {
        type = t;
        name = n;
    }


    public Class<?> getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
