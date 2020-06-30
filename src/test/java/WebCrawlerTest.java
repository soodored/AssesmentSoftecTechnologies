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
    private final String URL_TEST = "file:///Users/stanislavaleshkevich/Desktop/sofTecTestProject/src/test/resources/testhtml/Test.html";
    private final String URL_TEST2 = "file:/Users/stanislavaleshkevich/Desktop/sofTecTestProject/src/test/resources/testhtml/Test2.html";

    private final WebCrawler webCrawler = new WebCrawler(WORDS, MAX_DEPTH);;

    @Test
    public void testCrawl(){
        LinkedHashMap<String, Map<String, Integer>> map = webCrawler.crawl(URL_TEST);

        Assert.assertEquals("Search depth",2, map.size());

        Map<String, Integer> innerMap = map.get(URL_TEST);

        Assert.assertEquals("3 words to be found",3, innerMap.size());
        Assert.assertEquals("The word \"One\" should be repeated 5 times", Integer.valueOf(5), innerMap.get(WORDS.get(0)));
        Assert.assertEquals("The word \"Two\" should be repeated 2 times",Integer.valueOf(2), innerMap.get(WORDS.get(1)));
        Assert.assertEquals("The word \"Three\" should be repeated 3 times",Integer.valueOf(3), innerMap.get(WORDS.get(2)));

        innerMap = map.get(URL_TEST2);
        Assert.assertEquals("3 words to be found",3, innerMap.size());
        Assert.assertEquals("The word \"One\" should be repeated 3 times",Integer.valueOf(3), innerMap.get(WORDS.get(0)));
        Assert.assertEquals("The word \"Two\" should be repeated 4 times",Integer.valueOf(4), innerMap.get(WORDS.get(1)));
        Assert.assertEquals("The word \"Three\" should be repeated 5 times",Integer.valueOf(5), innerMap.get(WORDS.get(2)));
    }

    @Test(expected = RuntimeException.class)
    public void testCrawlError(){
        LinkedHashMap<String, Map<String, Integer>> map = webCrawler.crawl("http://wrong123.com");
    }
}



