import org.example.PageConnection;
import org.example.WebCrawler;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;


@RunWith(MockitoJUnitRunner.class)
public class WebCrawlerTest{

    private final List<String> WORDS = List.of("one", "two", "three");
    private final int MAX_DEPTH = 2;
    private final String URL = "file:///Users/stanislavaleshkevich/Desktop/sofTecTestProject/src/test/resources/testhtml/Test.html";

    private final WebCrawler webCrawler = new WebCrawler(WORDS, MAX_DEPTH, new PageConnection());;

    @Test
    public void testCrawl(){
        LinkedHashMap<String, Map<String, Integer>> map = webCrawler.crawl(URL);

        // There are assert
        System.out.println(map.toString());
    }
}
