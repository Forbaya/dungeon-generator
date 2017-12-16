package utils;

import gen.Cell;
import gen.Edge;
import gen.Vertex;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the minHeap data structure.
 */
public class MinHeapTest extends TestHelper {
    private MinHeap minHeap;

    @Before
    public void setUp() {
        minHeap = new MinHeap(50);
    }


    /**
     * Tests whether inserting cells increases the minHeap's size.
     *
     * @throws Exception an Exception
     */
    @Test
    public void testInsert() throws Exception {
        Edge firstEdge = new Edge(new Vertex(1, 0), new Vertex(2, 0));
        Edge secondEdge = new Edge(new Vertex(2, 0), new Vertex(4, 0));
        minHeap.insert(firstEdge);
        minHeap.insert(secondEdge);

        assertEqualsWithMessage("The size was expected to be %d but was %d.", 2, minHeap.size());
    }

    /**
     * Tests whether the removeMin actually returns the min cell.
     *
     * @throws Exception an Exception
     */
    @Test
    public void testRemoveMin() throws Exception {
        Edge firstEdge = new Edge(new Vertex(1, 0), new Vertex(2, 0));
        Edge secondEdge = new Edge(new Vertex(2, 0), new Vertex(4, 0));
        minHeap.insert(firstEdge);
        minHeap.insert(secondEdge);

        System.out.println("first: " + firstEdge.getWeight() + ", second: " + secondEdge.getWeight());

        double shortestEdgeWeight = firstEdge.getWeight() < secondEdge.getWeight() ? firstEdge.getWeight() : secondEdge.getWeight();

        Edge shortestEdge = minHeap.removeMin();
        assertEqualsWithMessage("The shortest edge weight should be  %f but was %f.", shortestEdgeWeight, shortestEdge.getWeight());
    }
}