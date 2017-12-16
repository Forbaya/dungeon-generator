package gen;

import javafx.scene.shape.Rectangle;

public class Corridor {
    private Rectangle rectangle;

    private Corridor(int x, int y, int width, int height) {
        createRectangle(x, y, width, height);
    }

    private void createRectangle(int x, int y, int width, int height) {
        this.rectangle = new Rectangle(x, y, width, height);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
