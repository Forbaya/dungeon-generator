package gen;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import utils.Constants;

/**
 * The edges used in delaunay triangulation and minimal spanning tree.
 */
public class Edge implements Comparable<Edge> {
    Vertex firstVertex;
    Vertex secondVertex;
    Line line;
    double weight;

    public Edge(Vertex firstVertex, Vertex secondVertex) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        weight = firstVertex.distance(secondVertex);
        line = new Line(firstVertex.getX() + Constants.SCREEN_WIDTH / 2, firstVertex.getY() + Constants.SCREEN_HEIGHT / 2, secondVertex.getX() + Constants.SCREEN_WIDTH / 2, secondVertex.getY() + Constants.SCREEN_HEIGHT / 2);
        setRendering();
    }

    /**
     * Sets the rendering of the line.
     */
    private void setRendering() {
        line.setStrokeType(StrokeType.CENTERED);
        line.setStroke(Color.web("pink", 0.5));
        line.setStrokeWidth(1);
    }

    /**
     * Get the first vertex.
     *
     * @return the first vertex
     */
    public Vertex getFirstVertex() {
        return firstVertex;
    }

    /**
     * Get the second vertex
     *
     * @return the second vertex
     */
    public Vertex getSecondVertex() {
        return secondVertex;
    }

    /**
     * Get the line.
     *
     * @return the line
     */
    public Line getLine() {
        return line;
    }

    /**
     * Get the weight of the edge.
     *
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Check whether this edge is the same edge as another edge.
     *
     * @param anotherEdge another edge
     * @return true if this edge is same with another edge, otherwise false
     */
    public boolean isSame(Edge anotherEdge) {
        return (firstVertex.isSame(anotherEdge.getFirstVertex()) && secondVertex.isSame(anotherEdge.getSecondVertex())) ||
                (firstVertex.isSame(anotherEdge.getSecondVertex()) && secondVertex.isSame(anotherEdge.getFirstVertex()));
    }

    /**
     * Compares this edge to another edge.
     *
     * @param anotherEdge another edge
     * @return -1 if this edges weight less than another edges, 0 if they're the same, otherwise 1
     */
    @Override
    public int compareTo(Edge anotherEdge) {
        if (this.weight < anotherEdge.getWeight()) {
            return -1;
        } else if (this.weight == anotherEdge.getWeight()) {
            return 0;
        }
        return 1;
    }
}
