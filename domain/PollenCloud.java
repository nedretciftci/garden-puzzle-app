package domain;
import java.util.HashSet;

public class PollenCloud {
    private HashSet<String> pollenSet; // Ensures no duplicate pollen types
    private HashSet<Color> colorSet;  // Ensures no duplicate colors

    public PollenCloud() {
        this.pollenSet = new HashSet<>();
        this.colorSet = new HashSet<>();
    }

    public void addPollen(String pollen) {
        this.pollenSet.add(pollen);
    }

    public void addColor(Color color) {
        this.colorSet.add(color);
    }

    public HashSet<String> getPollenSet() {
        return this.pollenSet;
    }

    public HashSet<Color> getColorSet() {
        return this.colorSet;
    }
    @Override
    public String toString() {
        String pollens = "ftu"; // Predefined pollen order
        StringBuilder pollenString = new StringBuilder();
    
        // Format the pollenSet
        for (char pollen : pollens.toCharArray()) {
            if (pollenSet.contains(String.valueOf(pollen))) {
                pollenString.append(pollen); // Append the pollen if it exists in the set
            } else {
                pollenString.append(" "); // Append '*' for missing pollens
            }
        }
    
        // Predefined color order for RGB
        Color[] colorOrder = {Color.RED, Color.GREEN, Color.BLUE};
        StringBuilder colorString = new StringBuilder();
    
        // Format the colorSet
        for (Color color : colorOrder) {
            if (colorSet.contains(color)) {
                colorString.append(color.toString()); // Use the custom toString() of Color
            } else {
                colorString.append(" ");
            }
        }
    
        // Return the formatted string with a newline between the two sets
        return pollenString.toString() + colorString.toString();
    }

    public Boolean isEmpty(){
        return pollenSet.isEmpty() && colorSet.isEmpty();
    }
    
}


