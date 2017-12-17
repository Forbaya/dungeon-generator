package gen;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * The corridors connecting the rooms in the dungeon.
 */
public class Corridor {
    private Rectangle rectangle;

    public Corridor(int x, int y, int width, int height) {
        createRectangle(x, y, width, height);
        setRectangleRendering();
    }

    /**
     * Creates the rectangle.
     *
     * @param x      the x-coordinate
     * @param y      the y-coordinate
     * @param width  the width
     * @param height the height
     */
    private void createRectangle(int x, int y, int width, int height) {
        rectangle = new Rectangle(x, y, width, height);
    }

    /**
     * Sets the rendering options for the rectangle.
     */
    public void setRectangleRendering() {
        rectangle.setStrokeType(StrokeType.CENTERED);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.web("white", 1.0));
        rectangle.setStrokeWidth(1);
    }

    /**
     * Gets the rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return rectangle;
    }
}
