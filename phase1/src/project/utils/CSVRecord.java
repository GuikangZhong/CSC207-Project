package project.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class CSVRecord {
    public abstract String get(String key);

    public abstract String get(int index);

    public abstract void put(String key, String value);
}

class ReadOnlyCSVRecord extends CSVRecord {
    private HashMap<String, String> row;
    private List<String> rowOrdered;
    private List<String> keys;

    public ReadOnlyCSVRecord(List<String> keys, HashMap<String, String> row, List<String> rowOrdered) {
        this.row = row;
        this.rowOrdered = rowOrdered;
        this.keys = keys;
    }

    @Override
    public String get(String key) {
        return row.get(key);
    }

    @Override
    public String get(int index) {
        return rowOrdered.get(index);
    }

    @Override
    public void put(String key, String value) {
        throw new UnsupportedOperationException();
    }
}

class WriteOnlyCSVRecord extends CSVRecord {
    public String toText() {
        StringBuilder builder = new StringBuilder();
        for (String key : keys) {
            String value = row.get(key);
            value = value.replace("\"", "\"\"");
            builder.append(value);
        }
        return builder.toString();
    }

    private HashMap<String, String> row;
    private List<String> keys;

    WriteOnlyCSVRecord(List<String> keys) {
        row = new HashMap<>();
        this.keys = keys;
    }

    public String get(String key) {
        throw new UnsupportedOperationException();
    }

    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void put(String key, String value) {
        row.put(key, value);
    }
}