package gen;

/**
 * Data structure for getting data out of the minimal spanning tree.
 */
public class EdgeWithCells {
    private Edge edge;
    private Cell firstCell;
    private Cell secondCell;

    public EdgeWithCells(Edge edge, Cell firstCell, Cell secondCell) {
        this.edge = edge;
        this.firstCell = firstCell;
        this.secondCell = secondCell;
    }

    /**
     * Get the edge.
     *
     * @return the edge
     */
    public Edge getEdge() {
        return edge;
    }

    /**
     * Get the first cell.
     *
     * @return the first cell
     */
    public Cell getFirstCell() {
        return firstCell;
    }

    /**
     * Get the second cell.
     * @return the second cell
     */
    public Cell getSecondCell() {
        return secondCell;
    }
}
