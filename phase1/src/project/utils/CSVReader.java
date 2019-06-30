package project.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {
    private String source;
    private Pattern pattern;
    private List<String> keys;

    public CSVReader(String source) {
        this.source = source;
        String item = "\"([^\"]|\"\")*\"|[^,\"]*";
        pattern = Pattern.compile(item);
    }

    private String parseItem() {
        Matcher m = pattern.matcher(source);
        if (m.find()) {
            String result = m.group();
            source = source.substring(result.length());
            result = result.replace("\"\"", "\"");
            return result;
        }
        throw new RuntimeException("Parse failed");
    }

    private List<String> parseLine() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            result.add(parseItem());
        }
        return result;
    }

    private void parseHeader() {
        int index = 0;
        while (index < source.length() && source.charAt(index) != '\n') {
            index++;
        }
        String header = source.substring(0, index);
        source = source.substring(index + 1);
        keys = Arrays.asList(header.split(","));
    }

    CSVTable parse() {
        parseHeader();
        CSVTable table = new CSVTable(keys);
        while (!source.isEmpty()) {
            List<String> line = parseLine();
            HashMap<String, String> map = new HashMap<>();
            for (int i = 0; i < keys.size(); i++) {
                map.put(keys.get(i), line.get(i));
            }
            CSVRecord record = new ReadOnlyCSVRecord(keys, map, line);
            table.addRecord(record);
        }
        return table;
    }
}
