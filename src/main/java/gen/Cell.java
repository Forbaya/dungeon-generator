package gen;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import utils.ArrayList;
import utils.Constants;
import utils.Tuple;
import utils.Utils;

import static utils.Utils.random;

/**
 * Cells that the dungeon is made of.
 */
public class Cell {
    private Rectangle rectangle;
    private Tuple<Integer, Integer> cellCenter;
    private double distanceFromCenterOfCircle;
    private ArrayList<Cell> collidingCells;

    public Cell() {
        collidingCells = new ArrayList<>();
        createRectangle();
        createCellCenterAndDistanceFromCircle((int)rectangle.getX(), (int)rectangle.getY(), (int)rectangle.getWidth(), (int)rectangle.getHeight());
    }

    /**
     * Creates rectangle of the Cell.  The x and y coordinates are inside the circle.
     */
    private void createRectangle() {
        int widthTiles = (random.nextInt() & Integer.MAX_VALUE) % Constants.CELL_MAX_TILES;
        int heightTiles = (random.nextInt() & Integer.MAX_VALUE) % Constants.CELL_MAX_TILES;
        int roomWidth = widthTiles >= Constants.CELL_MIN_TILES ? widthTiles * Constants.TILE_SIZE : Constants.CELL_MIN_TILES * Constants.TILE_SIZE;
        int roomHeight = heightTiles >= Constants.CELL_MIN_TILES ? heightTiles * Constants.TILE_SIZE : Constants.CELL_MIN_TILES * Constants.TILE_SIZE;
        System.out.println("Room w: " + roomWidth + ", Room h: " + roomHeight);

        Tuple<Integer, Integer> pointInCircle = getRandomPointInCircle();
        int rectX = Utils.snapIntoGrid(Constants.SCREEN_WIDTH / 2 + pointInCircle.x - roomWidth / 2);
        int rectY = Utils.snapIntoGrid(Constants.SCREEN_HEIGHT / 2 + pointInCircle.y - roomHeight / 2);
        this.rectangle = new Rectangle(rectX, rectY, roomWidth, roomHeight);
        setRendering();
    }

    /**
     * Sets the rendering options for JavaFX.
     */
    public void setRendering() {
        this.rectangle.setStrokeType(StrokeType.CENTERED);
        this.rectangle.setFill(Color.TRANSPARENT);
        this.rectangle.setStroke(Color.web("white", 1.0));
        this.rectangle.setStrokeWidth(1);
    }

    /**
     * Sets the color for JavaFX.
     *
     * @param color the color to set
     */
    public void setColor(String color) {
        this.rectangle.setStroke(Color.web(color, 1.0));
    }

    /**
     * Creates to tuple that contains the center of the cell. Also creates a double variable for distance from the
     * center of the circle. These are used for separating cells from each other.
     *
     * @param x          the x
     * @param y          the y
     * @param roomWidth  the width of the room
     * @param roomHeight the height of the room
     */
    private void createCellCenterAndDistanceFromCircle(int x, int y, int roomWidth, int roomHeight) {
        int cellCenterX = x + roomWidth / 2 - Constants.CIRCLE_CENTER_X;
        int cellCenterY = y + roomHeight / 2 - Constants.CIRCLE_CENTER_Y;
        cellCenter = new Tuple<>(cellCenterX, cellCenterY);
        distanceFromCenterOfCircle = Math.sqrt(cellCenterX * cellCenterX + cellCenterY * cellCenterY);
    }

    /**
     * Gets a random point in the circle.
     *
     * @return a random point in the circle
     */
    public Tuple<Integer, Integer> getRandomPointInCircle() {
        double t = 2 * Math.PI * Math.random();
        double u = Math.random() + Math.random();
        double r = u > 1 ? 2 - u : u;

        int x = Utils.snapIntoGrid(Constants.CIRCLE_RADIUS * r  * Math.cos(t));
        int y = Utils.snapIntoGrid(Constants.CIRCLE_RADIUS * r  * Math.sin(t));

        return new Tuple<>(x, y);
    }

    /**
     * Gets the rectangle of the cell.
     *
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Gets the ArrayList of colliding cells.
     *
     * @return the ArrayList of colliding cells
     */
    public ArrayList getCollidingCells() {
        return collidingCells;
    }

    /**
     * Adds a colliding cell to the list of colliding cells.
     *
     * @param cell a colliding cell
     */
    public void addCollidingCell(Cell cell) {
        collidingCells.add(cell);
    }

    /**
     * Checks whether the cell is colliding with another cell.
     *
     * @return true if cell has a collision
     */
    public boolean hasCollision() {
        return collidingCells.size() > 0;
    }
}
