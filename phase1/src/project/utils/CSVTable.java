package project.utils;

import java.util.Collections;
import java.util.List;

public class CSVTable {
    private List<String> keys;

    public List<String> getKeys() {
        return Collections.unmodifiableList(keys);
    }

    public List<CSVRecord> getRecords() {
        return Collections.unmodifiableList(records);
    }

    private List<CSVRecord> records;

    CSVTable( List<String> keys){
        this.keys = keys;
    }
    void addRecord(CSVRecord record){
        records.add(record);
    }
}
