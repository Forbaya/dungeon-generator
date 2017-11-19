package gen;

import javafx.scene.Group;
import org.junit.Before;
import org.junit.Test;
import utils.TestHelper;

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
    public void testDungeonCellCount() {
        int cellCount = 10;
        Dungeon dungeon = new Dungeon(group, cellCount);
        dungeon.generateDungeon();

        assertEqualsWithMessage("The cell count should be %d but was %d", cellCount, dungeon.getCells().size());
    }

}