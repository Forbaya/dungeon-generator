package gen;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import utils.Constants;

public class Edge {
    Vertex firstVertex;
    Vertex secondVertex;
    Line line;

    public Edge(Vertex firstVertex, Vertex secondVertex) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        line = new Line(firstVertex.getX() + Constants.SCREEN_WIDTH / 2, firstVertex.getY() + Constants.SCREEN_HEIGHT / 2, secondVertex.getX() + Constants.SCREEN_WIDTH / 2, secondVertex.getY() + Constants.SCREEN_HEIGHT / 2);
        setRendering();
    }

    private void setRendering() {
        line.setStrokeType(StrokeType.CENTERED);
        line.setStroke(Color.web("pink", 0.5));
        line.setStrokeWidth(1);
    }

    public Vertex getFirstVertex() {
        return firstVertex;
    }

    public Vertex getSecondVertex() {
        return secondVertex;
    }

    public Line getLine() {
        return line;
    }

    public double getLength() {
        return firstVertex.distance(secondVertex);
    }

    public boolean isSame(Edge anotherEdge) {
        return (firstVertex.isSame(anotherEdge.getFirstVertex()) && secondVertex.isSame(anotherEdge.getSecondVertex())) ||
                (firstVertex.isSame(anotherEdge.getSecondVertex()) && secondVertex.isSame(anotherEdge.getFirstVertex()));
    }
}
