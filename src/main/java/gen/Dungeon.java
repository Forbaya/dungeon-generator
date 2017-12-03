package gen;

import javafx.scene.Group;
import utils.ArrayList;
import utils.Axis;
import utils.Utils;

import java.util.Random;

/**
 * The dungeon itself.
 */
public class Dungeon {
    private ArrayList<Cell> cells;
    private Group group;
    private int cellCount;
    private Random random;

    public Dungeon(Group group, int cellCount) {
        this.group = group;
        this.cellCount = cellCount;
        cells = new ArrayList<>();
        random = new Random();
    }

    /**
     * This starts the dungeon generation.
     *
     * @throws Exception an exception
     */
    public void generateDungeon() throws Exception {
        generateCells();
        separateAllCollidingCells();
    }

    /**
     * Generates the cells. When a cell is generated and it collides with pre-existing cell, they are added
     * to each other as a colliding cell and their color are changed to red.
     *
     * @throws Exception an Exception
     */
    private void generateCells() throws Exception {
        for (int i = 0; i < cellCount; i++) {
            Cell newCell = new Cell(i);
            for (int j = 0; j < cells.size(); j++) {
                Cell oldCell = cells.get(j);
                if (Utils.checkCollision(newCell.getRectangle(), oldCell.getRectangle())) {
                    addCollisions(oldCell, newCell);
                }
            }
            cells.add(newCell);
            group.getChildren().add(newCell.getRectangle());
        }
    }

    /**
     * Marks the two cells as colliding.
     *
     * @param firstCell  the first cell
     * @param secondCell the second cell
     * @throws Exception an Exception
     */
    private void addCollisions(Cell firstCell, Cell secondCell) throws Exception {
        firstCell.addCollidingCell(secondCell);
        secondCell.addCollidingCell(firstCell);
        firstCell.setColor("red");
        secondCell.setColor("red");
    }

    /**
     * When cells are generated, some of them are likely to be on top of each other. This method separates them
     * from each other.
     *
     * @throws Exception an Exception
     */
    private void separateAllCollidingCells() throws Exception {
        while (cellsHaveCollisions()) {
            Cell closestCollidingCell = getClosestCollidingCell();
            Cell secondClosestCollidingCell = closestCollidingCell.getClosestCollidingCell();
            separateTwoCells(closestCollidingCell, secondClosestCollidingCell);
        }
    }

    /**
     * Checks if at least two cells are colliding.
     *
     * @return true if some cells are colliding, otherwise false
     */
    private boolean cellsHaveCollisions() {
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).hasCollision()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Separates two cells from each other.
     *
     * @param firstCell  the first colliding cell
     * @param secondCell the second colliding cell
     */
    private void separateTwoCells(Cell firstCell, Cell secondCell) {
        int xDifferenceBetweenCenters = Math.abs(firstCell.getCellCenter().x - secondCell.getCellCenter().x);
        int yDifferenceBetweenCenters = Math.abs(firstCell.getCellCenter().y - secondCell.getCellCenter().y);
        int xCollisionDistance = Math.abs(xDifferenceBetweenCenters - (int)firstCell.getRectangle().getWidth() / 2 - (int)secondCell.getRectangle().getWidth() / 2);
        int yCollisionDistance = Math.abs(yDifferenceBetweenCenters - (int)firstCell.getRectangle().getHeight() / 2 - (int)secondCell.getRectangle().getHeight() / 2);

        Axis axis = getAxis(xCollisionDistance, yCollisionDistance);
        int direction = secondCell.getCellCenter().x >= 0 ? 1 : -1;
        int amountToMove = axis == Axis.X_AXIS ? xCollisionDistance : yCollisionDistance;
        secondCell.move(axis, amountToMove * direction);
        updateCellCollisions(secondCell);
    }

    /**
     * Gets the axis that a cell is moved in. The cell is moved in the axis that has a bigger collision distance.
     * If the x-axis collision distance is same as the y-axis collision distance, the axis is random.
     *
     * @param xCollisionDistance the x-axis collision distance
     * @param yCollisionDistance the y-axis collision distance
     * @return the axis
     */
    public Axis getAxis(int xCollisionDistance, int yCollisionDistance) {
        if (xCollisionDistance == yCollisionDistance) {
            return random.nextFloat() > 0.5 ? Axis.X_AXIS : Axis.Y_AXIS;
        }
        return xCollisionDistance < yCollisionDistance ? Axis.X_AXIS : Axis.Y_AXIS;
    }

    /**
     * Updates the cell collision lists.
     *
     * @param movedCell the cell that was just moved
     */
    private void updateCellCollisions(Cell movedCell) {
        for (int i = 0; i < cells.size(); i++) {
            Cell firstCell = cells.get(i);
            for (int j = 0; j < firstCell.getCollidingCells().size(); j++) {
                Cell secondCell = (Cell) firstCell.getCollidingCells().get(j);
                if (!Utils.checkCollision(firstCell.getRectangle(), secondCell.getRectangle())) {
                    firstCell.removeCollidingCell(secondCell.getId());
                    secondCell.removeCollidingCell(firstCell.getId());
                }
            }
            if (!firstCell.equals(movedCell) && Utils.checkCollision(firstCell.getRectangle(), movedCell.getRectangle())) {
                firstCell.addCollidingCell(movedCell);
                movedCell.addCollidingCell(firstCell);
            }
        }
    }

    /**
     * Gets the colliding cell that is closest to the center of the circle.
     *
     * @return the closest colliding cell
     */
    private Cell getClosestCollidingCell() {
        Cell closest = null;

        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            if (cell.hasCollision() && (closest == null ||cell.getDistanceFromCenterOfCircle() < closest.getDistanceFromCenterOfCircle())) {
                closest = cell;
            }
        }
        return closest;
    }

    /**
     * Gets all the cells.
     *
     * @return the cells
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }
}
