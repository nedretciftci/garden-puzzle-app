package domain;
import domain.gardenobjects.GardenObject;
import domain.gardenobjects.GardenPlant;
import domain.gardenobjects.LightSource;
import domain.gardenobjects.Statue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Garden {
    private static final int ROWS = 6; // Number of rows (A-F)
    private static final int COLUMNS = 8; // Number of columns (1-8)
    private List<GardenSquare> gardenSquares; // List to hold all garden squares
    private GardenSquare targetSquare;

    // Constructor to initialize the garden
    public Garden() {
        this.gardenSquares = new ArrayList<>();
        initializeSquares();
        //place 7 statues in the garden with loop
        initializeStatues();

    }

    private void initializeStatues(){
        Random rand = new Random();
        for (int i = 1; i <= 7; i++) {
            int row, column;
            do {
                row = rand.nextInt(ROWS) + 1;
                column = rand.nextInt(COLUMNS) + 1;
            } while (!placeObject(new Statue("S" + i), row, column)); // Ensure unique placement
        }
    }

    // Initializes all garden squares and assigns their coordinates
    private void initializeSquares() {
        for (int row = 1; row <= ROWS; row++) {
            for (int col = 1; col <= COLUMNS; col++) {
                gardenSquares.add(new GardenSquare(row, col));           
            }
        }
    }

    // Retrieves a GardenSquare by its coordinate (e.g., "A1", "B5")
    public GardenSquare getSquareByCoordinate(int row, int column) {
        for (GardenSquare square : gardenSquares) {
            if (square.getRow() == row && square.getColumn() == column) {
                return square;
            }
        }
        return null;
    }
    public GardenSquare getGardenSquare(GardenObject gardenObject) {
        for (GardenSquare square : gardenSquares) {
            if (square.getGardenObject() == gardenObject) {
                return square;
            }
        }
        return null;
    }
    
    public GardenSquare getSquareByRelativePosition(GardenSquare currentSquare, int rowOffset, int colOffset) {
        int newRow = currentSquare.getRow() + rowOffset;
        int newCol = currentSquare.getColumn() + colOffset;
        return getSquareByCoordinate(newRow, newCol);

    }
    
    // Places an object in a specific garden square
    public boolean placeObject(GardenObject object, int row, int column) {
        GardenSquare square = getSquareByCoordinate(row, column);
        if ((square == null) || (square.getIsBlocked())) return false;
        square.setGardenObject(object);
        return true;
    }

    // Prints a visual representation of the garden grid
    //with target inside
    public void displayGarden() {
        char rowLetter = 'A';
        int index = 0;
        for (int row = 1; row <= ROWS; row++) {
            System.out.print(rowLetter + " |");
            for (int col = 1; col <= COLUMNS; col++) {
                System.out.print(" " + gardenSquares.get(index).toString() + " |");
                index++;
            }
            System.out.println();
            rowLetter++;
        }
        System.out.println("      1        2        3        4        5        6        7        8"); // Column headers
    }

    // Getter for the garden squares
    public List<GardenSquare> getGardenSquares() {
        return gardenSquares;
    }

    public int getROWS() {
        return ROWS;
    }

    public int getCOLUMNS() {
        return COLUMNS;
    }

    public GardenSquare getTargetSquare() {
        return targetSquare;
    }

    public void waitAndTriggerActions() {
        System.out.println("The gardener starts waiting...");
        for (GardenSquare square : gardenSquares) {
            GardenObject obj = square.getGardenObject();
            if (obj instanceof LightSource lightSource) {
                lightSource.lightUp(this); // Trigger lightUp for LightSource
            } else if (obj instanceof GardenPlant gardenPlant) {
                gardenPlant.bloom(this); // Trigger bloom for GardenPlant
            }
        }
        System.out.println("All LightSources have lit up and all GardenPlants have bloomed!");
    }



}

