package gen;

public class EdgeWithCells {
    private Edge edge;
    private Cell firstCell;
    private Cell secondCell;

    public EdgeWithCells(Edge edge, Cell firstCell, Cell secondCell) {
        this.edge = edge;
        this.firstCell = firstCell;
        this.secondCell = secondCell;
    }

    public Edge getEdge() {
        return edge;
    }

    public Cell getFirstCell() {
        return firstCell;
    }

    public Cell getSecondCell() {
        return secondCell;
    }
}
