package gen;

import org.junit.Test;
import utils.TestHelper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TriangleTest extends TestHelper {
    /**
     * Tests the isSame()-method.
     */
    @Test
    public void testIsSame() {
        Triangle triangle = new Triangle(new Vertex(-1, 1, 0), new Vertex(-1, 2, 3), new Vertex(-1, 4, 5));
        Triangle triangle2 = new Triangle(new Vertex(-1, 2, 3), new Vertex(-1, 4, 5), new Vertex(-1, 1, 0));
        Triangle triangle3 = new Triangle(new Vertex(-1, 2, 3), new Vertex(-1, 1, 0), new Vertex(-1, 8, 5));

        assertTrue("The triangles are the same", triangle.isSame(triangle2));
        assertFalse("The triangles are not the same", triangle.isSame(triangle3));
        assertFalse("The triangles are not the same", triangle2.isSame(triangle3));
    }

    /**
     * Tests the containsVertex()-method.
     */
    @Test
    public void testContainsVertex() {
        Triangle triangle = new Triangle(new Vertex(-1, 1, 0), new Vertex(-1, 2, 3), new Vertex(-1, 4, 5));
        Vertex firstVertex = new Vertex(-1, 1, 0);
        Vertex secondVertex = new Vertex(-1, 2, 3);
        Vertex thirdVertex = new Vertex(-1, 4, 5);
        Vertex fourthVertex = new Vertex(-1, 1, 2);

        assertTrue("Triangle contains the vertex (" + firstVertex.getX() + ", " + firstVertex.getY() + ")", triangle.containsVertex(firstVertex));
        assertTrue("Triangle contains the vertex (" + secondVertex.getX() + ", " + secondVertex.getY() + ")", triangle.containsVertex(secondVertex));
        assertTrue("Triangle contains the vertex (" + thirdVertex.getX() + ", " + thirdVertex.getY() + ")", triangle.containsVertex(thirdVertex));
        assertFalse("Triangle does not contain the vertex (" + fourthVertex.getX() + ", " + fourthVertex.getY() + ")", triangle.containsVertex(fourthVertex));
    }
}
