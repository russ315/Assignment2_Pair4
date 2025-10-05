package org.example;

import algorithms.MaxHeap;
import metrics.CsvExporter;
import metrics.PerformanceTracker;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        PerformanceTracker tracker = new PerformanceTracker();
        MaxHeap heap = new MaxHeap(10, tracker);

        tracker.start();

        heap.insert(4);
        heap.insert(10);
        heap.insert(3);
        heap.insert(5);
        heap.insert(1);

        tracker.stop();

        System.out.println("Max: " + heap.extractMax());
        System.out.println("Comparisons: " + tracker.getComparisons());
        System.out.println("Swaps: " + tracker.getSwaps());
        System.out.println("Duration (ms): " + tracker.getDurationMs());
    }
}