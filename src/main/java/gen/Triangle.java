package gen;

public class Triangle {
    private Vertex firstVertex;
    private Vertex secondVertex;
    private Vertex thirdVertex;
    private Edge firstEdge;
    private Edge secondEdge;
    private Edge thirdEdge;

    public Triangle(Vertex firstVertex, Vertex secondVertex, Vertex thirdVertex) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        this.thirdVertex = thirdVertex;
    }

    public boolean containsVertex(Vertex vertex) {
        return firstVertex.isSame(vertex) || secondVertex.isSame(vertex) || thirdVertex.isSame(vertex);
    }

    public boolean isSame(Triangle otherTriangle) {
        Vertex v1 = firstVertex;
        Vertex v2 = secondVertex;
        Vertex v3 = thirdVertex;
        Vertex ov1 = otherTriangle.getFirstVertex();
        Vertex ov2 = otherTriangle.getSecondVertex();
        Vertex ov3 = otherTriangle.getThirdVertex();

        return (v1.isSame(ov1) || v1.isSame(ov2) || v1.isSame(ov3)) &&
                (v2.isSame(ov1) || v2.isSame(ov2) || v2.isSame(ov3)) &&
                (v3.isSame(ov1) || v3.isSame(ov2) || v3.isSame(ov3));
    }

    public boolean circumcircleContains(Vertex vertex) {
        int v1x = firstVertex.getX();
        int v2x = secondVertex.getX();
        int v3x = thirdVertex.getX();
        int v1y = firstVertex.getY();
        int v2y = secondVertex.getY();
        int v3y = thirdVertex.getY();

        double ab = v1x * v1x + v1y * v1y;
        double cd = v2x * v2x + v2y * v2y;
        double ef = v3x * v3x + v3y * v3y;

        double circumcircleX = (ab * (v3y - v2y) + cd * (v1y - v3y) + ef * (v2y - v1y)) / (v1x * (v3y - v2y) + v2x * (v1y - v3y) + v3x * (v2y - v1y)) / 2.0;
        double circumcircleY = (ab * (v3x - v2x) + cd * (v1x - v3x) + ef * (v2x - v1x)) / (v1y * (v3x - v2x) + v2y * (v1x - v3x) + v3y * (v2x - v1x)) / 2.f;
        double circumcircleRadius = Math.sqrt(((v1x - circumcircleX) * (v1x - circumcircleX)) + ((v1y - circumcircleY) * (v1y - circumcircleY)));

        double distance = Math.sqrt(((vertex.getX() - circumcircleX) * (vertex.getX() - circumcircleX)) + ((vertex.getY() - circumcircleY) * (vertex.getY() - circumcircleY)));

        return distance <= circumcircleRadius;
    }

    public Vertex getFirstVertex() {
        return firstVertex;
    }

    public Vertex getSecondVertex() {
        return secondVertex;
    }

    public Vertex getThirdVertex() {
        return thirdVertex;
    }

    public Edge getFirstEdge() {
        return firstEdge;
    }

    public Edge getSecondEdge() {
        return secondEdge;
    }

    public Edge getThirdEdge() {
        return thirdEdge;
    }
}
