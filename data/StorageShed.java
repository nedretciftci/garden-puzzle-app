package data;

import domain.gardenobjects.*;
import java.util.ArrayList;
import java.util.List;

public class StorageShed {
    private List<GardenObject> objects;

    public StorageShed() {
        this.objects = new ArrayList<>();
    }

    public void addObject(GardenObject obj) {
        objects.add(obj);
    }

    public List<GardenObject> getGardenObjects() {
        return objects;
    }

    public void printAllObjects() {
        for (GardenObject obj : objects) {
            System.out.println(obj);
        }
    }
}
