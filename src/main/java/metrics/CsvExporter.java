package metrics;

import java.io.FileWriter;
import java.io.IOException;

public class CsvExporter {
    private final String filePath;

    public CsvExporter(String filePath) {
        this.filePath = filePath;
    }

    public void export(String algorithmName, int dataSize, String dataType,
                       long comparisons, long swaps, double durationMs) {
        boolean fileExists = new java.io.File(filePath).exists();

        try (FileWriter writer = new FileWriter(filePath, true)) {
            if (!fileExists) {
                writer.write("Algorithm,DataSize,DataType,Comparisons,Swaps,Duration(ms)\n");
            }

            writer.write(String.format("%s,%d,%s,%d,%d,%.3f\n",
                    algorithmName, dataSize, dataType, comparisons, swaps, durationMs));
        } catch (IOException e) {
            System.err.println("Failed to write CSV: " + e.getMessage());
        }
    }
}
