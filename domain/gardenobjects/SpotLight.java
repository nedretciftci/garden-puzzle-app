package domain.gardenobjects;

import domain.Color;
import domain.Garden;
import domain.GardenSquare;
import java.util.ArrayList;
import java.util.List;

public class SpotLight extends LightSource {
    // Default constructor
    public SpotLight(String id, Color color, int areaOfLightReach) {
        super(id, color, areaOfLightReach);
    }

    // Empty constructor
    public SpotLight() {
        super();
    }

    // Copy constructor
    public SpotLight(SpotLight spotLight) {
        super(spotLight);
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
    // Spotlights light 4 or 5 squares downward.
    @Override
    protected List<GardenSquare> calculateLightReach(Garden garden) {
        List<GardenSquare> lightReach = new ArrayList<>();
        GardenSquare currentSquare = garden.getGardenSquare(this);
        if (currentSquare == null) return lightReach;

        // Get the area of light reach of the spotlight
        int maxReach = getAreaOfLightReach();

        // Spotlights light downwards
        for (int i = 1; i <= maxReach; i++) {
            // Down
            GardenSquare downSquare = garden.getSquareByRelativePosition(currentSquare, i, 0);
            // Add the down square to the list of squares that the light reaches
            if (downSquare != null && !downSquare.getIsBlocked()) {
                lightReach.add(downSquare);
            } else {
                break; // Stop spreading downward if a block is encountered
            }
        }

        // Return the list of garden squares that the light reaches
        return lightReach;
    }

    // toString method
    @Override
    public String toString() {
        return "SpotLight - " + super.toString();
    }
}

