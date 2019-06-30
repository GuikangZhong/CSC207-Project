package project.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class CSVRecord implements Iterable<CSVItem> {
    public abstract String get(String key);

    public abstract String get(int index);

    public abstract void put(String key, String value);

    public abstract CSVRecord put(String value);
}

class ReadOnlyCSVRecord extends CSVRecord {
    private HashMap<String, String> row;
    private List<String> rowOrdered;
    private List<String> keys;

    ReadOnlyCSVRecord(List<String> keys, HashMap<String, String> row, List<String> rowOrdered) {
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

    @Override
    public CSVRecord put(String value) {
        throw new UnsupportedOperationException();
    }

    private class CSVIterator implements Iterator<CSVItem> {
        private int index = 0;
        ReadOnlyCSVRecord record;

        CSVIterator(ReadOnlyCSVRecord record) {
            this.record = record;
        }

        @Override
        public boolean hasNext() {
            return index == record.keys.size() - 1;
        }

        @Override
        public CSVItem next() {
            String key = record.keys.get(index);
            CSVItem item = new CSVItem(key, record.row.get(key));
            index++;
            return item;
        }
    }

    @Override
    public Iterator<CSVItem> iterator() {
        return new CSVIterator(this);
    }
}

class WriteOnlyCSVRecord extends CSVRecord {
    String toText() {
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
    private int index = 0;

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
    public CSVRecord put(String value) {
        row.put(keys.get(index), value);
        index++;
        return this;
    }

    @Override
    public void put(String key, String value) {
        row.put(key, value);
    }

    @Override
    public Iterator<CSVItem> iterator() {
        throw new UnsupportedOperationException();
    }
}