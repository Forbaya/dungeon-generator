package ui;

import gen.Dungeon;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import utils.Constants;

/**
 * The graphical user interface to show the dungeon.
 */
public class UserInterface {
    private Dungeon dungeon;

    public UserInterface(Stage primaryStage) {
        Group group = new Group();
        group.getChildren().add(createGrid());
        Circle circle = createCircle();
        group.getChildren().add(circle);

        dungeon = new Dungeon(group, 35);
        try {
            dungeon.generateDungeon();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(group, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dungeon generator");
        primaryStage.show();
    }

    /**
     * Creates the circle used for generating cells.
     *
     * @return the circle
     */
    public Circle createCircle() {
        Circle circle = new Circle(Constants.CIRCLE_CENTER_X, Constants.CIRCLE_CENTER_Y, Constants.CIRCLE_RADIUS);
        circle.setStroke(Color.web("pink", 0.2));
        circle.setStrokeWidth(1);
        circle.setFill(Color.TRANSPARENT);
        return circle;
    }

    /**
     * Creates the background grid.
     *
     * @return Group that contains the grid lines
     */
    public Group createGrid() {
        Group group = new Group();

        for (int i = 0; i < Constants.SCREEN_WIDTH; i += Constants.TILE_SIZE) {
            Line verticalLine = new Line(i, 0, i, Constants.SCREEN_HEIGHT);
            verticalLine.setStrokeType(StrokeType.CENTERED);
            verticalLine.setStroke(Color.web("pink", 0.2));
            verticalLine.setStrokeWidth(1);
            group.getChildren().add(verticalLine);
        }

        for (int i = 0; i < Constants.SCREEN_HEIGHT; i += Constants.TILE_SIZE) {
            Line horizontalLine = new Line(0, i, Constants.SCREEN_WIDTH, i);
            horizontalLine.setStrokeType(StrokeType.CENTERED);
            horizontalLine.setStroke(Color.web("pink", 0.2));
            horizontalLine.setStrokeWidth(1);
            group.getChildren().add(horizontalLine);
        }

        return group;
    }
}
