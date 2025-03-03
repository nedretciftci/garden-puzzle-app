package data;

import domain.Color;
import domain.gardenobjects.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FileIO {
    public static void readFile(String filePath, StorageShed storageShed) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0].trim();
                String id = parts[1].trim();
                String thirdAttribute = parts[2].trim();
                int areaOfReach = Integer.parseInt(parts[3].trim());

                if (type.equalsIgnoreCase("flower")) {
                    Flower flower = new Flower(id, thirdAttribute, areaOfReach);
                    storageShed.addObject(flower);
                } else if (type.equalsIgnoreCase("tree")) {
                    Tree tree = new Tree(id, thirdAttribute, areaOfReach);
                    storageShed.addObject(tree);
                } else if (type.equalsIgnoreCase("bush")) {
                    Bush bush = new Bush(id, thirdAttribute, areaOfReach);
                    storageShed.addObject(bush);
                } else if (type.equalsIgnoreCase("small_lamp")) { // Updated check
                    SmallLamp smallLamp = new SmallLamp(id, Color.fromString(thirdAttribute), areaOfReach);
                    storageShed.addObject(smallLamp);
                } else if (type.equalsIgnoreCase("large_lamp")) { // Updated check
                    LargeLamp largeLamp = new LargeLamp(id, Color.fromString(thirdAttribute), areaOfReach);
                    storageShed.addObject(largeLamp);
                } else if (type.equalsIgnoreCase("spotlight")) {
                    SpotLight spotLight = new SpotLight(id, Color.fromString(thirdAttribute), areaOfReach);
                    storageShed.addObject(spotLight);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
