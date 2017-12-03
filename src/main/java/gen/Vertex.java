package gen;

public class Vertex {
    private int x;
    private int y;

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
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
