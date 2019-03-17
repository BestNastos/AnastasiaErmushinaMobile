package setup;

public enum PropertyFile {

    NATIVE("src\\nativetests.properties"),
    WEB("src\\webtest.properties"),
    HYBRID("src\\webtest.properties"); //for the future

    private String propertyPath;

    PropertyFile(String appType) {
        this.propertyPath = appType;
    }

    @Override
    public String toString() {
        return propertyPath;
    }
}
