import org.example.PageConnection;
import org.example.WebCrawler;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


@RunWith(MockitoJUnitRunner.class)
public class WebCrawlerTest{

    private final List<String> WORDS = List.of("one", "two", "three");
    private final int MAX_DEPTH = 2;
    private final String URL = "file:///Users/stanislavaleshkevich/Desktop/sofTecTestProject/src/test/resources/testhtml/Test.html";

    BufferedReader fileReader = new BufferedReader(new FileReader(new File("/Users/stanislavaleshkevich/Desktop/sofTecTestProject/src/test/resources/testhtml/Test.html")));
    String page = fileReader.lines().collect(Collectors.joining());
    private Document document = Jsoup.parse(page);

    private final WebCrawler webCrawler = new WebCrawler(WORDS, MAX_DEPTH, new PageConnection());;

    public WebCrawlerTest() throws IOException {
    }


    @Before
    public void init() {
    }

    @Test
    public void testCrawl(){
        LinkedHashMap<String, Map<String, Integer>> map = webCrawler.crawl(URL);

        System.out.println(map.toString());
    }
}
