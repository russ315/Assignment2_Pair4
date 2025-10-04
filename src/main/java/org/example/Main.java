package org.example;

import metrics.CsvExporter;
import metrics.PerformanceTracker;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        PerformanceTracker tracker = new PerformanceTracker();
        tracker.start();

        // Some work
        Thread.sleep(2000);

        tracker.stop();
        CsvExporter exporter = new CsvExporter("results.csv");
        exporter.export("MinHeap", 10000, "random",
                tracker.getComparisons(),
                tracker.getSwaps(),
                tracker.getDurationMs());
    }
}