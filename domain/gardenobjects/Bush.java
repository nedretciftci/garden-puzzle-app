package domain.gardenobjects;

import domain.Garden;
import domain.GardenSquare;
import java.util.ArrayList;
import java.util.List;


public class Bush extends GardenPlant{
    //Default constructor
    public Bush() {
        super();
    }
    //Full constructor
    public Bush(String id, String name, int areaOfPollenSpread) {
        super(id, name, areaOfPollenSpread);
    }
    //Copy constructor
    public Bush(Bush bush) {
        super(bush);
    }

    // Shared bloom mechanism
    //* bloom(row, column) :Bush Pollens spread 1 or 2 squares vertically. The spread of Pollens happens across both sides of the plants. For example, a Bush with 1 square spread would result in both direct upper and lower squares of the Bush to have Pollens.
    @Override
    public void bloom(Garden garden) {
        List<GardenSquare> affectedSquares = calculateSpread(garden);
        for (GardenSquare square : affectedSquares) {
            if (square != null) {
                square.getPollenCloud().addPollen("u");
            }
        }
    }

    @Override
    protected List<GardenSquare> calculateSpread(Garden garden) {
        List<GardenSquare> affectedSquares = new ArrayList<>();
        GardenSquare currentSquare = garden.getGardenSquare(this); // Find the square containing this object
        if (currentSquare == null) return affectedSquares;
    
        int maxSpread = getAreaOfPollenSpread();
    
        // Spread vertically (up and down)
        for (int i = 1; i <= maxSpread; i++) {
            // Up
            GardenSquare upSquare = garden.getSquareByRelativePosition(currentSquare, -i, 0); // Up
            if (upSquare != null && !upSquare.getIsBlocked()) {
                affectedSquares.add(upSquare);
            } else {
                break; // Stop spreading upward if a block is encountered
            }
    
            // Down
            GardenSquare downSquare = garden.getSquareByRelativePosition(currentSquare, i, 0); // Down
            if (downSquare != null && !downSquare.getIsBlocked()) {
                affectedSquares.add(downSquare);
            } else {
                break; // Stop spreading downward if a block is encountered
            }
        }
    
        return affectedSquares;
    }    

    @Override
    public String toString() {
        return "Bush - " + super.toString();
    }

}