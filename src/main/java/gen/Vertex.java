package gen;

public class Vertex {
    private int x;
    private int y;
    private int cellId;

    public Vertex(int cellId, int x, int y) {
        this.cellId = cellId;
        this.x = x;
        this.y = y;
    }

    public int getCellId() {
        return cellId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distance(Vertex anotherVertex) {
        int dx = this.x - anotherVertex.getX();
        int dy = this.y - anotherVertex.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public boolean isSame(Vertex anotherVertex) {
        return this.x == anotherVertex.getX() && this.y == anotherVertex.getY();
    }
}
