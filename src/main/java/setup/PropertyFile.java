package setup;

/**
 * Stores paths to property files for testing native, web and hybrid applications.
 */

public enum PropertyFile {

    NATIVE("src\\nativetests.properties"),
    WEB("src\\webtest.properties"),
    HYBRID("src\\webtest.properties");

    private String propertyPath;

    PropertyFile(String path) {
        this.propertyPath = path;
    }

    @Override
    public String toString() {
        return propertyPath;
    }
}
