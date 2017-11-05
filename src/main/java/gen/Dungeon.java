package gen;

public class Dungeon {
    private Cell[] cells;

    public Dungeon(int cellCount) {
        cells = new Cell[cellCount];
    }

    public void generateDungeon() {
        generateCells();
    }

    public void generateCells() {
        for (int i = 0; i < cells.length - 1; i++) {
            cells[i] = new Cell();
        }
    }
}
