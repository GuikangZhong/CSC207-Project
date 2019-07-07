package project.utils;

public class CSVItem {
    private String key;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    private String value;

    CSVItem(String key, String value){
        this.key = key;
        this.value = value;
    }
}
