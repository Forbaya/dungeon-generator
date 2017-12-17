package gen;

/**
 * The triangles that delaunay triangulation generates.
 */
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
        this.firstEdge = new Edge(firstVertex, secondVertex);
        this.secondEdge = new Edge(secondVertex, thirdVertex);
        this.thirdEdge = new Edge(thirdVertex, firstVertex);
    }

    /**
     * Checks whether the triangle contains a given vertex.
     *
     * @param vertex the vertex
     * @return true if the triangle contains the vertex, otherwise false
     */
    public boolean containsVertex(Vertex vertex) {
        return firstVertex.isSame(vertex) || secondVertex.isSame(vertex) || thirdVertex.isSame(vertex);
    }

    /**
     * Checks whether this triangle is same an another triangle.
     *
     * @param anotherTriangle another triangle
     * @return true if this triangle is same as another triangle, otherwise false
     */
    public boolean isSame(Triangle anotherTriangle) {
        Vertex v1 = firstVertex;
        Vertex v2 = secondVertex;
        Vertex v3 = thirdVertex;
        Vertex ov1 = anotherTriangle.getFirstVertex();
        Vertex ov2 = anotherTriangle.getSecondVertex();
        Vertex ov3 = anotherTriangle.getThirdVertex();

        return (v1.isSame(ov1) || v1.isSame(ov2) || v1.isSame(ov3)) &&
                (v2.isSame(ov1) || v2.isSame(ov2) || v2.isSame(ov3)) &&
                (v3.isSame(ov1) || v3.isSame(ov2) || v3.isSame(ov3));
    }

    /**
     * Checks whether the circumcircle containing this triangles vertices contains a given vertex.
     *
     * @param vertex the given vertex
     * @return true if the circumcircle contains the given vertex, otherwise false
     */
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

    /**
     * Get the first vertex.
     *
     * @return the first vertex
     */
    public Vertex getFirstVertex() {
        return firstVertex;
    }

    /**
     * Get the second vertex.
     *
     * @return the second vertex
     */
    public Vertex getSecondVertex() {
        return secondVertex;
    }

    /**
     * Get the third vertex
     *
     * @return the third vertex
     */
    public Vertex getThirdVertex() {
        return thirdVertex;
    }

    /**
     * Get the first edge.
     *
     * @return the first edge
     */
    public Edge getFirstEdge() {
        return firstEdge;
    }

    /**
     * Get the second edge.
     *
     * @return the second edge
     */
    public Edge getSecondEdge() {
        return secondEdge;
    }

    /**
     * Get the third edge.
     *
     * @return the third edge
     */
    public Edge getThirdEdge() {
        return thirdEdge;
    }
}
