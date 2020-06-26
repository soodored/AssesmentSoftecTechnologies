package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WebCrawler {

    private final List<String> WORDS;
    private final int MAX_DEPTH;

    private final HashSet<String> links = new HashSet<>();
    private final LinkedHashMap<String, Map<String, Integer>> result = new LinkedHashMap<>();
    private int innerCount = 0;

    public WebCrawler(List<String> words, int maxDepth){
        this.WORDS = words;
        this.MAX_DEPTH = maxDepth;
    }

    public LinkedHashMap<String, Map<String, Integer>> crawl(String url) {
        processPage(url);
        return result;
    }

    private void processPage(String url) {
        if (!links.contains(url) && innerCount != MAX_DEPTH) {
            innerCount++;
            links.add(url);

            Document document = getPageByUrl(url);

            result.put(url, searchOnPageAllWords(document));

            searchOnInnerPage(document);
        }
    }

    private Document getPageByUrl(String url){

        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    private Map<String, Integer> searchOnPageAllWords(Document document){
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String word : WORDS) {
            int count = searchOnPageByWord(document.toString(), word.trim());
            map.put(word, count);
        }
        return map;
    }

    private int searchOnPageByWord(String page, String word) {
        int count = 0;

        Pattern pattern = Pattern.compile("[ >,]" + word + "[ <,]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(page);

        while (matcher.find()) {
            count++;
        }

        return count;
    }

    private void searchOnInnerPage(Document document){
        Elements elements = document.select("a[href]");

        for (Element elem : elements) {
            processPage(elem.attr("abs:href"));
        }
    }
}
