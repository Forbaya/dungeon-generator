package gen;

import javafx.scene.Group;
import utils.ArrayList;
import utils.Utils;

/**
 * The dungeon itself.
 */
public class Dungeon {
    private ArrayList<Cell> cells;
    private Group group;
    private int cellCount;

    public Dungeon(Group group, int cellCount) {
        this.group = group;
        this.cellCount = cellCount;
        cells = new ArrayList<>();
    }

    /**
     * This starts the dungeon generation.
     */
    public void generateDungeon() {
        generateCells();
        separateCells();
    }

    /**
     * Generates the cells. When a cell is generated and it collides with pre-existing cell, they are added
     * to each other as a colliding cell and their color are changed to red.
     */
    private void generateCells() {
        for (int i = 0; i < cellCount; i++) {
            Cell newCell = new Cell();
            for (int j = 0; j < cells.size(); j++) {
                Cell oldCell = cells.get(j);
                if (Utils.checkCollision(newCell.getRectangle(), oldCell.getRectangle())) {
                    oldCell.addCollidingCell(newCell);
                    newCell.addCollidingCell(oldCell);
                    oldCell.setColor("red");
                    newCell.setColor("red");
                }
            }
            cells.add(newCell);
            group.getChildren().add(newCell.getRectangle());
        }
    }

    /**
     * When cells are generated, some of them are likely to be on top of each other. This method separates them
     * from each other.
     */
    private void separateCells() {
        Cell closestCollidingCell = getClosestCollidingCell();
    }

    private Cell getClosestCollidingCell() {
        return null;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }
}
