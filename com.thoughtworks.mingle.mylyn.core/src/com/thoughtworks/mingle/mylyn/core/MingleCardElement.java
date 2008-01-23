package com.thoughtworks.mingle.mylyn.core;

public enum MingleCardElement {
    // Format: ENUM ( "pretty name", "xml key", <hidden: true/false>, <readonly:
    // true/false>)
    // Hidden elements are not automatically displayed in ui
    STATUS_WHITEBOARD("Name:", "name", true, false),
    ACTUAL_TIME("Description:", "description", true, false),
    ADD_COMMENT("Additional Comments:", "comment", true, false),
    ID("Id:", "id", true, false);

    private final boolean isHidden;

    private final boolean isReadOnly;

    private final String keyString;

    private final String prettyName;

    MingleCardElement(String prettyName, String fieldName) {
        this(prettyName, fieldName, false, false);
    }

    MingleCardElement(String prettyName, String fieldName, boolean hidden) {
        this(prettyName, fieldName, hidden, false);
    }

    MingleCardElement(String prettyName, String fieldName, boolean hidden, boolean readonly) {
        this.prettyName = prettyName;
        this.keyString = fieldName;
        this.isHidden = hidden;
        this.isReadOnly = readonly;
    }

    public String getKeyString() {
        return keyString;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public String toString() {
        return prettyName;
    }
}
