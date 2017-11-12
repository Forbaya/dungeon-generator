package gen;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import utils.Constants;
import utils.Tuple;
import utils.Utils;

import static utils.Utils.random;

public class Cell {
    private Rectangle rectangle;

    public Cell() {
        createRectangle();
    }

    /**
     * Creates rectangle of the Cell.  The x and y coordinates are inside the circle.
     */
    public void createRectangle() {
        int widthTiles = (random.nextInt() & Integer.MAX_VALUE) % Constants.ROOM_MAX_SIZE;
        int heightTiles = (random.nextInt() & Integer.MAX_VALUE) % Constants.ROOM_MAX_SIZE;
        int roomWidth = Constants.ROOM_MIN_SIZE * Constants.TILE_SIZE + widthTiles * Constants.TILE_SIZE + 1;
        int roomHeight = Constants.ROOM_MIN_SIZE * Constants.TILE_SIZE + heightTiles * Constants.TILE_SIZE + 1;

        Tuple<Integer, Integer> pointInCircle = getRandomPointInCircle();
        int rectX = Utils.snapIntoGrid(Constants.SCREEN_WIDTH / 2 + pointInCircle.x - roomWidth / 2);
        int rectY = Utils.snapIntoGrid(Constants.SCREEN_HEIGHT / 2 + pointInCircle.y - roomHeight / 2);
        this.rectangle = new Rectangle(rectX, rectY, roomWidth, roomHeight);
        rectangle.setStrokeType(StrokeType.CENTERED);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.web("white", 1.0));
        rectangle.setStrokeWidth(1);

    }

    /**
     * Gets a random point in the circle.
     *
     * @return a random point in the circle
     */
    public Tuple<Integer, Integer> getRandomPointInCircle() {
        // Is it okay to use Math library?
        double t = 2 * Constants.PI * Math.random();
        double u = Math.random() + Math.random();
        double r = u > 1 ? 2 - u : u;

        int x = Utils.snapIntoGrid(Constants.CIRCLE_RADIUS * r  * Math.cos(t));
        int y = Utils.snapIntoGrid(Constants.CIRCLE_RADIUS * r  * Math.sin(t));

        return new Tuple<>(x, y);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
