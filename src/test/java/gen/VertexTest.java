package gen;

import org.junit.Test;
import utils.TestHelper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VertexTest extends TestHelper {
    /**
     * Tests the isSame()-method.
     */
    @Test
    public void testIsSame() {
        Vertex vertex = new Vertex(-1, 0, 1);
        Vertex vertex2 = new Vertex(-1, 1, 0);
        Vertex vertex3 = new Vertex(-1, 1, 0);

        assertFalse("The vertices are not the same", vertex.isSame(vertex2));
        assertFalse("The vertices are not the same", vertex.isSame(vertex3));
        assertTrue("The vertices are the same", vertex2.isSame(vertex3));
    }
}
