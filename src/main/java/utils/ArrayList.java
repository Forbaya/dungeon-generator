package utils;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array list for objects.
 */
public class ArrayList<T> implements Iterable<T> {
    private Object[] list;
    private int size;
    private int modCount;

    public ArrayList() {
        size = 0;
        list = new Object[5];
        modCount = 0;
    }

    /**
     * Adds a given object to the list.
     *
     * @param object the object
     */
    public void add(T object) {
        modCount++;
        if (list.length - size <= 2) {
            increaseListSize();
        }
        list[size++] = object;
    }

    /**
     * Doubles the size of the list.
     */
    private void increaseListSize() {
        list = Arrays.copyOf(list, list.length * 2);
    }

    /**
     * Get object from index.
     *
     * @param index the index
     * @return the object
     */
    public T get(int index) {
        return (T)list[index];
    }

    /**
     * Removes and returns the object in the given index.
     *
     * @param index the index
     * @return the removed object
     */
    public Object remove(int index) {
        modCount++;
        if (index < size) {
            Object object = list[index];
            list[index] = null;
            int i = index;
            while (i < size) {
                list[i] = list[i + 1];
                list[i + 1] = null;
                i++;
            }
            size--;
            return object;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Gives the size of the list.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether the ArrayList is empty.
     *
     * @return true if ArrayList is empty, otherwise false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int current = 0;
            int lastRet = -1;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return current != size;
            }

            @Override
            public T next() {
                checkForComodification();
                int i = current;
                if (i >= size) {
                    throw new NoSuchElementException();
                }
                Object[] elementData = ArrayList.this.list;
                if (i >= list.length) {
                    throw new ConcurrentModificationException();
                }
                current = i + 1;
                return (T) elementData[lastRet = i];
            }

            @Override
            public void remove() {
                if (lastRet < 0) {
                    throw new IllegalStateException();
                }
                checkForComodification();
                try {
                    ArrayList.this.remove(lastRet);
                    current = lastRet;
                    lastRet = -1;
                    expectedModCount = modCount;
                } catch (IndexOutOfBoundsException ex) {
                    throw new ConcurrentModificationException();
                }
            }

            void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
