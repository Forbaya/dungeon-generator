package utils;

import gen.Cell;
import gen.Edge;

/**
 * Heap data structure for cells.
 */
public class MinHeap {
    private Edge[] heap;
    private int size;
    private int maxSize;

    /**
     * The constructor.
     *
     * @param maxSize the max size of the heap
     */
    public MinHeap(int maxSize) {
        this.maxSize = maxSize;
        heap = new Edge[maxSize + 1];
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
     * Gets the current size of the heap.
     *
     * @return the current size
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether the heap contains a cell.
     *
     * @param cell the cell
     * @return true if heap contains the cell, otherwise false
     */
    public boolean contains(Cell cell) {
        for (int i = 1; i < size; i++) {
            if (heap[i].equals(cell)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Inserts a new edge to the heap and heapifies.
     *
     * @param edge the edge to be inserted
     * @throws Exception an Exception
     */
    public void insert(Edge edge) throws Exception {
        if (size == maxSize) {
            throw new Exception("The heap is full!");
        } else {
            size++;
            heap[size] = edge;
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
     * Removes the shortest edge from the heap and heapifies.
     *
     * @return the shortest edge that was removed from the heap
     * @throws Exception an Exception
     */
    public Edge removeMin() throws Exception {
        if (isEmpty()) {
            throw new Exception("The heap is empty!");
        }
        Edge min = getMin();
        heap[1] = heap[size];
        size--;
        removeHeapify();
        return min;
    }

    /**
     * Gets the shortest edge from the heap.
     *
     * @return the shortest edge
     * @throws Exception an Exception
     */
    public Edge getMin() throws Exception {
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
        Edge tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
