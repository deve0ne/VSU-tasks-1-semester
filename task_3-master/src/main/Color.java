package main;

public enum Color {
    WHITE("белое"),
    GRAY("серое"),
    ORANGE("оранжевое"),
    YELLOW("жёлтое"),
    GREEN("зелёное"),
    BLUE("синее");

    private final String localizedText;

    Color(String localizedText) {
        this.localizedText = localizedText;
    }

    public String getLocalizedText() {
        return localizedText;
    }
}
