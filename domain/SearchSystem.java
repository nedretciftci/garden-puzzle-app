package domain;
import data.StorageShed;
import domain.gardenobjects.GardenObject;
import domain.gardenobjects.GardenPlant;
import domain.gardenobjects.LightSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchSystem {
    private StorageShed storageShed; // Storage shed holding garden objects
    private Scanner scanner;

    public SearchSystem(StorageShed storageShed) {
        this.storageShed = storageShed;
        this.scanner = new Scanner(System.in);
    }

    public List<GardenObject> startSearch() {
        List<GardenObject> selectedObjects = new ArrayList<>();
        int remainingSelections = 7; // User can take up to 7 objects
        System.out.println("==> Please search for Garden Objects from the Storage Shed. You can take up to " + remainingSelections + " object(s).");
        while (remainingSelections > 0) {
            System.out.println("Please select the type of object ([1] Plant, [2] Light): ");
            int objectType = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (objectType == 1) {
                searchPlants();
                System.out.println("--> Please enter the id of the Garden Plant you would like to take: ");
                String id = scanner.nextLine().toUpperCase();
                //add the selected object to result list by id
                for (GardenObject obj : storageShed.getGardenObjects()) {
                    if (obj instanceof GardenPlant && obj.getId().equals(id)) {
                        selectedObjects.add(obj);
                        break;
                    }
                }
            } else if (objectType == 2) {
                searchLights();
                System.out.println("--> Please enter the id of the Light Source you would like to take: ");
                String id = scanner.nextLine().toUpperCase();
                //add the selected object to result list by id
                for (GardenObject obj : storageShed.getGardenObjects()) {
                    if (obj instanceof LightSource && obj.getId().equals(id)) {
                        selectedObjects.add(obj);
                        break;
                    }
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            remainingSelections--;

            System.out.println("You have taken " + (selectedObjects.size()) +". Would you like to select another one? ([1] Yes, [2] No): ");
            int repeat = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (repeat != 1) {
                break;
            }
        }

        System.out.println("You have completed your selection.");
        return selectedObjects;
    }

    private void searchPlants() {
        System.out.println("Please select search filter for Plants ([1] Plant type, [2] Plant id, [3] Name, [4] Area of Spread): ");
        int filter = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (filter) {
            case 1 -> {
                System.out.println("Please enter a plant type (e.g., Bush, Tree): ");
                String type = scanner.nextLine();
                displayResults(searchPlantsByType(type));
            }
            case 2 -> {
                System.out.println("Please enter the plant ID: ");
                String id = scanner.nextLine().toUpperCase();
                displayResults(searchPlantsById(id));
            }
            case 3 -> {
                System.out.println("Please enter the plant name: ");
                String name = scanner.nextLine();
                displayResults(searchPlantsByName(name));
            }
            case 4 -> {
                System.out.println("Please enter a plant area of spread value: ");
                int area = scanner.nextInt();
                scanner.nextLine();
                displayResults(searchPlantsByArea(area));
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    private void searchLights() {
        System.out.println("Please select search filter for Light Sources ([1] Light type, [2] Light id, [3] Color, [4] Area of reach): ");
        int filter = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (filter) {
            case 1 -> {
                System.out.println("Please enter a light type (e.g., Small Lamp, Large Lamp): ");
                String type = scanner.nextLine();
                displayResults(searchLightsByType(type));
            }
            case 2 -> {
                System.out.println("Please enter the light ID: ");
                String id = scanner.nextLine().toUpperCase();
                displayResults(searchLightsById(id));
            }
            case 3 -> {
                System.out.println("Please enter the light color (e.g., RED, BLUE): ");
                String colorName = scanner.nextLine();
                Color color = Color.valueOf(colorName.toUpperCase());
                displayResults(searchLightsByColor(color));
            }
            case 4 -> {
                System.out.println("Please enter a light area of reach value between 1 and 5: ");
                int area = scanner.nextInt();
                scanner.nextLine();
                displayResults(searchLightsByArea(area));
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    private List<GardenObject> searchPlantsByType(String type) {
        List<GardenObject> results = new ArrayList<>();
        for (GardenObject obj : storageShed.getGardenObjects()) {
            if (obj instanceof GardenPlant) {
                results.addAll(((GardenPlant) obj).searchByType(type));
            }
        }
        return results;
    }

    private List<GardenObject> searchPlantsById(String id) {
        List<GardenObject> results = new ArrayList<>();
        for (GardenObject obj : storageShed.getGardenObjects()) {
            if (obj instanceof GardenPlant) {
                results.addAll(((GardenPlant) obj).searchById(id));
            }
        }
        return results;
    }

    private List<GardenObject> searchPlantsByName(String name) {
        List<GardenObject> results = new ArrayList<>();
        for (GardenObject obj : storageShed.getGardenObjects()) {
            if (obj instanceof GardenPlant) {
                results.addAll(((GardenPlant) obj).searchByName(name));
            }
        }
        return results;
    }

    private List<GardenObject> searchPlantsByArea(int area) {
        List<GardenObject> results = new ArrayList<>();
        for (GardenObject obj : storageShed.getGardenObjects()) {
            if (obj instanceof GardenPlant) {
                results.addAll(((GardenPlant) obj).searchByArea(area));
            }
        }
        return results;
    }

    private List<GardenObject> searchLightsByType(String type) {
        List<GardenObject> results = new ArrayList<>();
        for (GardenObject obj : storageShed.getGardenObjects()) {
            if (obj instanceof LightSource) {
                results.addAll(((LightSource) obj).searchByType(type));
            }
        }
        return results;
    }

    private List<GardenObject> searchLightsById(String id) {
        List<GardenObject> results = new ArrayList<>();
        for (GardenObject obj : storageShed.getGardenObjects()) {
            if (obj instanceof LightSource) {
                results.addAll(((LightSource) obj).searchById(id));
            }
        }
        return results;
    }

    private List<GardenObject> searchLightsByColor(Color color) {
        List<GardenObject> results = new ArrayList<>();
        for (GardenObject obj : storageShed.getGardenObjects()) {
            if (obj instanceof LightSource) {
                results.addAll(((LightSource) obj).searchByColor(color));
            }
        }
        return results;
    }

    private List<GardenObject> searchLightsByArea(int area) {
        List<GardenObject> results = new ArrayList<>();
        for (GardenObject obj : storageShed.getGardenObjects()) {
            if (obj instanceof LightSource) {
                results.addAll(((LightSource) obj).searchByArea(area));
            }
        }
        return results;
    }

    private void displayResults(List<GardenObject> results) {
        if (results.isEmpty()) {
            System.out.println("No matching objects found.");
        } else {
            System.out.println("The objects that fit the given criteria:");
            for (GardenObject obj : results) {
                System.out.println(obj.toString());
            }
        }
    }
}
