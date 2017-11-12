package gen;

import javafx.scene.Group;

public class Dungeon {
    private Cell[] cells;
    private Group group;

    public Dungeon(Group group, int cellCount) {
        this.group = group;
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
            cells[i] = new Cell();
            group.getChildren().add(cells[i].getRectangle());
        }
    }
}
