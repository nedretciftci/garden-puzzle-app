package domain.gardenobjects;

import domain.Color;
import domain.Garden;
import domain.GardenSquare;
import java.util.ArrayList;
import java.util.List;

//Searchable interface implementation
public class LargeLamp extends LightSource{
    //Default constructor
    public LargeLamp(String id, Color color, int areaOfLightReach) {
        super(id, color, areaOfLightReach);
    }
    //Empty constructor
    public LargeLamp() {
        super();
    }
    //Copy constructor
    public LargeLamp(LargeLamp largeLamp) {
        super(largeLamp);
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
    //calculateLightReach method
    //LargeLamps light 3 or 4 squares to only their left side.
    @Override
    protected List<GardenSquare> calculateLightReach(Garden garden) {
        List<GardenSquare> lightReach = new ArrayList<>();
        GardenSquare currentSquare = garden.getGardenSquare(this);
        if (currentSquare == null) return lightReach;

        //Get the area of light reach of the large lamp
        int maxReach = getAreaOfLightReach();

        //LargeLamps light 3 or 4 squares to only their left side.
        for (int i = 1; i <= maxReach; i++) {
            //Left
            GardenSquare leftSquare = garden.getSquareByRelativePosition(currentSquare, 0, -i);
            //Add the left square to the list of squares that the light reaches
            if (leftSquare != null && !leftSquare.getIsBlocked()) {
                lightReach.add(leftSquare);
            } else {
                break; //Stop spreading left if a block is encountered
            }
        }

        //Return the list of garden squares that the light reaches
        return lightReach;
    }

    //toString method
    @Override
    public String toString() {
        return "LargeLamp - " + super.toString();
    }

}
