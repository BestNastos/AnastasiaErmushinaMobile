package setup;

/**
 * Stores paths to property files for testing native, web and hybrid applications.
 * This class is NOT used in this project because it is not needed when running
 * tests via xml configuration files.
 */

public enum PropertyFile {

    NATIVE("src\\nativetests.properties"),
    WEB("src\\webtest.properties");

    private String propertyPath;

    PropertyFile(String path) {
        this.propertyPath = path;
    }

    @Override
    public String toString() {
        return propertyPath;
    }
}
