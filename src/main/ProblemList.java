package main;

/**
 * Note: This should match the format of the resources folder.
 */
public enum ProblemList {

    ARRAY_LEVEL_1("Array 1", new String[]{
            "firstElementBoolean.txt",
            "firstElementDouble.txt",
            "firstElementInt.txt",
            "firstElementStrings.txt",
            "lastElementBoolean.txt",
            "lastElementDouble.txt",
            "lastElementInt.txt",
            "lastElementStrings.txt",
            "replaceFirst.txt"
    }),
    ARRAY_LEVEL_2("Array 2", new String[]{
            "replaceAll.txt"
    });


    public final String[] problemFiles;
    public final String name;

    private ProblemList(String name, String[] files) {
        this.name = name;
        problemFiles = files;
    }

    public String getPath() {
        String sep = "/";System.getProperty("file.separator");
        return switch (this) { // TODO: write a method for assembling file paths
            case ARRAY_LEVEL_1 -> "problems" + sep + "array" + sep + "level1";
            case ARRAY_LEVEL_2 -> "resources" + sep + "problems" + sep + "array" + sep + "level2";
            default -> "";
        };
    }

    public String getPath(String fileName) {
        String sep = "/";//System.getProperty("file.separator");
        return this.getPath() + sep + fileName;
    }

}
