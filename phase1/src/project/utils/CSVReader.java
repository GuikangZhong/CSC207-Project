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
    private int index = 0;
    private int lineNo = 0, colNo = 0;

    public CSVReader(String source) {
        this.source = source.replace("\r\n", "\n").replace("\r", "\n");
        String item = "\"([^\"]|\"\")*\"|[^,\"\\n]+";
        pattern = Pattern.compile(item);
    }

    private String parseItem() {
        Matcher m = pattern.matcher(source);
        if (m.find(index)) {
            String match = m.group();
            advance(match.length());
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < match.length(); i++) {
                boolean flag = true;
                if (match.charAt(i) == '\"') {
                    if (i + 1 >= match.length() || match.charAt(i + 1) != '\"')
                        flag = false;
                }
                if (flag)
                    s.append(match.charAt(i));
            }
            String result = s.toString();

            result = result.replace("\"\"", "\"").trim();
            return result;
        }
        throw new RuntimeException("Parse failed at " + lineNo + ":" + colNo);
    }

    private void advance1() {
        if (source.charAt(index) == '\n') {
            lineNo += 1;
            colNo = 1;
        } else {
            colNo++;
        }
        index++;
    }

    private void advance(int k) {
        for (int i = 0; i < k; i++)
            advance1();
    }

    private void skipNewLine() {
        while (index < source.length() && source.charAt(index) == '\n') {
           advance1();
        }
    }

    private List<String> parseLine() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            result.add(parseItem());
            if (i != keys.size() - 1) {
                if (source.charAt(index) != ',') {
                    throw new RuntimeException("',' expected at " + lineNo + ":" + colNo);
                } else {
                    advance(1);
                }
            }
        }
        skipNewLine();
        return result;
    }

    private void parseHeader() {
        int i = 0;
        while (i < source.length() && source.charAt(i) != '\n' && source.charAt(i) != '\r') {
            i++;
        }
        String header = source.substring(0, i);
        if (index < source.length())
            source = source.substring(i + 1);
        keys = Arrays.asList(header.split(","));
    }

    public CSVTable parse() {
        parseHeader();
        CSVTable table = new CSVTable(keys);
        System.out.println(keys);
        while (index < source.length()) {

            List<String> line = parseLine();
            //   System.out.println(line);
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
