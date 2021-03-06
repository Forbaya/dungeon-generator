package utils;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayListTest extends TestHelper {
    private ArrayList<Integer> intArrayList;

    @Before
    public void setUp() throws Exception {
        intArrayList = new ArrayList<>();
    }

    /**
     * Tests adding an integers to the ArrayList and checking it's size.
     */
    @Test
    public void testAddingAnIntegerAndCheckingSize() {
        intArrayList.add(1);
        assertEqualsWithMessage("Expected size was %d and actual size was %d", 1, intArrayList.size());
        intArrayList.add(2);
        assertEqualsWithMessage("Expected size was %d and actual size was %d", 2, intArrayList.size());
    }

    /**
     * Adds integers to the ArrayList and tests if get-method works.
     */
    @Test
    public void testAddAddingAnIntegerAndGettingIt() {
        intArrayList.add(7);
        intArrayList.add(3);
        assertEqualsWithMessage("Expected to get %d and got %d", 7, intArrayList.get(0));
        assertEqualsWithMessage("Expected to get %d and got %d", 3, intArrayList.get(1));
    }

    /**
     * Tests if the ArrayList can have 500 integers.
     */
    @Test
    public void testAddingFiveHundredIntegers() {
        for (int i = 0; i < 500; i++) {
            intArrayList.add(i);
        }
        assertEqualsWithMessage("Expected to have %d integers and had %d integers", 500, intArrayList.size());
    }

    /**
     * Tests removing elements from the ArrayList.
     */
    @Test
    public void testRemovingElements() {
        intArrayList.add(123);
        intArrayList.add(321);
        assertEqualsWithMessage("Expected to have %d elements but had %d elements", 2, intArrayList.size());
        intArrayList.remove(1);
        assertEqualsWithMessage("Expected to have %d elements but had %d elements", 1, intArrayList.size());
        intArrayList.remove(0);
        assertEqualsWithMessage("Expected to have %d elements but had %d elements", 0, intArrayList.size());
    }

    /**
     * Tests an Iterator of the ArrayList.
     */
    @Test
    public void testArrayListIterator() {
        intArrayList.add(0);
        intArrayList.add(1);
        intArrayList.add(2);

        Iterator<Integer> it = intArrayList.iterator();

        assertTrue(it.hasNext());
        int firstElement = it.next();
        assertEqualsWithMessage("The number should be %d but was %d", 0, firstElement);
        it.remove();
        assertEqualsWithMessage("The size of the ArrayList should be %d but was %d", 2, intArrayList.size());
        assertTrue(it.hasNext());
        firstElement = it.next();
        assertEqualsWithMessage("The number should be %d but was %d", 1, firstElement);
        it.remove();
        assertTrue(it.hasNext());
        firstElement = it.next();
        assertEqualsWithMessage("The number should be %d but was %d", 2, firstElement);
        it.remove();
        assertFalse(it.hasNext());
    }
}