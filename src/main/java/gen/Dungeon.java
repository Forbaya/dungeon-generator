package gen;

import javafx.scene.Group;
import utils.ArrayList;
import utils.Axis;
import utils.Utils;

import java.util.Random;

/**
 * The dungeon itself.
 */
public class Dungeon {
    private ArrayList<Cell> cells;
    private Group group;
    private int cellCount;
    private Random random;
    private ArrayList<Triangle> triangles;

    public Dungeon(Group group, int cellCount) {
        this.group = group;
        this.cellCount = cellCount;
        cells = new ArrayList<>();
        random = new Random();
        triangles = new ArrayList<>();
    }

    /**
     * This starts the dungeon generation.
     *
     * @throws Exception an exception
     */
    public void generateDungeon() throws Exception {
        generateCells();
        separateAllCollidingCells();
    }

    /**
     * Generates the cells. When a cell is generated and it collides with pre-existing cell, they are added
     * to each other as a colliding cell.
     *
     * @throws Exception an Exception
     */
    private void generateCells() throws Exception {
        for (int i = 0; i < cellCount; i++) {
            Cell newCell = new Cell(i);
            for (int j = 0; j < cells.size(); j++) {
                Cell oldCell = cells.get(j);
                if (Utils.checkCollision(newCell.getRectangle(), oldCell.getRectangle())) {
                    addCollisions(oldCell, newCell);
                }
            }
            cells.add(newCell);
            group.getChildren().add(newCell.getRectangle());
        }
    }

    /**
     * Marks the two cells as colliding.
     *
     * @param firstCell  the first cell
     * @param secondCell the second cell
     * @throws Exception an Exception
     */
    private void addCollisions(Cell firstCell, Cell secondCell) throws Exception {
        firstCell.addCollidingCell(secondCell);
        secondCell.addCollidingCell(firstCell);
    }

    /**
     * When cells are generated, some of them are likely to be on top of each other. This method separates them
     * from each other.
     *
     * @throws Exception an Exception
     */
    private void separateAllCollidingCells() throws Exception {
        while (cellsHaveCollisions()) {
            Cell closestCollidingCell = getClosestCollidingCell();
            Cell secondClosestCollidingCell = closestCollidingCell.getClosestCollidingCell();
            separateTwoCells(closestCollidingCell, secondClosestCollidingCell);
        }
    }

    /**
     * Checks if at least two cells are colliding.
     *
     * @return true if some cells are colliding, otherwise false
     */
    private boolean cellsHaveCollisions() {
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).hasCollision()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Separates two cells from each other.
     *
     * @param firstCell  the first colliding cell
     * @param secondCell the second colliding cell
     */
    private void separateTwoCells(Cell firstCell, Cell secondCell) {
        int xDifferenceBetweenCenters = Math.abs(firstCell.getCellCenter().x - secondCell.getCellCenter().x);
        int yDifferenceBetweenCenters = Math.abs(firstCell.getCellCenter().y - secondCell.getCellCenter().y);
        int xCollisionDistance = Math.abs(xDifferenceBetweenCenters - (int)firstCell.getRectangle().getWidth() / 2 - (int)secondCell.getRectangle().getWidth() / 2);
        int yCollisionDistance = Math.abs(yDifferenceBetweenCenters - (int)firstCell.getRectangle().getHeight() / 2 - (int)secondCell.getRectangle().getHeight() / 2);

        Axis axis = getAxis(xCollisionDistance, yCollisionDistance);
        int direction = secondCell.getCellCenter().x >= 0 ? 1 : -1;
        int amountToMove = axis == Axis.X_AXIS ? xCollisionDistance : yCollisionDistance;
        secondCell.move(axis, amountToMove * direction);
        updateCellCollisions(secondCell);
    }

    /**
     * Gets the axis that a cell is moved in. The cell is moved in the axis that has a bigger collision distance.
     * If the x-axis collision distance is same as the y-axis collision distance, the axis is random.
     *
     * @param xCollisionDistance the x-axis collision distance
     * @param yCollisionDistance the y-axis collision distance
     * @return the axis
     */
    public Axis getAxis(int xCollisionDistance, int yCollisionDistance) {
        if (xCollisionDistance == yCollisionDistance) {
            return random.nextFloat() > 0.5 ? Axis.X_AXIS : Axis.Y_AXIS;
        }
        return xCollisionDistance < yCollisionDistance ? Axis.X_AXIS : Axis.Y_AXIS;
    }

    /**
     * Updates the cell collision lists.
     *
     * @param movedCell the cell that was just moved
     */
    private void updateCellCollisions(Cell movedCell) {
        for (int i = 0; i < cells.size(); i++) {
            Cell firstCell = cells.get(i);
            for (int j = 0; j < firstCell.getCollidingCells().size(); j++) {
                Cell secondCell = (Cell) firstCell.getCollidingCells().get(j);
                if (!Utils.checkCollision(firstCell.getRectangle(), secondCell.getRectangle())) {
                    firstCell.removeCollidingCell(secondCell.getId());
                    secondCell.removeCollidingCell(firstCell.getId());
                }
            }
            if (!firstCell.equals(movedCell) && Utils.checkCollision(firstCell.getRectangle(), movedCell.getRectangle())) {
                firstCell.addCollidingCell(movedCell);
                movedCell.addCollidingCell(firstCell);
            }
        }
    }

    /**
     * Gets the colliding cell that is closest to the center of the circle.
     *
     * @return the closest colliding cell
     */
    private Cell getClosestCollidingCell() {
        Cell closest = null;

        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            if (cell.hasCollision() && (closest == null ||cell.getDistanceFromCenterOfCircle() < closest.getDistanceFromCenterOfCircle())) {
                closest = cell;
            }
        }
        return closest;
    }

    /**
     * Implementation of the Bowyer-Watson algorithm.
     */
    public void delaunayTriangulate() {
        ArrayList<Vertex> vertices = getCellCenters();
        Triangle superTriangle = createSuperTriangle(vertices);

        for (int i = 0; i < vertices.size(); i++) {
            Vertex vertex = vertices.get(i);
            ArrayList<Triangle> badTriangles = findBadTriangles(vertex);
        }
    }

    /**
     * Gets the cell centers points as Vertex objects.
     *
     * @return a List of cell center points
     */
    private ArrayList<Vertex> getCellCenters() {
        ArrayList<Vertex> cellCenters = new ArrayList<>();

        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            if (cell.isRoom()) {
                Vertex vertex = new Vertex(cell.getCellCenter().x, cell.getCellCenter().y);
                cellCenters.add(vertex);
            }
        }

        return cellCenters;
    }

    /**
     * Creates the super triangle that contains all the other triangles.
     *
     * @param vertices the vertices
     * @return the super triangle
     */
    private Triangle createSuperTriangle(ArrayList<Vertex> vertices) {
        int minX = vertices.get(0).getX();
        int maxX = minX;
        int minY = vertices.get(0).getY();
        int maxY = minY;

        for (int i = 1; i < vertices.size(); i++) {
            Vertex vertex = vertices.get(i);
            minX = Math.min(vertex.getX(), minX);
            maxX = Math.max(vertex.getX(), maxX);
            minY = Math.min(vertex.getY(), minY);
            maxY = Math.max(vertex.getY(), maxY);
        }

        int convexMultiplier = 2;
        int dx = (maxX - minX) * convexMultiplier;
        int dy = (maxY - minY) * convexMultiplier;
        int deltaMax = Math.max(dx, dy);
        int midX = (minX + maxX) / 2;
        int midY = (minY + maxY) / 2;

        Vertex v1 = new Vertex(midX - 2 * deltaMax, midY - deltaMax);
        Vertex v2 = new Vertex(midX, midY + 2 * deltaMax);
        Vertex v3 = new Vertex(midX + 2 * deltaMax, midY - deltaMax);

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);

        return new Triangle(v1, v2, v3);
    }

    /**
     * Finds the triangles that aren't valid in delaunay triangulation.
     *
     * @param vertex a point
     * @return a list of bad triangles
     */
    public ArrayList<Triangle> findBadTriangles(Vertex vertex) {
        ArrayList<Triangle> badTriangles = new ArrayList<>();

        for (int i = 0; i < triangles.size(); i++) {
            Triangle triangle = triangles.get(i);
            if (triangle.circumcircleContains(vertex)) {
                badTriangles.add(triangle);
            }
        }

        return badTriangles;
    }

    public ArrayList<Edge> findBoundaryOfThePolygonalHole(ArrayList<Triangle> badTriangles) {
        ArrayList<Edge> polygonBoundary = new ArrayList<>();

        for (int i = 0; i < badTriangles.size(); i++) {
            Triangle triangle = badTriangles.get(i);
            polygonBoundary.add(triangle.getFirstEdge());
            polygonBoundary.add(triangle.getSecondEdge());
            polygonBoundary.add(triangle.getThirdEdge());
        }

        ArrayList<Edge> badEdges = new ArrayList<>();
        for (int i = 0; i < polygonBoundary.size(); i++) {
            Edge firstEdge = polygonBoundary.get(i);
            for (int j = 0; i < polygonBoundary.size(); j++) {
                Edge secondEdge = polygonBoundary.get(j);
                if (!firstEdge.equals(secondEdge) && firstEdge.isSame(secondEdge)) {
                    badEdges.add(firstEdge);
                    badEdges.add(secondEdge);
                }
            }
        }

        // Remove duplicate edges.

        return polygonBoundary;
    }

    /**
     * Gets all the cells.
     *
     * @return the cells
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }
}
