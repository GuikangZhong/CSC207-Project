package project.utils;

import java.util.ArrayList;
import java.util.List;

public class CSVWriter {
    private List<String> keys;
    private List<WriteOnlyCSVRecord> records;

    public CSVWriter(List<String> keys) {
        this.keys = keys;
        this.records = new ArrayList<>();
    }

    CSVRecord newRecord() {
        WriteOnlyCSVRecord record = new WriteOnlyCSVRecord(keys);
        return record;
    }

    void addRecord(CSVRecord record) {
        records.add((WriteOnlyCSVRecord) record);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.join(",", keys)).append("\n");
        for (WriteOnlyCSVRecord record : records) {
            builder.append(record.toText()).append("\n");
        }
        return builder.toString();
    }
}
