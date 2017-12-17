package gen;

import javafx.scene.Group;
import utils.ArrayList;
import utils.Constants;
import utils.Tuple;
import utils.Utils;

import java.util.Iterator;
import java.util.Random;

/**
 * The dungeon itself.
 */
public class Dungeon {
    private ArrayList<Cell> cells;
    private ArrayList<Cell> rooms;
    private Group group;
    private int cellCount;
    private int roomCount;
    private Random random;
    private ArrayList<Triangle> triangles;
    private int roomIds[];
    private ArrayList<Edge> mstEdges;
    private ArrayList<EdgeWithCells> mstEdgesWithCells;
    private ArrayList<Corridor> corridors;


    public Dungeon(Group group, int cellCount) {
        this.group = group;
        this.cellCount = cellCount;
        cells = new ArrayList<>();
        rooms = new ArrayList<>();
        random = new Random();
        triangles = new ArrayList<>();
        mstEdgesWithCells = new ArrayList<>();
        corridors = new ArrayList<>();
    }

    /**
     * Gets the axis that a cell is moved in. The cell is moved in the axis that has a smaller collision distance.
     * If the x-axis collision distance is same as the y-axis collision distance, the axis is randomed.
     *
     * @param xCollisionDistance the x-axis collision distance
     * @param yCollisionDistance the y-axis collision distance
     * @return the axis
     */
    private Constants.Axis getAxis(int xCollisionDistance, int yCollisionDistance) {
        if (xCollisionDistance == yCollisionDistance) {
            return random.nextFloat() > 0.5 ? Constants.Axis.X_AXIS : Constants.Axis.Y_AXIS;
        }
        return xCollisionDistance < yCollisionDistance ? Constants.Axis.X_AXIS : Constants.Axis.Y_AXIS;
    }

    /**
     * Gets all the cells.
     *
     * @return the cells
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }

    private int[] getRoomIds() {
        int[] roomIds = new int[roomCount];

        for (int i = 0; i < roomCount; i++) {
            Cell cell = rooms.get(i);
            if (cell.isRoom()) {
                roomIds[i] = cell.getId();
            }
        }

        return roomIds;
    }

    /**
     * This starts the dungeon generation.
     */
    public void generateDungeon() {
        generateCells();
        separateAllCollidingCells();
        delaunayTriangulate();
        buildMinimumSpanningTree();
        createShapedCorridors();

        renderRooms();
    }

    private void renderRooms() {
        for (Cell room : rooms) {
            group.getChildren().add(room.getRectangle());
        }
    }

    /**
     * Generates the cells. When a cell is generated and it collides with pre-existing cell, they are added
     * to each other as a colliding cell.
     */
    private void generateCells() {
        for (int i = 0; i < cellCount; i++) {
            Cell newCell = new Cell(i);
            for (Cell oldCell : cells) {
                if (Utils.checkCollision(newCell.getRectangle(), oldCell.getRectangle())) {
                    addCollisions(oldCell, newCell);
                }
            }
            cells.add(newCell);
            if (newCell.isRoom()) {
                rooms.add(newCell);
            }
        }
        roomCount = rooms.size();
        roomIds = getRoomIds();
    }

    /**
     * Method for adding cells. This is used in tests.
     *
     * @param cell the cell to be added
     */
    public void addCell(Cell cell) {
        this.cells.add(cell);
    }

    /**
     * Marks the two cells as colliding.
     *
     * @param firstCell  the first cell
     * @param secondCell the second cell
     */
    private void addCollisions(Cell firstCell, Cell secondCell) {
        firstCell.addCollidingCell(secondCell);
        secondCell.addCollidingCell(firstCell);
    }

    /**
     * When cells are generated, some of them are likely to be on top of each other. This method separates them
     * from each other.
     */
    private void separateAllCollidingCells() {
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
        for (Cell cell : cells) {
            if (cell.hasCollision()) {
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

        Constants.Axis axis = getAxis(xCollisionDistance, yCollisionDistance);
        int direction = secondCell.getCellCenter().x >= 0 ? 1 : -1;
        int amountToMove = axis == Constants.Axis.X_AXIS ? xCollisionDistance : yCollisionDistance;
        secondCell.move(axis, amountToMove * direction);
        updateCellCollisions(secondCell);
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
                Cell secondCell = firstCell.getCollidingCells().get(j);
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
        for (Cell cell : cells) {
            if (cell.hasCollision() && (closest == null || cell.getDistanceFromCenterOfCircle() < closest.getDistanceFromCenterOfCircle())) {
                closest = cell;
            }
        }
        return closest;
    }

    /**
     * Implementation of the Bowyer-Watson algorithm.
     */
    private void delaunayTriangulate() {
        ArrayList<Vertex> vertices = getCellCenters();
        Triangle superTriangle = createSuperTriangle(vertices);
        triangles.add(superTriangle);

        for (Vertex vertex : vertices) {
            ArrayList<Triangle> badTriangles = findBadTriangles(vertex);
            ArrayList<Edge> polygon = findBoundaryOfThePolygonalHole(badTriangles);
            removeBadTrianglesFromTheTriangulation(badTriangles);
            retriangulateThePolygonalHole(polygon, vertex);
        }

        removeTrianglesThatShareAVertexWithSuperTriangle(superTriangle);
        //renderDelaunayTriangles();
    }

    /**
     * Gets the cell centers points as Vertex objects.
     *
     * @return a List of cell center points
     */
    private ArrayList<Vertex> getCellCenters() {
        ArrayList<Vertex> cellCenters = new ArrayList<>();

        for (Cell cell : cells) {
            if (cell.isRoom()) {
                Vertex vertex = new Vertex(cell.getId(), cell.getCellCenter().x, cell.getCellCenter().y);
                cellCenters.add(vertex);
            }
        }

        return cellCenters;
    }

    /**
     * Creates the super triangle that contains all the other triangles. The super triangle  and all edges
     * connecting to it will be removed in the end.
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

        Vertex v1 = new Vertex(-1, midX - 2 * deltaMax, midY - deltaMax);
        Vertex v2 = new Vertex(-1, midX, midY + 2 * deltaMax);
        Vertex v3 = new Vertex(-1, midX + 2 * deltaMax, midY - deltaMax);

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);

        return new Triangle(v1, v2, v3);
    }

    /**
     * Finds the triangles that aren't valid in Delaunay triangulation.
     *
     * @param vertex a point
     * @return a list of bad triangles
     */
    private ArrayList<Triangle> findBadTriangles(Vertex vertex) {
        ArrayList<Triangle> badTriangles = new ArrayList<>();

        for (int i = 0; i < triangles.size(); i++) {
            Triangle triangle = triangles.get(i);
            if (triangle.circumcircleContains(vertex)) {
                badTriangles.add(triangle);
            }
        }

        return badTriangles;
    }

    /**
     * Finds the boundary of the polygonal hole.
     *
     * @param badTriangles the bad triangles
     * @return the boundary of the polygonal hole
     */
    private ArrayList<Edge> findBoundaryOfThePolygonalHole(ArrayList<Triangle> badTriangles) {
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
            for (int j = 0; j < polygonBoundary.size(); j++) {
                Edge secondEdge = polygonBoundary.get(j);
                if (!firstEdge.equals(secondEdge) && firstEdge.isSame(secondEdge)) {
                    badEdges.add(firstEdge);
                    badEdges.add(secondEdge);
                }
            }
        }

        Iterator<Edge> it = polygonBoundary.iterator();
        while (it.hasNext()) {
            Edge edge = it.next();
            for (Edge badEdge : badEdges) {
                if (edge.isSame(badEdge)) {
                    it.remove();
                    break;
                }
            }
        }

        return polygonBoundary;
    }

    /**
     * Removes the bad triangles from the triangulation.
     *
     * @param badTriangles the bad triangles
     */
    private void removeBadTrianglesFromTheTriangulation(ArrayList<Triangle> badTriangles) {
        Iterator<Triangle> it = triangles.iterator();
        while (it.hasNext()) {
            Triangle triangle = it.next();
            for (Triangle badTriangle : badTriangles) {
                if (triangle.isSame(badTriangle)) {
                    it.remove();
                }
            }
        }
    }

    /**
     * Creates new triangles in the polygonal hole by connecting all edges to a vertex.
     *
     * @param polygon the polygon
     * @param vertex  the vertex
     */
    private void retriangulateThePolygonalHole(ArrayList<Edge> polygon, Vertex vertex) {
        for (int i = 0; i < polygon.size(); i++) {
            Edge edge = polygon.get(i);
            triangles.add(new Triangle(edge.getFirstVertex(), edge.getSecondVertex(), vertex));
        }
    }

    /**
     * Removes all the triangles from the triangulation that share a vertex with the super triangle.
     *
     * @param triangle the super triangle
     */
    private void removeTrianglesThatShareAVertexWithSuperTriangle(Triangle triangle) {
        Iterator<Triangle> it = triangles.iterator();
        while (it.hasNext()) {
            Triangle t = it.next();
            if (t.containsVertex(triangle.getFirstVertex()) || t.containsVertex(triangle.getSecondVertex()) || t.containsVertex(triangle.getThirdVertex())) {
                it.remove();
            }
        }
    }

    /**
     * Renders the delaunay triangles.
     */
    public void renderDelaunayTriangles() {
        for (int i = 0; i < triangles.size(); i++) {
            Triangle triangle = triangles.get(i);
            group.getChildren().add(triangle.getFirstEdge().getLine());
            group.getChildren().add(triangle.getSecondEdge().getLine());
            group.getChildren().add(triangle.getThirdEdge().getLine());
        }
    }

    /**
     * Builds a minimum spanning tree by using Prim's algorithm.
     */
    private void buildMinimumSpanningTree() {
        int graph[][] = createAdjacencyMatrix();
        int parent[] = new int[roomCount];
        int key[] = new int[roomCount];
        boolean mstSet[] = new boolean[roomCount];

        for (int i = 0; i < roomCount; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < roomCount; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < roomCount; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        mstEdges = createMSTEdges(parent);
        //renderMSTEdges();
    }

    /**
     * Creates the adjacency matrix used by Prim's algorithm. Only the cells that are rooms have edges.
     *
     * @return the adjacency matrix as a two dimensional array
     */
    private int[][] createAdjacencyMatrix() {
        int graph[][] = new int[roomCount][roomCount];
        for (int i = 0; i < roomCount; i++) {
            for (int j = 0; i < roomCount; i++) {
                graph[i][j] = 0;
            }
        }

        for (Triangle triangle : triangles) {
            Edge firstEdge = triangle.getFirstEdge();
            Edge secondEdge = triangle.getSecondEdge();
            Edge thirdEdge = triangle.getThirdEdge();

            addTwoNodesToAdjacencyMatrix(graph, firstEdge);
            addTwoNodesToAdjacencyMatrix(graph, secondEdge);
            addTwoNodesToAdjacencyMatrix(graph, thirdEdge);
        }

        return graph;
    }

    /**
     * Gets the minimum key of a node that isn't part of the MST.
     *
     * @param key    the keys
     * @param mstSet the set of nodes that are part of the MST
     * @return the minimum key
     */
    private int minKey(int key[], boolean mstSet[]) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < roomCount; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    /**
     * Adds two nodes to the adjacency matrix. There are more cells than rooms, which means room id's
     * cannot be used as adjacency matrix indices. I didn't want to create a Map data structure which
     * is why the roomIds is used. It does hurt the time complexity though.
     *
     * @param graph the graph as an adjacency matrix
     * @param edge  the edge containing the nodes
     */
    private void addTwoNodesToAdjacencyMatrix(int graph[][], Edge edge) {
        int u = 0;
        for (int i = 0; i < roomIds.length; i++) {
            if (edge.getFirstVertex().getCellId() == roomIds[i]) {
                u = i;
                break;
            }
        }
        int v = 0;
        for (int i = 0; i < roomIds.length; i++) {
            if (edge.getSecondVertex().getCellId() == roomIds[i]) {
                v = i;
                break;
            }
        }

        graph[u][v] = (int) edge.getWeight();
        graph[v][u] = (int) edge.getWeight();
    }

    /**
     * Creates MST edges from the parent array made by Prim's algorithm. The roomIds array is used again to map the
     * indices back to the rooms.
     *
     * @param parent the parent array
     * @return ArrayList of containing MST Edges
     */
    private ArrayList<Edge> createMSTEdges(int parent[]) {
        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = parent.length - 1; i > 0; i--) {
            int firstVertexId = roomIds[i];
            int secondVertexId = roomIds[parent[i]];
            Cell firstCell = null;
            Cell secondCell = null;
            for (Cell cell : rooms) {
                if (cell.getId() == firstVertexId) {
                    firstCell = cell;
                }
                if (cell.getId() == secondVertexId) {
                    secondCell = cell;
                }
            }
            Tuple<Integer, Integer> firstCellCenter = firstCell.getCellCenter();
            Tuple<Integer, Integer> secondCellCenter = secondCell.getCellCenter();
            Edge edge = new Edge(new Vertex(firstVertexId, firstCellCenter.x, firstCellCenter.y), new Vertex(secondVertexId, secondCellCenter.x, secondCellCenter.y));
            edges.add(edge);
            mstEdgesWithCells.add(new EdgeWithCells(edge, firstCell, secondCell));
        }

        return edges;
    }

    /**
     * Renders the MST edges.
     */
    private void renderMSTEdges() {
        for (Edge edge : mstEdges) {
            group.getChildren().add(edge.getLine());
        }
    }

    /**
     * Creates the L-shaped corridors between the rooms.
     */
    public void createShapedCorridors() {
        for (EdgeWithCells edgeWithCells : mstEdgesWithCells) {
            Cell firstCell = edgeWithCells.getFirstCell();
            Cell secondCell = edgeWithCells.getSecondCell();
            if (Utils.areNextToEachOther(firstCell.getRectangle(), secondCell.getRectangle())) {
                continue;
            }

            Tuple<Integer, Integer> firstCellCenter = firstCell.getCellCenter();
            Tuple<Integer, Integer> secondCellCenter = secondCell.getCellCenter();
            Constants.Direction[] directions = new Constants.Direction[2];
            directions[0] = firstCellCenter.y < secondCellCenter.y ? Constants.Direction.DOWN : Constants.Direction.UP;
            directions[1] = firstCellCenter.x < secondCellCenter.x ? Constants.Direction.RIGHT : Constants.Direction.LEFT;

            if (directions[0] == Constants.Direction.DOWN) {
                Corridor firstCorridor = createUpToDownCorridor(firstCell, secondCell);
                corridors.add(firstCorridor);
                group.getChildren().add(firstCorridor.getRectangle());
                if (!Utils.areNextToEachOther(firstCorridor.getRectangle(), secondCell.getRectangle())) {
                    if (directions[1] == Constants.Direction.RIGHT) {
                        Corridor secondCorridor = createLeftToRightCorridorAfterUpToDown(secondCell, firstCorridor);
                        corridors.add(secondCorridor);
                        group.getChildren().add(secondCorridor.getRectangle());
                    } else {
                        Corridor secondCorridor = createRightToLeftCorridorAfterUpToDown(secondCell, firstCorridor);
                        corridors.add(secondCorridor);
                        group.getChildren().add(secondCorridor.getRectangle());

                    }
                }
            } else {
                Corridor firstCorridor = createDownToUpCorridor(firstCell, secondCell);
                corridors.add(firstCorridor);
                group.getChildren().add(firstCorridor.getRectangle());

                if (!Utils.areNextToEachOther(firstCorridor.getRectangle(), secondCell.getRectangle())) {
                    if (directions[1] == Constants.Direction.RIGHT) {
                        Corridor secondCorridor = createLeftToRightCorridorAfterDownToUp(secondCell, firstCorridor);
                        corridors.add(secondCorridor);
                        group.getChildren().add(secondCorridor.getRectangle());

                    } else {
                        Corridor secondCorridor = createRightToLeftCorridorAfterDownToUp(secondCell, firstCorridor);
                        corridors.add(secondCorridor);
                        group.getChildren().add(secondCorridor.getRectangle());
                    }
                }
            }
        }
    }

    /**
     * Creates a corridor going down from a room.
     *
     * @param firstCell  the first cell
     * @param secondCell the second cell
     * @return the created corridor
     */
    private Corridor createUpToDownCorridor(Cell firstCell, Cell secondCell) {
        int firstCorridorStartX = Utils.snapIntoGrid(firstCell.getCellCenter().x + Constants.CIRCLE_CENTER_X);
        int firstCorridorStartY = (int) firstCell.getRectangle().getY() + (int) firstCell.getRectangle().getHeight();
        int firstCorridorEndX = firstCorridorStartX + Constants.TILE_SIZE - 1;
        int firstCorridorEndY = Utils.snapIntoGrid(secondCell.getCellCenter().y + Constants.CIRCLE_CENTER_Y);

        return new Corridor(firstCorridorStartX, firstCorridorStartY, firstCorridorEndX - firstCorridorStartX, firstCorridorEndY - firstCorridorStartY);
    }

    /**
     * Creates a corridor going from left to right (towards the second cell) that continues a corridor going down from a room.
     *
     * @param secondCell    the second cell
     * @param firstCorridor the first corridor
     * @return the created corridor
     */
    private Corridor createLeftToRightCorridorAfterUpToDown(Cell secondCell, Corridor firstCorridor) {
        int secondCorridorStartX = (int )firstCorridor.getRectangle().getX() + (int) firstCorridor.getRectangle().getWidth();
        int secondCorridorStartY = (int) firstCorridor.getRectangle().getY() + (int) firstCorridor.getRectangle().getHeight() - Constants.TILE_SIZE;
        int secondCorridorEndX = (int) secondCell.getRectangle().getX();
        int secondCorridorEndY = secondCorridorStartY + Constants.TILE_SIZE - 1;

        return new Corridor(secondCorridorStartX, secondCorridorStartY, secondCorridorEndX - secondCorridorStartX, secondCorridorEndY - secondCorridorStartY);
    }

    /**
     * Creates a corridor going from right to left (towards the second cell) that continues a corridor going down from a room.
     *
     * @param secondCell    the second cell
     * @param firstCorridor the first corridor
     * @return the created corridor
     */
    private Corridor createRightToLeftCorridorAfterUpToDown(Cell secondCell, Corridor firstCorridor) {
        int secondCorridorStartX = (int) secondCell.getRectangle().getX() + (int) secondCell.getRectangle().getWidth();
        int secondCorridorStartY = Utils.snapIntoGrid(secondCell.getCellCenter().y + Constants.CIRCLE_CENTER_Y);
        int secondCorridorEndX = (int )firstCorridor.getRectangle().getX() + (int) firstCorridor.getRectangle().getWidth();
        int secondCorridorEndY = (int) firstCorridor.getRectangle().getY() + (int) firstCorridor.getRectangle().getHeight() + Constants.TILE_SIZE - 1;

        return new Corridor(secondCorridorStartX, secondCorridorStartY, secondCorridorEndX - secondCorridorStartX, secondCorridorEndY - secondCorridorStartY);
    }

    /**
     * Creates a corridor going up from a room.
     *
     * @param firstCell  the first cell
     * @param secondCell the second cell
     * @return the created corridor
     */
    private Corridor createDownToUpCorridor(Cell firstCell, Cell secondCell) {
        int firstCorridorStartX = Utils.snapIntoGrid(firstCell.getCellCenter().x + Constants.CIRCLE_CENTER_X);
        int firstCorridorStartY = Utils.snapIntoGrid(secondCell.getCellCenter().y + Constants.CIRCLE_CENTER_Y);
        int firstCorridorWidth = Constants.TILE_SIZE;
        int firstCorridorHeight = (int) firstCell.getRectangle().getY() - firstCorridorStartY;

        return new Corridor(firstCorridorStartX, firstCorridorStartY, firstCorridorWidth, firstCorridorHeight);
    }

    /**
     * Creates a corridor going from left to right (towards the second cell) that continues a corridor going up from a room.
     *
     * @param secondCell    the second cell
     * @param firstCorridor the first corridor
     * @return the created corridor
     */
    private Corridor createLeftToRightCorridorAfterDownToUp(Cell secondCell, Corridor firstCorridor) {
        int secondCorridorStartX = (int) firstCorridor.getRectangle().getX() + (int) firstCorridor.getRectangle().getWidth();
        int secondCorridorStartY = (int) firstCorridor.getRectangle().getY();
        int secondCorridorEndX = (int) secondCell.getRectangle().getX();
        int secondCorridorEndY = secondCorridorStartY + Constants.TILE_SIZE - 1;

        return new Corridor(secondCorridorStartX, secondCorridorStartY, secondCorridorEndX - secondCorridorStartX, secondCorridorEndY - secondCorridorStartY);
    }

    /**
     * Creates a corridor going from right to left (towards the second cell) that continues a corridor going up from a room.
     *
     * @param secondCell    the second cell
     * @param firstCorridor the first corridor
     * @return the created corridor
     */
    private Corridor createRightToLeftCorridorAfterDownToUp(Cell secondCell, Corridor firstCorridor) {
        int secondCorridorStartX = (int) secondCell.getRectangle().getX() + (int) secondCell.getRectangle().getWidth();
        int secondCorridorStartY = Utils.snapIntoGrid(secondCell.getCellCenter().y + Constants.CIRCLE_CENTER_Y);
        int secondCorridorEndX = (int) firstCorridor.getRectangle().getX() + (int) firstCorridor.getRectangle().getWidth();
        int secondCorridorEndY = secondCorridorStartY + Constants.TILE_SIZE - 1;

        return new Corridor(secondCorridorStartX, secondCorridorStartY, secondCorridorEndX - secondCorridorStartX, secondCorridorEndY - secondCorridorStartY);
    }
}
