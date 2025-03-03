package domain;

public enum Color {
    RED, BLUE, GREEN;

    @Override
    public String toString() {
        switch (this) {
            case RED:
                return "r";
            case BLUE:
                return "b";
            case GREEN:
                return "g";
            default:
                return name();
        }
    }
    public static Color fromString(String colorString) {
        switch (colorString.toLowerCase()) {
            case "red":
                return RED;
            case "green":
                return GREEN;
            case "blue":
                return BLUE;
            default:
                throw new IllegalArgumentException("Unknown color: " + colorString);
        }
    }
}
