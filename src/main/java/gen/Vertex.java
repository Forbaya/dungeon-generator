package gen;

/**
 * The vertices used in delaunay triangulation and the minimal spanning tree.
 * The vertex is a center of a room, which is why it has a cell id. The x and y are a cell center coordinates.
 */
public class Vertex {
    private int x;
    private int y;
    private int cellId;

    public Vertex(int cellId, int x, int y) {
        this.cellId = cellId;
        this.x = x;
        this.y = y;
    }

    /**
     * Get the cell id.
     *
     * @return the cell id
     */
    public int getCellId() {
        return cellId;
    }

    /**
     * Get the x-coordinate.
     *
     *  @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y-coordinate
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Get the distance to another vertex.
     *
     * @param anotherVertex the another vertex
     * @return the distance to another vertex
     */
    public double distance(Vertex anotherVertex) {
        int dx = this.x - anotherVertex.getX();
        int dy = this.y - anotherVertex.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Check whether this vertex is same as another vertex.
     *
     * @param anotherVertex the another vertex
     * @return true if this vertex is same as another vertex, otherwise false
     */
    public boolean isSame(Vertex anotherVertex) {
        return this.x == anotherVertex.getX() && this.y == anotherVertex.getY();
    }
}
