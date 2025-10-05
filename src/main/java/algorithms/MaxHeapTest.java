package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MaxHeapTest {

    @Test
    void testInsertAndExtractMax() {
        PerformanceTracker tracker = new PerformanceTracker();
        MaxHeap heap = new MaxHeap(10, tracker);

        heap.insert(5);
        heap.insert(10);
        heap.insert(3);
        heap.insert(8);

        assertEquals(10, heap.extractMax());
        assertEquals(8, heap.extractMax());
        assertEquals(5, heap.extractMax());
        assertEquals(3, heap.extractMax());
        assertEquals(0, heap.getSize());
    }

    @Test
    void testEmptyHeapExtractThrowsException() {
        PerformanceTracker tracker = new PerformanceTracker();
        MaxHeap heap = new MaxHeap(5, tracker);

        assertThrows(IllegalStateException.class, heap::extractMax);
    }

    @Test
    void testInsertBeyondCapacityThrowsException() {
        PerformanceTracker tracker = new PerformanceTracker();
        MaxHeap heap = new MaxHeap(2, tracker);

        heap.insert(1);
        heap.insert(2);

        assertThrows(IllegalStateException.class, () -> heap.insert(3));
    }

    @Test
    void testSingleElementHeap() {
        PerformanceTracker tracker = new PerformanceTracker();
        MaxHeap heap = new MaxHeap(3, tracker);

        heap.insert(42);
        assertEquals(42, heap.extractMax());
        assertEquals(0, heap.getSize());
    }

    @Test
    void testDuplicateElements() {
        PerformanceTracker tracker = new PerformanceTracker();
        MaxHeap heap = new MaxHeap(6, tracker);

        heap.insert(5);
        heap.insert(5);
        heap.insert(5);

        assertEquals(5, heap.extractMax());
        assertEquals(5, heap.extractMax());
        assertEquals(5, heap.extractMax());
        assertEquals(0, heap.getSize());
    }

    @Test
    void testHeapPropertyMaintained() {
        PerformanceTracker tracker = new PerformanceTracker();
        MaxHeap heap = new MaxHeap(8, tracker);

        int[] values = {4, 10, 3, 5, 1, 9, 2, 8};
        for (int v : values) heap.insert(v);

        int prev = heap.extractMax();
        while (heap.getSize() > 0) {
            int next = heap.extractMax();
            assertTrue(prev >= next, "Heap property violated!");
            prev = next;
        }
    }
}
