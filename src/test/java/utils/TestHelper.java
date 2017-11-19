package utils;

import static org.junit.Assert.assertEquals;

/**
 * Contains helper methods for all unit tests.
 */
public class TestHelper {
    public TestHelper() {
    }

    /**
     * Assert equals with a formatted message.
     *
     * @param message  the message
     * @param expected the expected object
     * @param actual   the actual object
     */
    public void assertEqualsWithMessage(String message, Object expected, Object actual) {
        assertEquals(String.format(message, expected, actual), expected, actual);
    }
}
