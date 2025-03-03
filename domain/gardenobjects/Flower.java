package domain.gardenobjects;

import domain.Garden;
import domain.GardenSquare;
import java.util.ArrayList;
import java.util.List;


public class Flower extends GardenPlant{
    //Default constructor
    public Flower() {
        super();
    }
    //Full constructor
    public Flower(String id, String name, int areaOfPollenSpread) {
        super(id, name, areaOfPollenSpread);
    }
    //Copy constructor
    public Flower(Flower flower) {
        super(flower);
    }

    // Shared bloom mechanism
    //* bloom(row, column) :Bush Pollens spread 1 or 2 squares vertically. The spread of Pollens happens across both sides of the plants. For example, a Bush with 1 square spread would result in both direct upper and lower squares of the Bush to have Pollens.
    @Override
    public void bloom(Garden garden) {
        List<GardenSquare> affectedSquares = calculateSpread(garden);
        for (GardenSquare square : affectedSquares) {
            if (square != null) {
                square.getPollenCloud().addPollen("f");
            }
        }
    }


    @Override
    protected List<GardenSquare> calculateSpread(Garden garden) {
        List<GardenSquare> affectedSquares = new ArrayList<>();
        GardenSquare currentSquare = garden.getGardenSquare(this); // Find the square containing this object
        if (currentSquare == null) return affectedSquares;
    
        int maxSpread = getAreaOfPollenSpread();
    
        // Flower Pollens spread 2, 3 or 4 squares diagonally.
        for (int i = 1; i <= maxSpread; i++) {
            // Up-Left
            GardenSquare upLeftSquare = garden.getSquareByRelativePosition(currentSquare, -i, -i);
            if (upLeftSquare != null && !upLeftSquare.getIsBlocked()) {
                affectedSquares.add(upLeftSquare);
            } else {
                break; // Stop spreading if a block is encountered
            }
    
            // Up-Right
            GardenSquare upRightSquare = garden.getSquareByRelativePosition(currentSquare, -i, i);
            if (upRightSquare != null && !upRightSquare.getIsBlocked()) {
                affectedSquares.add(upRightSquare);
            } else {
                break; // Stop spreading if a block is encountered
            }
    
            // Down-Left
            GardenSquare downLeftSquare = garden.getSquareByRelativePosition(currentSquare, i, -i);
            if (downLeftSquare != null && !downLeftSquare.getIsBlocked()) {
                affectedSquares.add(downLeftSquare);
            } else {
                break; // Stop spreading if a block is encountered
            }
    
            // Down-Right
            GardenSquare downRightSquare = garden.getSquareByRelativePosition(currentSquare, i, i);
            if (downRightSquare != null && !downRightSquare.getIsBlocked()) {
                affectedSquares.add(downRightSquare);
            } else {
                break; // Stop spreading if a block is encountered
            }
        }
    
        return affectedSquares;
    }    

}