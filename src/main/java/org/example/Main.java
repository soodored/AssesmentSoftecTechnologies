package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter website: ");
        String url = scanner.nextLine();
        System.out.print("Enter comma separated words: ");
        String line = scanner.nextLine();
        List<String> words = Stream.of(line.split(",")).collect(Collectors.toList());

        System.out.println("Enter max depth from 1 to 10");
        int maxDepth = readMaxDepth(scanner);

        WebCrawler webCrawler = new WebCrawler(words, maxDepth);

        LinkedHashMap<String, Map<String, Integer>> map = webCrawler.crawl(url);

         CSVFile.createCSVFile(map);
    }

    private static int readMaxDepth(Scanner scanner) {
        int maxDepth = scanner.nextInt();
        if (maxDepth < 1 || maxDepth > 10){
            System.out.println("You entered the wrong depth");
            maxDepth = readMaxDepth(scanner);
        }
        return maxDepth;
    }
}
