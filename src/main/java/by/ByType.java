package by;

public enum ByType {

    XPATH("xpath"),
    ID("id"),
    NAME("name");

    private final String TYPE_NAME;

    ByType(String typeName) {
        this.TYPE_NAME = typeName;
    }

    public String getTypeName() {
        return TYPE_NAME;
    }

}
