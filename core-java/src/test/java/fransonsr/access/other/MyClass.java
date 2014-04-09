package fransonsr.access.other;

public class MyClass {

    private final String privateValue = "privateValue";
    private final String defaultValue = "defaultValue";
    private final String protectedValue = "protectedValue";
    private final String publicValue = "publicValue";

    @SuppressWarnings("unused")
    private String getPrivateValue() {
        return privateValue;
    }

    String getDefaultValue() {
        return defaultValue;
    }

    protected String getProtectedValue() {
        return protectedValue;
    }

    public String getPublicValue() {
        return publicValue;
    }


}
