package domain;
import java.util.List;

import domain.gardenobjects.GardenObject;

public interface Searchable {
    // Method to search by type (e.g., "Flower", "Lamp")
    List<GardenObject> searchByType(String type);
    
    // Method to search by ID (e.g., "P17", "L1")
    List<GardenObject> searchById(String id);
    
    // Method to search by name (e.g., "Rose", "L1")
    List<GardenObject> searchByName(String name); //only for gardenPlant objects
    
    // Method to search by area (e.g., pollen spread or light reach)
    List<GardenObject> searchByArea(int area);

    List<GardenObject> searchByColor(Color color);
}
