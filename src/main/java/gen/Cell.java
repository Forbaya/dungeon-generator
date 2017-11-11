package gen;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Cell {
    private Rectangle rectangle;

    public Cell(double x, double y, double width, double height) {
        System.out.println("x: " + x + ", y: " + y + ", width: " + width + ", height: " + height);
        this.rectangle = new Rectangle(x, y, width, height);
        rectangle.setStrokeType(StrokeType.CENTERED);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.web("white", 1.0));
        rectangle.setStrokeWidth(1);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public double getX() {
        return this.rectangle.getX();
    }

    public double getY() {
        return this.rectangle.getY();
    }

    public double getWidth() {
        return this.rectangle.getWidth();
    }

    public double getHeight() {
        return this.rectangle.getHeight();
    }
}
