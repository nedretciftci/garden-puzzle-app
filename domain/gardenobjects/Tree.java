package domain.gardenobjects;

import domain.Garden;
import domain.GardenSquare;
import java.util.ArrayList;
import java.util.List;

public class Tree extends GardenPlant {
    //Default constructor
    public Tree() {
        super();
    }
    //Full constructor
    public Tree(String id, String name, int areaOfPollenSpread) {
        super(id, name, areaOfPollenSpread);
    }
    //Copy constructor
    public Tree(Tree tree) {
        super(tree);
    }

    // Shared bloom mechanism
    //* bloom(row, column) :Bush Pollens spread 1 or 2 squares vertically. The spread of Pollens happens across both sides of the plants. For example, a Bush with 1 square spread would result in both direct upper and lower squares of the Bush to have Pollens.
    @Override
    public void bloom(Garden garden) {
        List<GardenSquare> affectedSquares = calculateSpread(garden);
        for (GardenSquare square : affectedSquares) {
            if (square != null) {
                square.getPollenCloud().addPollen("t");
            }
        }
    }


    @Override
    protected List<GardenSquare> calculateSpread(Garden garden) {
        List<GardenSquare> affectedSquares = new ArrayList<>();
        GardenSquare currentSquare = garden.getGardenSquare(this); // Find the square containing this object
        if (currentSquare == null) return affectedSquares;
    
        int maxSpread = getAreaOfPollenSpread();
    
        //Tree Pollens spread 3, 4 or 5 squares horizontally.
        for (int i = 1; i <= maxSpread; i++) {
            // Left
            GardenSquare leftSquare = garden.getSquareByRelativePosition(currentSquare, 0, -i); // Left
            if (leftSquare != null && !leftSquare.getIsBlocked()) {
                affectedSquares.add(leftSquare);
            } else {
                break; // Stop spreading left if a block is encountered
            }
    
            // Right
            GardenSquare rightSquare = garden.getSquareByRelativePosition(currentSquare, 0, i); // Right
            if (rightSquare != null && !rightSquare.getIsBlocked()) {
                affectedSquares.add(rightSquare);
            } else {
                break; // Stop spreading right if a block is encountered
            }
        }
    
        return affectedSquares;
    }    

    @Override
    public String toString() {
        return "Tree - " + super.toString();
    }

    
}
