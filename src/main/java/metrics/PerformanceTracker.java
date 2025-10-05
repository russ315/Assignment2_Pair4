package metrics;

public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long startTime;
    private long endTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    /** Count a comparison (e.g., key1 < key2). Returns true for convenient inline usage. */
    public boolean cmp(boolean result) {
        comparisons++;
        return result;
    }

    /** Record a swap/move operation. */
    public void swap() {
        swaps++;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getDurationNs() {
        return endTime - startTime;
    }

    public double getDurationMs() {
        return (endTime - startTime) / 1_000_000.0;
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        startTime = 0;
        endTime = 0;
    }
}
