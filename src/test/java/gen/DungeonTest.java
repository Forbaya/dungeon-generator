package gen;

import javafx.scene.Group;
import org.junit.Before;
import org.junit.Test;
import utils.TestHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static junit.framework.TestCase.assertTrue;

public class DungeonTest extends TestHelper {
    private Group group;

    @Before
    public void setUp() throws Exception {
        group = new Group();
    }

    /**
     * Tests whether dungeon has the correct amount of cells.
     */
    @Test
    public void testDungeonCellCount() throws Exception {
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

        Method addCollisions = Dungeon.class.getDeclaredMethod("addCollisions", Cell.class, Cell.class);
        addCollisions.setAccessible(true);
        addCollisions.invoke(dungeon, cell, cell2);

        assertEqualsWithMessage("The colliding cell ArrayList size should be %d but was %d", cell.getCollidingCells().size(), 1);
        assertEqualsWithMessage("The colliding cell ArrayList size should be %d but was %d", cell2.getCollidingCells().size(), 1);
    }
}