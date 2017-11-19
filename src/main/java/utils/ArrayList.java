package utils;

import java.util.Arrays;

/**
 * Array list for objects.
 */
public class ArrayList<T> {
    private Object[] list;
    private int size;

    public ArrayList() {
        size = 0;
        list = new Object[5];
    }

    /**
     * Adds a given object to the list.
     *
     * @param object the object
     */
    public void add(T object) {
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
        return this.size;
    }
}
