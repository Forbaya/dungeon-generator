package ui;

import gen.Dungeon;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class UserInterface {
    private Dungeon dungeon;

    public UserInterface(Stage primaryStage) {
        Group group = new Group();
        group.getChildren().add(addGrid());
        group.getChildren().add(addCircle());

        dungeon = new Dungeon(group, 10);
        dungeon.generateDungeon();

        Scene scene = new Scene(group, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dungeon generator");
        primaryStage.show();
    }

    public Circle addCircle() {
        Circle circle = new Circle(400, 288, 16*16);
        circle.setStroke(Color.web("pink", 0.2));
        circle.setStrokeWidth(1);
        circle.setFill(Color.TRANSPARENT);
        return circle;
    }

    public Group addGrid() {
        Group group = new Group();

        for (int i = 0; i < 800; i += 16) {
            Line verticalLine = new Line(i, 0, i, 600);
            verticalLine.setStrokeType(StrokeType.CENTERED);
            verticalLine.setStroke(Color.web("pink", 0.2));
            verticalLine.setStrokeWidth(1);
            group.getChildren().add(verticalLine);
        }

        for (int i = 0; i < 600; i += 16) {
            Line horizontalLine = new Line(0, i, 800, i);
            horizontalLine.setStrokeType(StrokeType.CENTERED);
            horizontalLine.setStroke(Color.web("pink", 0.2));
            horizontalLine.setStrokeWidth(1);
            group.getChildren().add(horizontalLine);
        }

        return group;
    }
}
