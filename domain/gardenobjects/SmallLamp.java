package domain.gardenobjects;

import domain.Color;
import domain.Garden;
import domain.GardenSquare;
import java.util.ArrayList;
import java.util.List;

public class SmallLamp extends LightSource{
    // Default constructor
    public SmallLamp(String id, Color color, int areaOfLightReach) {
        super(id, color, areaOfLightReach);
    }

    // Empty constructor
    public SmallLamp() {
        super();
    }

    // Copy constructor
    public SmallLamp(SmallLamp smallLamp) {
        super(smallLamp);
    }

    @Override
    public void lightUp(Garden garden) {
        List<GardenSquare> affectedSquares = calculateLightReach(garden);
        for (GardenSquare square : affectedSquares) {
            if (square != null) {
                square.getPollenCloud().addColor(this.getColor()); // Accessing `getColor()` from LightSource
            }
        }
    }

    // calculateLightReach method
    @Override
    protected List<GardenSquare> calculateLightReach(Garden garden) {
        List<GardenSquare> lightReach = new ArrayList<>();
        GardenSquare currentSquare = garden.getGardenSquare(this);
        if (currentSquare == null) return lightReach;

        // Get the area of light reach of the small lamp
        int maxReach = getAreaOfLightReach();

        // SmallLamps light to their right side.
        for (int i = 1; i <= maxReach; i++) {
            // Right
            GardenSquare rightSquare = garden.getSquareByRelativePosition(currentSquare, 0, i);
            // Add the right square to the list of squares that the light reaches
            if (rightSquare != null && !rightSquare.getIsBlocked()) {
                lightReach.add(rightSquare);
            } else {
                break; // Stop spreading right if a block is encountered
            }
        }

        // Return the list of garden squares that the light reaches
        return lightReach;
    }

    // toString method
    @Override
    public String toString() {
        return "SmallLamp - " + super.toString();
    }

}

