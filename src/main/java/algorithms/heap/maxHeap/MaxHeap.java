package algorithms.heap.maxHeap;

import metrics.PerformanceTracker;

public class MaxHeap implements IMaxHeap {
    private final int[] heap;
    private int size;
    private final PerformanceTracker tracker;


    public MaxHeap(int capacity, PerformanceTracker tracker) {
        this.heap = new int[capacity];
        this.size = 0;
        this.tracker = tracker;
    }

    @Override
    public void insert(int value) {
        if (size == heap.length) {
            throw new IllegalStateException("Heap is full");
        }

        heap[size] = value;
        int current = size;
        size++;

        // Bubble up
        while (current > 0) {
            tracker.addComparison();
            if (heap[current] > heap[parent(current)]) {
                swap(current, parent(current));
                tracker.swap();
                current = parent(current);
            } else {
                break;
            }
        }
    }

    @Override
    public int extractMax() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int root = heap[0];
        heap[0] = heap[--size];
        heapify(0);
        return root;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    private void heapify(int i) {
        int largest = i;
        int left = left(i);
        int right = right(i);

        if (left < size) {
            tracker.addComparison();
            if (heap[left] > heap[largest]) {
                largest = left;
            }
        }

        if (right < size) {
            tracker.addComparison();
            if (heap[right] > heap[largest]) {
                largest = right;
            }
        }

        if (largest != i) {
            swap(i, largest);
            tracker.swap();
            heapify(largest);
        }
    }
}
