package gen;

import org.junit.Before;
import org.junit.Test;
import utils.Constants;
import utils.TestHelper;

import static org.junit.Assert.*;

public class CellTest extends TestHelper {
    Cell cell;

    @Before
    public void setUp() throws Exception {
        cell  = new Cell(0);
    }

    @Test
    public void testCellWidthAndHeight() throws Exception {
        double minSize = Constants.CELL_MIN_TILES * Constants.TILE_SIZE;
        double maxSize = Constants.CELL_MAX_TILES * Constants.TILE_SIZE;
        System.out.println("min size: " + minSize + ", max size: " + maxSize);
        System.out.println("w: " + cell.getRectangle().getWidth() + ", h: " + cell.getRectangle().getHeight());

        assertTrue(cell.getRectangle().getWidth() >= minSize && cell.getRectangle().getWidth() <= maxSize);
        assertTrue(cell.getRectangle().getHeight() >= minSize && cell.getRectangle().getHeight() <= maxSize);
    }
}