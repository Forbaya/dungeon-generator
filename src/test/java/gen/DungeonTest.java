package gen;

import javafx.scene.Group;
import org.junit.Before;
import org.junit.Test;
import utils.Constants;
import utils.TestHelper;
import utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class DungeonTest extends TestHelper {
    private Group group;

    @Before
    public void setUp() {
        group = new Group();
    }

    /**
     * Tests whether dungeon has the correct amount of cells.
     */
    @Test
    public void testDungeonCellCount() {
        int cellCount = 10;
        Dungeon dungeon = new Dungeon(group, cellCount);
        dungeon.generateDungeon();

        assertEqualsWithMessage("The cell count should be %d but was %d", cellCount, dungeon.getCells().size());
    }

    @Test
    public void testAddCollision() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Cell cell = new Cell(1);
        Cell cell2 = new Cell(2);

        assertTrue(cell.getCollidingCells().isEmpty());
        assertTrue(cell2.getCollidingCells().isEmpty());

        Dungeon dungeon = new Dungeon(group, 0);

        Method addCollisionsMethod = Dungeon.class.getDeclaredMethod("addCollisions", Cell.class, Cell.class);
        addCollisionsMethod.setAccessible(true);
        addCollisionsMethod.invoke(dungeon, cell, cell2);

        assertEqualsWithMessage("The colliding cell ArrayList size should be %d but was %d", cell.getCollidingCells().size(), 1);
        assertEqualsWithMessage("The colliding cell ArrayList size should be %d but was %d", cell2.getCollidingCells().size(), 1);
        assertTrue(cell.getCollidingCells().get(0).equals(cell2));
        assertTrue(cell2.getCollidingCells().get(0).equals(cell));
    }

    /**
     * Tests the cellsHaveCollision()-method.
     *
     * @throws NoSuchMethodException     a NoSuchMethodException
     * @throws InvocationTargetException an InvocationTargetException
     * @throws IllegalAccessException    an IllegalAccessException
     */
    @Test
    public void testCellsHaveCollision() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Cell cell = new Cell(1);
        Cell cell2 = new Cell(2);
        cell.setRectangle(Constants.CIRCLE_CENTER_X, Constants.CIRCLE_CENTER_Y, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2);
        cell2.setRectangle(Constants.CIRCLE_CENTER_X + Constants.TILE_SIZE, Constants.CIRCLE_CENTER_Y, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2);

        assertTrue(cell.getCollidingCells().isEmpty());
        assertTrue(cell2.getCollidingCells().isEmpty());

        Dungeon dungeon = new Dungeon(group, 0);

        if (Utils.checkCollision(cell.getRectangle(), cell2.getRectangle())) {
            Method addCollisionsMethod = Dungeon.class.getDeclaredMethod("addCollisions", Cell.class, Cell.class);
            addCollisionsMethod.setAccessible(true);
            addCollisionsMethod.invoke(dungeon, cell, cell2);
        }

        dungeon.addCell(cell);
        dungeon.addCell(cell2);

        Method cellsHaveCollisionsMethod = Dungeon.class.getDeclaredMethod("cellsHaveCollisions");
        cellsHaveCollisionsMethod.setAccessible(true);
        assertTrue((boolean) cellsHaveCollisionsMethod.invoke(dungeon));
    }

    /**
     * Tests the getAxis()-method.
     *
     * @throws NoSuchMethodException     a NoSuchMethodException
     * @throws InvocationTargetException an InvocationTargetException
     * @throws IllegalAccessException    an IllegalAccessException
     */
    @Test
    public void testGetAxis() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Dungeon dungeon = new Dungeon(group, 0);
        Method getAxisMethod = Dungeon.class.getDeclaredMethod("getAxis", int.class, int.class);
        getAxisMethod.setAccessible(true);

        assertEquals(Constants.Axis.Y_AXIS, getAxisMethod.invoke(dungeon, Constants.TILE_SIZE * 2, Constants.TILE_SIZE));
        assertEquals(Constants.Axis.X_AXIS, getAxisMethod.invoke(dungeon, Constants.TILE_SIZE, Constants.TILE_SIZE * 2));
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

        Dungeon dungeon = new Dungeon(group, 0);

        Method addCollisionsMethod = Dungeon.class.getDeclaredMethod("addCollisions", Cell.class, Cell.class);
        addCollisionsMethod.setAccessible(true);
        if (Utils.checkCollision(cell.getRectangle(), cell2.getRectangle())) {
            addCollisionsMethod.invoke(dungeon, cell, cell2);
        }
        if (Utils.checkCollision(cell.getRectangle(), cell3.getRectangle())) {
            addCollisionsMethod.invoke(dungeon, cell, cell3);
        }
        if (Utils.checkCollision(cell2.getRectangle(), cell3.getRectangle())) {
            addCollisionsMethod.invoke(dungeon, cell2, cell3);
        }

        assertTrue(cell.getCollidingCells().size() == 2);
        assertTrue(cell2.getCollidingCells().size() == 1);
        assertTrue(cell3.getCollidingCells().size() == 1);

        dungeon.addCell(cell);
        dungeon.addCell(cell2);
        dungeon.addCell(cell3);

        Method getClosestCollidingCellMethod = Dungeon.class.getDeclaredMethod("getClosestCollidingCell");
        getClosestCollidingCellMethod.setAccessible(true);
        Cell closest = (Cell) getClosestCollidingCellMethod.invoke(dungeon);

        assertTrue(closest.equals(cell2));
    }

    /**
     * Tests the updateCellCollisions()-method.
     *
     * @throws NoSuchMethodException     a NoSuchMethodException
     * @throws InvocationTargetException an InvocationTargetException
     * @throws IllegalAccessException    an IllegalAccessException
     */
    @Test
    public void testUpdateCellCollisions() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Cell cell = new Cell(1);
        Cell cell2 = new Cell(2);
        Cell cell3 = new Cell(3);
        cell.setRectangle(Constants.CIRCLE_CENTER_X, Constants.CIRCLE_CENTER_Y, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2);
        cell2.setRectangle(Constants.CIRCLE_CENTER_X + Constants.TILE_SIZE, Constants.CIRCLE_CENTER_Y, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2);
        cell3.setRectangle(Constants.CIRCLE_CENTER_X + Constants.TILE_SIZE * 3, Constants.CIRCLE_CENTER_Y, Constants.TILE_SIZE * 2, Constants.TILE_SIZE * 2);

        Dungeon dungeon = new Dungeon(group, 0);

        Method addCollisionsMethod = Dungeon.class.getDeclaredMethod("addCollisions", Cell.class, Cell.class);
        addCollisionsMethod.setAccessible(true);
        if (Utils.checkCollision(cell.getRectangle(), cell2.getRectangle())) {
            addCollisionsMethod.invoke(dungeon, cell, cell2);
        }
        if (Utils.checkCollision(cell.getRectangle(), cell3.getRectangle())) {
            addCollisionsMethod.invoke(dungeon, cell, cell3);
        }
        if (Utils.checkCollision(cell2.getRectangle(), cell3.getRectangle())) {
            addCollisionsMethod.invoke(dungeon, cell2, cell3);
        }

        assertTrue(cell.getCollidingCells().size() == 1);
        assertTrue(cell2.getCollidingCells().size() == 1);
        assertTrue(cell3.getCollidingCells().size() == 0);

        dungeon.addCell(cell);
        dungeon.addCell(cell2);
        dungeon.addCell(cell3);

        cell2.move(Constants.Axis.X_AXIS, Constants.TILE_SIZE);
        Method updateCellCollisionsMethod = Dungeon.class.getDeclaredMethod("updateCellCollisions", Cell.class);
        updateCellCollisionsMethod.setAccessible(true);
        updateCellCollisionsMethod.invoke(dungeon, cell2);

        assertTrue(cell.getCollidingCells().size() == 0);
        assertTrue(cell2.getCollidingCells().size() == 1);
        assertTrue(cell3.getCollidingCells().size() == 1);

        cell3.move(Constants.Axis.X_AXIS, Constants.TILE_SIZE);
        updateCellCollisionsMethod.invoke(dungeon, cell3);
        assertTrue(cell.getCollidingCells().size() == 0);
        assertTrue(cell2.getCollidingCells().size() == 0);
        assertTrue(cell3.getCollidingCells().size() == 0);
    }
 }
