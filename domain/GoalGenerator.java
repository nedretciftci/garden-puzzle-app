package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GoalGenerator {
    // For example, the puzzle game will randomly tell the gardener to make Tree and Flower Pollens
    //infused with red, blue, and green colors to reach square B5 in the Garden. Thus, the gardener will
    //place the appropriate 7 GardenObjects in the Garden to achieve the designated goal for square B5.
    private List<String> requiredPollens;
    private List<Color> requiredColors;
    private GardenSquare targetSquare;
    private Random random;

    public GoalGenerator(Garden garden) {
        targetSquare = getRandomSquare(garden);
        targetSquare.setTarget(true);
        requiredPollens = getRandomPollenType();
        requiredColors = getRandomColors();
        random = new Random();
    }

    public String printGoal() {
        // Randomly select required pollen types
        return "Goal: Make " + requiredPollens + " Pollen(s) with " + requiredColors + " color(s)";
    }

    //selects random not blocked square as target square
    private GardenSquare getRandomSquare(Garden garden) {
        GardenSquare square;
        
        int rowRandom = getRandomNumber(1, garden.getROWS());
        int columnRandom = getRandomNumber(1, garden.getCOLUMNS());

        do {
            square = garden.getSquareByCoordinate(rowRandom, columnRandom);
        } while (square.getIsBlocked());
        return square;
    }

    private List<String> getRandomPollenType() {
        String[] types = {"f", "t", "u"};
        List<List<String>> subsets = new ArrayList<>();
    
        // Generate subsets dynamically
        for (int i = 0; i < types.length; i++) {
            List<String> subset = new ArrayList<>();
            subset.add(types[i]); // Start with the current element
            subsets.add(new ArrayList<>(subset)); // Add the single-element subset
    
            for (int j = i + 1; j < types.length; j++) {
                subset.add(types[j]); // Add subsequent elements to build larger subsets
                subsets.add(new ArrayList<>(subset)); // Add the subset to the list
            }
        }
    
        // Randomly select one subset
        return subsets.get(new Random().nextInt(subsets.size()));
    }
    
    private List<Color> getRandomColors() {
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};
        List<List<Color>> subsets = new ArrayList<>();
    
        // Generate subsets dynamically
        for (int i = 0; i < colors.length; i++) {
            List<Color> subset = new ArrayList<>();
            subset.add(colors[i]); // Start with the current color
            subsets.add(new ArrayList<>(subset)); // Add the single-element subset
    
            for (int j = i + 1; j < colors.length; j++) {
                subset.add(colors[j]); // Add subsequent colors to build larger subsets
                subsets.add(new ArrayList<>(subset)); // Add the subset to the list
            }
        }
    
        // Randomly select one subset
        return subsets.get(new Random().nextInt(subsets.size()));
    }
    

    private int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public List<String> getRequiredPollens() {
        return requiredPollens;
    }

    public List<Color> getRequiredColors() {
        return requiredColors;
    }

    public GardenSquare getTargetSquare() {
        return targetSquare;
    }

}
