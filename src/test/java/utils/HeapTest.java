package utils;

import gen.Cell;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the heap data structure.
 */
public class HeapTest extends TestHelper {
    private Heap heap;

    @Before
    public void setUp() {
        heap = new Heap(50);
    }


    /**
     * Tests whether inserting cells increases the heap's size.
     *
     * @throws Exception an Exception
     */
    @Test
    public void testInsert() throws Exception {
        Cell firstCell = new Cell(0);
        Cell secondCell = new Cell(1);
        heap.insert(firstCell);
        heap.insert(secondCell);

        assertEqualsWithMessage("The size was expected to be %d but was %d.", 2, heap.size());
    }

    /**
     * Tests whether the removeMin actually returns the min cell.
     *
     * @throws Exception an Exception
     */
    @Test
    public void testRemoveMin() throws Exception {
        Cell firstCell = new Cell(0);
        Cell secondCell = new Cell(1);
        heap.insert(firstCell);
        heap.insert(secondCell);

        System.out.println("first: " + firstCell.getDistanceFromCenterOfCircle() + ", second: " + secondCell.getDistanceFromCenterOfCircle());

        double smallestDistance = firstCell.getDistanceFromCenterOfCircle() < secondCell.getDistanceFromCenterOfCircle() ?
                firstCell.getDistanceFromCenterOfCircle() : secondCell.getDistanceFromCenterOfCircle();

        Cell smallestDistanceCell = heap.removeMin();
        assertEqualsWithMessage("The smallest distance from circle was expected to be %f but was %f.", smallestDistance, smallestDistanceCell.getDistanceFromCenterOfCircle());
    }
}