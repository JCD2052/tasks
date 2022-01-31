package email;

public enum BoxName {
    INBOX("INBOX");

    private final String boxName;

    BoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getBoxName() {
        return boxName;
    }
}
