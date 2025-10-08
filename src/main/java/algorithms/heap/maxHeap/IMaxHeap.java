package algorithms.heap.maxHeap;

public interface IMaxHeap {
    void insert(int value);

    int extractMax();

    int getSize();

    void printHeap();
}
