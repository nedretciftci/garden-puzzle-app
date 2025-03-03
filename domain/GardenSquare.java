package domain;

import domain.gardenobjects.GardenObject;

public class GardenSquare {
    private int row; // Pollen contents of the square
    private int column;
    private GardenObject object; // The object currently in the square
    private PollenCloud pollenCloud; // Pollen contents of the square
    private Boolean isBlocked; // Whether the square is blocked
    private Boolean isTarget; // Whether the square is the target

    // Default constructor
    public GardenSquare() {
        this.row = 0;
        this.column = 0;
        this.object = null;
        this.pollenCloud = new PollenCloud();
        this.isBlocked = false;
        this.isTarget = false;
    }
    // Full constructor
    public GardenSquare(int row, int column) {
        this.row = row;
        this.column = column;
        this.object = null;
        this.pollenCloud = new PollenCloud();
        this.isBlocked = false;
        this.isTarget = false;
    }
    // Copy constructor
    public GardenSquare(GardenSquare square) {
        this.row = square.row;
        this.column = square.column;
        this.object = square.object;
        this.pollenCloud = square.pollenCloud;
        this.isBlocked = square.isBlocked;
        this.isTarget = square.isTarget;
    }

    // Getter for the row
    public int getRow() {
        return row;
    }
    // Getter for the column
    public int getColumn() {
        return column;
    }
    //Getter for the garden object
    public GardenObject getGardenObject() {
        return object;
    }
    //Getter for the pollen cloud
    public PollenCloud getPollenCloud() {
        return pollenCloud;
    }
    //Getter for the isBlocked attribute
    public Boolean getIsBlocked() {
        return isBlocked;
    }
    //Setter for the isBlocked attribute
    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
    //Setter for the garden object
    public void setGardenObject(GardenObject object) {
        this.object = object;
        this.setIsBlocked(true);
    }
    //Setter for the pollen cloud
    public void setPollenCloud(PollenCloud pollenCloud) {
        this.pollenCloud = pollenCloud;
    }
    //Setter for the row
    public void setRow(int row) {
        this.row = row;
    }
    //Setter for the column
    public void setColumn(int column) {
        this.column = column;
    }

    public void setTarget(Boolean isTarget) {
        this.isTarget = isTarget;
    }

    public Boolean getTarget() {
        return isTarget;
    }


    @Override //TEKRAR BAKILCAK
    public String toString() {
        if (object != null) {
            return object.displayInfo();
        } else if (!pollenCloud.isEmpty()) {
            return pollenCloud.toString();
        } else if (getTarget()){
            return "Target";
        }
        else {
            return "      ";
        }
    }
}