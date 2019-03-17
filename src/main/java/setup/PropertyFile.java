package setup;

public enum PropertyFile {

    NATIVE("nativetests"),
    WEB("webtest"),
    HYBRID("hybridtest");

    private String appType;

    PropertyFile(String appType) {
        this.appType = appType;
    }

    @Override
    public String toString() {
        return appType;
    }

    public String getPropertyFileName(){ //TODO not
        return "src\\" + appType + ".properties";
    }
}
