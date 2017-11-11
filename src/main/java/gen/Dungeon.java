package gen;

import javafx.scene.Group;

import java.util.Random;

public class Dungeon {
    private Cell[] cells;
    private Random random;
    private Group group;

    public Dungeon(Group group, int cellCount) {
        this.group = group;
        random = new Random();
        cells = new Cell[cellCount];
    }

    /**
     * This starts the dungeon generation.
     */
    public void generateDungeon() {
        generateCells();
    }

    /**
     * Generates the cells.
     */
    private void generateCells() {
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell( getRandomDouble(400), getRandomDouble(400),  32 + getRandomDouble(320), 32 + getRandomDouble(320));
            group.getChildren().add(cells[i].getRectangle());
        }
    }

    public double getRandomDouble(int bound) {
        double randomDouble = (double)random.nextInt(bound);
        return randomDouble - randomDouble % 16;
    }

    public Cell[] getCells() {
        return cells;
    }
}
