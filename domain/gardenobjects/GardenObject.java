package domain.gardenobjects;

public abstract class GardenObject {
    // Attribute to store the ID of the garden object
    private String id;

    // Default constructor
    public GardenObject() {
        this.id = "";
    }

    //Full constructor
    public GardenObject(String id) {
        this.id = id;
    }

    // Copy constructor
    public GardenObject(GardenObject gardenObject) {
        this.id = gardenObject.id;
    }

    // Getter for ID
    public String getId() {
        return id;
    }

    // Setter for ID
    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "ID: " + id;
    }

    public String displayInfo() {
        if (getId().length() == 3) {
            return ("  " + getId() + " ");
        }
        else return ("  " + getId() + "  ");
    }

}
