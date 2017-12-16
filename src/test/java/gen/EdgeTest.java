package gen;

import org.junit.Test;
import utils.TestHelper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EdgeTest extends TestHelper {
    /**
     * Tests the isSame()-method.
     */
    @Test
    public void testIsSame() {
        Edge edge = new Edge(new Vertex(-1, 1, 0), new Vertex(-1, 3, 4));
        Edge edge2 = new Edge(new Vertex(-1, 3, 4), new Vertex(-1, 1, 0));
        Edge edge3 = new Edge(new Vertex(-1, 1, 0), new Vertex(-1, 2, 0));

        assertTrue("The edges are the same", edge.isSame(edge2));
        assertFalse("The edges are not the same", edge.isSame(edge3));
        assertFalse("The edges are not the same", edge2.isSame(edge3));
    }
}
