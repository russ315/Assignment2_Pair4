package org.example;

import algorithms.MaxHeap;
import metrics.CsvExporter;
import metrics.PerformanceTracker;
import org.example.heap.BinomialHeap;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 20000;
        String dataType = "random";
        PerformanceTracker tracker = new PerformanceTracker();
        MaxHeap heap = new MaxHeap(10, tracker);

        tracker.start();
        BinomialHeap.Node[] handles = new BinomialHeap.Node[n];
        for (int i = 0; i < n; i++) {
            handles[i] = heap.insert(data[i]);
        }

        heap.insert(4);
        heap.insert(10);
        heap.insert(3);
        heap.insert(5);
        heap.insert(1);

        int last = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int v = heap.extractMin();
            if (v < last) throw new AssertionError("Heap order violated");
            last = v;
        }
        tracker.stop();

        System.out.println("Max: " + heap.extractMax());
        System.out.println("Comparisons: " + tracker.getComparisons());
        System.out.println("Swaps: " + tracker.getSwaps());
        System.out.println("Duration (ms): " + tracker.getDurationMs());
    }
}
