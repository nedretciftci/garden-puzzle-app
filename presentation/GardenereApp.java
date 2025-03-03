package presentation;

import data.FileIO;
import data.StorageShed;
import domain.Garden;
import domain.GoalGenerator;
import domain.SearchSystem;
import domain.gardenobjects.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GardenereApp {
    public static void main(String[] args) throws Exception {

        StorageShed shed = new StorageShed();
        FileIO file = new FileIO();
        file.readFile("src/storage_contents.csv", shed);

        System.out.println("Welcome to Garden Puzzle App.");

        Garden garden = new Garden();

        GoalGenerator goal = new GoalGenerator(garden);
        System.out.println(goal.printGoal());

        SearchSystem search = new SearchSystem(shed);
        List<GardenObject> selectedGardenObjects = search.startSearch();

        System.out.println("==> The gardener carries selected objects to the Garden.");
        System.out.println("********************************************************************");

        garden.displayGarden();
        System.out.println("==> Your chosen Garden Objects:");

        System.out.println(selectedGardenObjects.toString());

        gardenerActions(selectedGardenObjects, garden);

        System.out.println("==> The gardener starts waiting. All lights are lit up. All plants start\nblooming.");
        garden.waitAndTriggerActions();
        garden.displayGarden();

        System.out.println("==> Your chosen Garden Objects:");

        System.out.println(selectedGardenObjects.toString());

        Boolean pollensRequired = garden.getTargetSquare().getPollenCloud().getPollenSet() == goal.getRequiredPollens();
        Boolean colorsRequired = garden.getTargetSquare().getPollenCloud().getColorSet() == goal.getRequiredColors();

        if (pollensRequired && colorsRequired) {
            System.out.println("Congratulations! You have successfully completed the Garden Puzzle.");
        } else {
            System.out.println("Unfortunately, you have not completed the Garden Puzzle. Please try again.");
        }  
    }

    public static void gardenerActions(List<GardenObject> selectedGardenObjects, Garden garden) {
        // Create a copy of the original list to preserve the initial state
        List<GardenObject> availableGardenObjects = new ArrayList<>(selectedGardenObjects);
        Scanner scanner = new Scanner(System.in);

        while (!availableGardenObjects.isEmpty()) {
            // Display the available garden objects to place

            System.out.println("Enter the ID corresponding to the Garden Object you would like to place:");
            String id = scanner.nextLine();

            // Find the object by ID from availableGardenObjects
            GardenObject selectedObject = null;
            for (GardenObject obj : availableGardenObjects) {
                if (obj.getId().equals(id)) {
                    selectedObject = obj;
                    break;
                }
            }

            if (selectedObject == null) {
                System.out.println("Invalid ID. Please try again.");
                continue; // Retry without removing the object
            }

            System.out.println("Enter the location you would like to place the selected Garden Object (e.g., E5):");
            String location = scanner.nextLine();

            // Parse the location
            try {
                int row = location.toUpperCase().charAt(0) - 'A' + 1; // Convert letter to row index (e.g., A -> 1, B -> 2, ...)
                int column = Integer.parseInt(location.substring(1));

                // Validate the location
                if (row < 1 || row > garden.getROWS() || column < 1 || column > garden.getCOLUMNS()) {
                    System.out.println("Invalid location. Please try again.");
                    continue;
                }

                // Place the selected object in the garden
                if (garden.placeObject(selectedObject, row, column)) {
                    System.out.println("Object placed successfully.");
                    availableGardenObjects.remove(selectedObject); // Remove the placed object
                } else {
                    System.out.println("Failed to place the object. The location might be blocked or occupied.");
                }

            } catch (Exception e) {
                System.out.println("Invalid location format. Please use a letter followed by a number (e.g., E5).");
                continue;
            }

            // Ask the user if they want to place another object
            System.out.println("Would you like to place another object? ([1] Yes, [2] No):");
            String choice = scanner.nextLine();

            if (choice.equals("2")) {
                System.out.println("You chose to stop placing objects.");
                break;
            } else if (!choice.equals("1")) {
                System.out.println("Invalid input. Assuming 'Yes' by default.");
            }
        }

        System.out.println("==> You have run out of objects to place or stopped placing objects.");
    }
}