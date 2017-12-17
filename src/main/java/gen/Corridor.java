package gen;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Corridor {
    private Rectangle rectangle;

    public Corridor(int x, int y, int width, int height) {
        createRectangle(x, y, width, height);
        setRectangleRendering();
    }

    private void createRectangle(int x, int y, int width, int height) {
        this.rectangle = new Rectangle(x, y, width, height);
    }

    public void setRectangleRendering() {
        this.rectangle.setStrokeType(StrokeType.CENTERED);
        this.rectangle.setFill(Color.WHITE);
        this.rectangle.setStroke(Color.web("white", 1.0));
        this.rectangle.setStrokeWidth(1);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
