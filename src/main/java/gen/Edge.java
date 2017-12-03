package gen;

public class Edge {
    Vertex firstVertex;
    Vertex secondVertex;

    public Edge(Vertex firstVertex, Vertex secondVertex) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    public Vertex getFirstVertex() {
        return firstVertex;
    }

    public Vertex getSecondVertex() {
        return secondVertex;
    }

    public double getLength() {
        return firstVertex.distance(secondVertex);
    }

    public boolean isSame(Edge anotherEdge) {
        return (firstVertex.isSame(anotherEdge.getFirstVertex()) && secondVertex.isSame(anotherEdge.getSecondVertex())) ||
                (firstVertex.isSame(anotherEdge.getSecondVertex()) && secondVertex.isSame(anotherEdge.getFirstVertex()));
    }
}
