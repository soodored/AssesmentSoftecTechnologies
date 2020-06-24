package org.example;

import com.sun.security.jgss.GSSUtil;

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

        int maxDepth;
        for(;;) {
            System.out.println("Enter Max depth from 1 to 10: ");
            maxDepth = scanner.nextInt();
            if (maxDepth < 1 || maxDepth > 10) {
                System.out.println("You entered the wrong depth");
            }
            else {
                break;
            }
        }

        WebCrawler webCrawler = new WebCrawler( words, maxDepth);

        LinkedHashMap<String, Map<String, Integer>> map = webCrawler.getResult(url);

        System.out.println(map.toString());
    }
}
