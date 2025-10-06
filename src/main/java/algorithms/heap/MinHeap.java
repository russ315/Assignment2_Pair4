package algorithms.heap;

public interface MinHeap {
    /** Insert a key and return a handle (node id) used for decreaseKey/delete. */
    BinomialHeap.Node insert(int key);

    /** Return min key or Integer.MIN_VALUE if empty. */
    int findMin();

    /** Extract and return min key; throws if empty. */
    int extractMin();

    /** Decrease the key of a given node to newKey (must be <= current). */
    void decreaseKey(BinomialHeap.Node node, int newKey);

    /** Meld (merge) another heap into this one in O(log n). */
    void merge(BinomialHeap other);

    /** Number of elements. */
    int size();

    /** Is heap empty. */
    boolean isEmpty();
}
