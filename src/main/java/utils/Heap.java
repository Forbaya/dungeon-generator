package utils;

import gen.Cell;

/**
 * Heap data structure for cells.
 */
public class Heap {
    private Cell[] heap;
    private int size;
    private int maxSize;

    /**
     * The constructor.
     *
     * @param maxSize the max size of the heap
     */
    public Heap(int maxSize) {
        this.maxSize = maxSize;
        heap = new Cell[maxSize + 1];
        size = 0;
    }

    /**
     * Checks whether the heap is empty.
     *
     * @return true if the heap is empty, otherwise false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Inserts a new cell to the heap and heapifies.
     *
     * @param cell the cell to be inserted
     * @throws Exception an Exception
     */
    public void insert(Cell cell) throws Exception {
        if (size == maxSize) {
            throw new Exception("The heap is full!");
        } else {
            size++;
            heap[size] = cell;
            insertHeapify();
        }
    }

    /**
     * Keeps the minimum heap in order.
     */
    private void insertHeapify() {
        int index = size;
        while (index > 1) {
            int parent = index / 2;
            if (heap[index].compareTo(heap[parent]) >= 0) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
    }

    /**
     * Removes the minimum cell from the heap and heapifies.
     *
     * @return the minimum cell that was removed from the heap
     * @throws Exception an Exception
     */
    public Cell removeMin() throws Exception {
        if (isEmpty()) {
            throw new Exception("The heap is empty!");
        }
        Cell min = getMin();
        heap[1] = heap[size];
        size--;
        removeHeapify();
        return min;
    }

    /**
     * Gets the minimum cell from the heap.
     *
     * @return the minimum cell
     * @throws Exception an Exception
     */
    public Cell getMin() throws Exception {
        if (isEmpty()) {
            throw new Exception("The heap is empty!");
        }
        return heap[1];
    }

    /**
     * Keeps the minimum heap in order.
     */
    private void removeHeapify() {
        int index = 1;
        while (true) {
            int child = index * 2;
            if (child > size) {
                break;
            }
            if (child + 1 <= size) {
                child = findMinChild(child, child + 1);
            }
            if (heap[index].compareTo(heap[child]) <= 0) {
                break;
            }
            swap (index, child);
            index = child;
        }
    }

    /**
     * Finds the min child and returns it's index.
     *
     * @param leftChild  the left child
     * @param rightChild the right child
     * @return the index of the min child
     */
    private int findMinChild(int leftChild, int rightChild) {
        if (heap[leftChild].compareTo(heap[rightChild]) <= 0) {
            return leftChild;
        }
        return rightChild;
    }

    /**
     * Swaps elements i to j.
     *
     * @param i the element i
     * @param j the element j
     */
    private void swap(int i, int j) {
        Cell tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
