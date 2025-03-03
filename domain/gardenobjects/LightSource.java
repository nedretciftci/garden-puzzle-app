package domain.gardenobjects;

import domain.Color;
import domain.Garden;
import domain.GardenSquare;
import domain.Searchable;
import java.util.ArrayList;
import java.util.List;

public abstract class LightSource extends GardenObject implements Searchable {
    private Color color;  // Use shared Color enum
    private int areaOfLightReach;

    public LightSource() {
        super();
        this.color = null;
        this.areaOfLightReach = 0;
    }

    public LightSource(String id, Color color, int areaOfLightReach) {
        super(id);
        this.color = color;
        this.areaOfLightReach = areaOfLightReach;
    }

    public LightSource(LightSource lightSource) {
        super(lightSource);
        this.color = lightSource.color;
        this.areaOfLightReach = lightSource.areaOfLightReach;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getAreaOfLightReach() {
        return this.areaOfLightReach;
    }

    public void setAreaOfLightReach(int areaOfLightReach) {
        this.areaOfLightReach = areaOfLightReach;
    }

    public abstract void lightUp(Garden garden);

    protected abstract List<GardenSquare> calculateLightReach(Garden garden);

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
    public List<GardenObject> searchByArea(int area) {
        List<GardenObject> result = new ArrayList<>();
        if (this.areaOfLightReach == area) {
            result.add(this);
        }
        return result;
    }

    @Override
    public List<GardenObject> searchByColor(Color color) {
        List<GardenObject> result = new ArrayList<>();
        if (this.color == color) {
            result.add(this);
        }
        return result;
    }

    @Override
    public List<GardenObject> searchByName(String name) {
        // LightSource cannot be searched by name; return an empty list.
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Type:" + getClass() + super.toString() + ", Color: " + color + ", Area of Pollen Spread: " + areaOfLightReach;
    }
}


