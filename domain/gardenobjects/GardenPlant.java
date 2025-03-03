package domain.gardenobjects;

import domain.Color;
import domain.Garden;
import domain.GardenSquare;
import domain.Searchable;
import java.util.ArrayList;
import java.util.List;

public abstract class GardenPlant extends GardenObject implements Searchable {
    private String name;
    private int areaOfPollenSpread;

    //Default constructor
    public GardenPlant() {
        super();
        this.name = "";
        this.areaOfPollenSpread = 0;
    }
    //Full constructor
    public GardenPlant(String id, String name, int areaOfPollenSpread) {
        super(id);
        this.name = name;
        this.areaOfPollenSpread = areaOfPollenSpread;
    }
    //Copy constructor
    public GardenPlant(GardenPlant gardenPlant) {
        super(gardenPlant);
        this.name = gardenPlant.name;
        this.areaOfPollenSpread = gardenPlant.areaOfPollenSpread;
    }

    public String getName() {
        return name;
    }

    public int getAreaOfPollenSpread() {
        return areaOfPollenSpread;
    }

    public String getPlantType() {
        return this.getClass().getName();
    }

    // Shared bloom mechanism
    public abstract void bloom(Garden garden);

    // Abstract method for subclasses to define their spread logic
    protected abstract List<GardenSquare> calculateSpread(Garden garden);

    @Override
    public String toString() {
        return "Type:" + getClass() + super.toString() + ", Name: " + name + ", Area of Pollen Spread: " + areaOfPollenSpread;
    }

    @Override
    public List<GardenObject> searchByType(String type) {
        List<GardenObject> result = new ArrayList<>();
        if (this.getClass().getSimpleName().equalsIgnoreCase(type)) {
            result.add(this);
        }
        return result;
    }

    @Override
    public List<GardenObject> searchById(String id) {
        List<GardenObject> result = new ArrayList<>();
        if (this.getId().equalsIgnoreCase(id)) {
            result.add(this);
        }
        return result;
    }

    @Override
    public List<GardenObject> searchByName(String name) {
        List<GardenObject> result = new ArrayList<>();
        if (this.name.equalsIgnoreCase(name)) {
            result.add(this);
        }
        return result;
    }

    @Override
    public List<GardenObject> searchByArea(int area) {
        List<GardenObject> result = new ArrayList<>();
        if (this.areaOfPollenSpread == area) {
            result.add(this);
        }
        return result;
    }

    @Override
    public List<GardenObject> searchByColor(Color color) {
        return new ArrayList<>(); // Not applicable for GardenPlants
    }


}
