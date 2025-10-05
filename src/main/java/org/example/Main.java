package org.example;

import metrics.CsvExporter;
import metrics.PerformanceTracker;
import org.example.heap.BinomialHeap;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 20000;
        String dataType = "random";
        PerformanceTracker tracker = new PerformanceTracker();
        BinomialHeap heap = new BinomialHeap(tracker);

        int[] data = new int[n];
        Random rnd = new Random(42);
        for (int i = 0; i < n; i++) data[i] = rnd.nextInt(1_000_000);

        tracker.reset();
        tracker.start();
        BinomialHeap.Node[] handles = new BinomialHeap.Node[n];
        for (int i = 0; i < n; i++) {
            handles[i] = heap.insert(data[i]);
        }

        for (int i = 0; i < n/100; i++) {
            int idx = rnd.nextInt(n);
            heap.decreaseKey(handles[idx], data[idx]/2);
        }

        int last = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int v = heap.extractMin();
            if (v < last) throw new AssertionError("Heap order violated");
            last = v;
        }
        tracker.stop();

        CsvExporter exporter = new CsvExporter("results.csv");
        exporter.export("BinomialMinHeap", n, dataType,
                tracker.getComparisons(),
                tracker.getSwaps(),
                tracker.getDurationMs());

        System.out.printf("Done. Comparisons=%d, Swaps=%d, Time=%.2f ms\n",
                tracker.getComparisons(), tracker.getSwaps(), tracker.getDurationMs());
    }
}
