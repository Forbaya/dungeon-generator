package gen;

import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;
import utils.Constants;
import utils.TestHelper;
import utils.Tuple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class CellTest extends TestHelper {
    /**
     * Tests createRectangle()-method by checking rectangles width, height, x, and y values.
     *
     * @throws NoSuchMethodException     a NoSuchMethodException
     * @throws InvocationTargetException an InvocationTargetException
     * @throws IllegalAccessException    an IllegalAccessException
     */
    @Test
    public void testCreateRectangle() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Cell cell = new Cell(1);
        Method createRectangleMethod = Cell.class.getDeclaredMethod("createRectangle");
        createRectangleMethod.setAccessible(true);
        createRectangleMethod.invoke(cell);

        Rectangle rectangle = cell.getRectangle();
        double minSize = Constants.CELL_MIN_TILES * Constants.TILE_SIZE;
        double maxSize = Constants.CELL_MAX_TILES * Constants.TILE_SIZE;
        double maxX = Constants.CIRCLE_CENTER_X + Constants.CIRCLE_RADIUS + rectangle.getWidth();
        double minX = Constants.CIRCLE_CENTER_X - Constants.CIRCLE_RADIUS - rectangle.getWidth();
        double maxY = Constants.CIRCLE_CENTER_Y + Constants.CIRCLE_RADIUS + rectangle.getHeight();
        double minY = Constants.CIRCLE_CENTER_Y - Constants.CIRCLE_RADIUS - rectangle.getHeight();

        assertTrue("width was: " + rectangle.getWidth() + " and min size was: " + minSize, rectangle.getWidth() >= minSize);
        assertTrue("width was: " + rectangle.getWidth() + " and max size was: " + maxSize, rectangle.getWidth() <= maxSize);
        assertTrue("height was: " + rectangle.getHeight() + " and min size was: " + minSize, rectangle.getHeight() >= minSize);
        assertTrue("height was: " + rectangle.getHeight() + " and max size was: " + maxSize, rectangle.getHeight() <= maxSize);
        assertTrue("x was: " + rectangle.getX() + " and max x was: " + maxX, rectangle.getX() <= maxX);
        assertTrue("x was: " + rectangle.getX() + " and min x was: " + minX, rectangle.getX() >= minX);
        assertTrue("y was: " + rectangle.getY() + " and max y was: " + maxY, rectangle.getY() <= maxY);
        assertTrue("y was: " + rectangle.getY() + " and min y was: " + minY, rectangle.getY() >= minY);
    }

    /**
     * Tests createCellCenterAndDistanceFromCircle()-method by checking that the distance from center of the circle is correct.
     *
     * @throws NoSuchMethodException     a NoSuchMethodException
     * @throws InvocationTargetException an InvocationTargetException
     * @throws IllegalAccessException    an IllegalAccessException
     */
    @Test
    public void testCreateCellCenterAndDistanceFromCircle() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Cell cell = new Cell(1);
        cell.setRectangle(Constants.CIRCLE_CENTER_X + Constants.TILE_SIZE, Constants.CIRCLE_CENTER_Y - Constants.TILE_SIZE, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2);
        Rectangle rectangle = cell.getRectangle();

        Method createCellCenterAndDistanceFromCircleMethod = Cell.class.getDeclaredMethod("createCellCenterAndDistanceFromCircle", int.class, int.class, int.class, int.class);
        createCellCenterAndDistanceFromCircleMethod.setAccessible(true);
        createCellCenterAndDistanceFromCircleMethod.invoke(cell, (int)rectangle.getX(), (int)rectangle.getY(), (int)rectangle.getWidth(), (int)rectangle.getHeight());

        assertEqualsWithMessage("The distance from circle center should be %f but was %f", 32.0, cell.getDistanceFromCenterOfCircle());
    }

    /**
     * Tests the addCollidingCell()-method.
     */
    @Test
    public void testAddCollidingCell() {
        Cell cell = new Cell(1);
        Cell cell2 = new Cell(2);

        assertEqualsWithMessage("There should've been 0 colliding cells but was %d", 0, cell.getCollidingCells().size());
        assertEqualsWithMessage("There should've been 0 colliding cells but was %d", 0, cell2.getCollidingCells().size());

        cell.addCollidingCell(cell2);
        cell2.addCollidingCell(cell);

        assertEqualsWithMessage("There should've been 1 colliding cell but was %d", 1, cell.getCollidingCells().size());
        assertEqualsWithMessage("There should've been 1 colliding cell but was %d", 1, cell2.getCollidingCells().size());
        assertTrue("The colliding cell wasn't what it was supposed to be", cell.equals(cell2.getCollidingCells().get(0)));
        assertTrue("The colliding cell wasn't what it was supposed to be", cell2.equals(cell.getCollidingCells().get(0)));
    }

    /**
     * Tests the removeCollidingCell()-method.
     */
    @Test
    public void testRemoveCollidingCell() {
        Cell cell = new Cell(1);
        Cell cell2 = new Cell(2);

        cell.addCollidingCell(cell2);
        cell2.addCollidingCell(cell);

        cell.removeCollidingCell(cell2.getId());
        cell2.removeCollidingCell(cell.getId());

        assertEqualsWithMessage("There shouldn't be any colliding cells", 0, cell.getCollidingCells().size());
        assertEqualsWithMessage("There shouldn't be any colliding cells", 0, cell2.getCollidingCells().size());
    }

    /**
     * Tests the getClosestCollidingCell()-method.
     *
     * @throws NoSuchMethodException     a NoSuchMethodException
     * @throws InvocationTargetException an InvocationTargetException
     * @throws IllegalAccessException    an IllegalAccessException
     */
    @Test
    public void testGetClosestCollidingCell() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Cell cell = new Cell(1);
        Cell cell2 = new Cell(2);
        Cell cell3 = new Cell(3);
        cell.setRectangle(Constants.CIRCLE_CENTER_X + Constants.TILE_SIZE, Constants.CIRCLE_CENTER_Y, Constants.TILE_SIZE * 3, Constants.TILE_SIZE * 3);
        cell2.setRectangle(Constants.CIRCLE_CENTER_X + Constants.TILE_SIZE, Constants.CIRCLE_CENTER_Y - Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 3);
        cell3.setRectangle(Constants.CIRCLE_CENTER_X + Constants.TILE_SIZE * 3, Constants.CIRCLE_CENTER_Y + Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 3, Constants.TILE_SIZE * 3);

        cell.addCollidingCell(cell2);
        cell.addCollidingCell(cell3);
        cell2.addCollidingCell(cell);
        cell3.addCollidingCell(cell);

        Method getClosestCollidingCellMethod = Cell.class.getDeclaredMethod("getClosestCollidingCell");
        getClosestCollidingCellMethod.setAccessible(true);
        Cell closestCollidingCell = (Cell) getClosestCollidingCellMethod.invoke(cell);

        assertTrue(closestCollidingCell.equals(cell2));
    }

    /**
     * Tests the move()-method.
     */
    @Test
    public void testMove() {
        Cell cell = new Cell(1);
        double x = cell.getRectangle().getX();
        double y = cell.getRectangle().getY();
        cell.move(Constants.Axis.X_AXIS, 32);

        assertEqualsWithMessage("The x value should've been %f but was %f", x + 32, cell.getRectangle().getX());
        assertEqualsWithMessage("The y value should've been %f but was %f", y, cell.getRectangle().getY());

        cell.move(Constants.Axis.Y_AXIS, -32);

        assertEqualsWithMessage("The x value should've been %f but was %f", x + 32, cell.getRectangle().getX());
        assertEqualsWithMessage("The y value should've been %f but was %f", y - 32, cell.getRectangle().getY());
    }

    /**
     * Tests the hasCollision()-method.
     */
    @Test
    public void testHasCollision() {
        Cell cell = new Cell(1);
        Cell cell2 = new Cell(2);

        assertFalse("Cell should have no collisions", cell.hasCollision());
        assertFalse("Cell should have no collisions", cell2.hasCollision());

        cell.addCollidingCell(cell2);
        cell2.addCollidingCell(cell);

        assertTrue("Cell should have a collision", cell.hasCollision());
        assertTrue("Cell should have a collision", cell2.hasCollision());
    }

    /**
     * Tests the getRandomPointInCircle()-method.
     *
     * @throws NoSuchMethodException     a NoSuchMethodException
     * @throws InvocationTargetException an InvocationTargetException
     * @throws IllegalAccessException    an IllegalAccessException
     */
    @Test
    public void testGetRandomPointInCircle() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getRandomPointInCircleMethod = Cell.class.getDeclaredMethod("getRandomPointInCircle");
        getRandomPointInCircleMethod.setAccessible(true);
        Cell cell = new Cell(1);
        Tuple<Integer, Integer> point = (Tuple<Integer, Integer>) getRandomPointInCircleMethod.invoke(cell);

        int minX = Constants.CIRCLE_CENTER_X - Constants.CIRCLE_RADIUS;
        int maxX = Constants.CIRCLE_CENTER_X + Constants.CIRCLE_RADIUS;
        int minY = Constants.CIRCLE_CENTER_Y - Constants.CIRCLE_RADIUS;
        int maxY = Constants.CIRCLE_CENTER_Y + Constants.CIRCLE_RADIUS;

        int actualX = point.x + Constants.CIRCLE_CENTER_X;
        int actualY = point.y + Constants.CIRCLE_CENTER_Y;

        assertTrue("x was: " + actualX + " and max x is " + maxX, actualX <= maxX);
        assertTrue("x was: " + actualX + " and min x is " + minX, actualX >= minX);
        assertTrue("y was: " + actualY + " and max y is " + maxY, actualY <= maxY);
        assertTrue("y was: " + actualY + " and min y is " + minY, actualY >= minY);
    }
}
