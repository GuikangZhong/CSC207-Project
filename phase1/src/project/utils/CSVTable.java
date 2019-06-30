package project.utils;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CSVTable implements Iterable<CSVRecord> {
    private List<String> keys;

    public List<String> getKeys() {
        return Collections.unmodifiableList(keys);
    }

    public List<CSVRecord> getRecords() {
        return Collections.unmodifiableList(records);
    }

    private List<CSVRecord> records;

    CSVTable(List<String> keys) {
        this.keys = keys;
    }

    void addRecord(CSVRecord record) {
        records.add(record);
    }

    @Override
    public Iterator<CSVRecord> iterator() {
        return records.iterator();
    }
}
