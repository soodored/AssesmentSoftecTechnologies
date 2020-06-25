package org.example;

import java.io.FileWriter;
import java.util.Map;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;


public class CSVFile{

    public static void createCSVFile(Map<String, Map<String, Integer>> map) throws Exception {
        String csv = "data.csv";
        FileWriter out = new FileWriter(csv);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT)) {
            for (String key: map.keySet()) {
                printer.print("WebSite: " + key);
                printer.println();
                for (Map.Entry<String, Integer> entries : map.get(key).entrySet()){
                    printer.print(entries.getKey().trim() + " : " + entries.getValue().toString().trim());
                    printer.println();
                }
            }
        }
    }
}
