package reflection;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeValuePair {
    private String givenType, givenValue;
    private Type javaType;
    private Object javaValue;

    public TypeValuePair(String t, String v) {
        this.givenType = t;
        this.givenValue = v;
    }

    public String getGivenType() {
        return givenType;
    }

    public void setGivenType(String givenType) {
        this.givenType = givenType;
    }

    public String getGivenValue() {
        return givenValue;
    }

    public void setGivenValue(String givenValue) {
        this.givenValue = givenValue;
    }

    public Type getJavaType() {
        return javaType;
    }

    public void setJavaType(Type javaType) {
        this.javaType = javaType;
    }

    public Object getJavaValue() {
        return javaValue;
    }

    public void setJavaValue(Object javaValue) {
        this.javaValue = javaValue;
    }

    private static final Pattern ARRAY_MATCHER = Pattern.compile("([{ ])(.+?)([,}])");
    public static TypeValuePair parseTypeValuePair(String type, String val) {
        TypeValuePair tvp = new TypeValuePair(type, val);
        tvp.setJavaType(classFromString(type));
        System.out.println(type);
        System.out.println(val);

        if (type.endsWith("[]")) { // is an array
            // array code heavily based off this: https://docs.oracle.com/javase/tutorial/reflect/special/arrayInstance.html
            Matcher m = ARRAY_MATCHER.matcher(val);
            Type arrClass = classFromString(type.substring(0, type.length() - 2));
            ArrayList<String> elems = new ArrayList<>();
            while (m.find()) {
                System.out.println("found");
                System.out.println(m.group(0));
                elems.add(m.group(2));
            }
            System.out.println(elems);
            Object arr = Array.newInstance(arrClass.getType(), elems.size());
            for (int i = 0; i < elems.size(); i++) {
                Object o;
                try {
                    o = arrClass.getType().getConstructor(String.class).newInstance(elems.get(i));
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException("Unable to create an object of type " + arrClass +
                            " with the constructor parameters (as a String) " + elems.get(i), e);
                }
                Array.set(arr, i, o);
            }
            tvp.setJavaValue(arr);

        } else {
            // all the autoboxed primitive classes have a constructor that takes a string version.
            // for the others, hope and pray, ig
            try {
                Object o = tvp.getJavaType().getType().getConstructor(String.class).newInstance(val);
                tvp.setJavaValue(o);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException("Unable to create an object of type " + tvp.getJavaType() +
                        " (given the String " + tvp.getGivenType() +
                        ") with the constructor parameters (as a String) " + val, e);
            }
        }

        return tvp;
    }

    public static Type classFromString(String s) {
        if (s.equals("int")) return new Type(Integer.class, "Integer"); // TODO: need one for character
        if (s.equals("char")) return new Type(Character.class, "Character");

        // to account for primitives that have boxed classes
        String cappedReturnType = s.substring(0,1).toUpperCase() + s.substring(1);

        // arrays
        if (s.endsWith("[]")) {
            Type base = classFromString(s.substring(0, s.length() - 2));
            Object o = Array.newInstance(base.getType(), 0);
            return new Type(o.getClass(), cappedReturnType);
        }

        Type retClass;
        try {
            retClass = new Type(
                    Class.forName("java.lang." + cappedReturnType),
                    cappedReturnType);
        } catch (ClassNotFoundException e) {
            System.out.println(s);
            System.out.println("Not a class");
            throw new IllegalStateException(e);
        }
        return retClass;
    }

}
