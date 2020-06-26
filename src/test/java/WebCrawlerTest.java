import org.example.Main;
import org.example.WebCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Collector;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest(WebCrawler.class)
public class WebCrawlerTest {

    private final List<String> WORDS = Collections.singletonList("one, two, three");
    private final int MAX_DEPTH = 2;
    private final String URL = "http://test.com";

    @Test
    public void testCrawl() throws Exception {
        BufferedReader fileReader = new BufferedReader(new FileReader(new File("/Users/stanislavaleshkevich/Desktop/sofTecTestProject/src/test/resources/testhtml/Test.html")));
        String page = fileReader.lines().collect(Collectors.joining());
        Document document = Jsoup.parse(page);

        WebCrawler mockWebCrawler = PowerMockito.spy(new WebCrawler(WORDS, MAX_DEPTH));
        when(mockWebCrawler, method(WebCrawler.class, "getPageByUrl", String.class)).withArguments(URL).thenReturn(document);

        LinkedHashMap<String, Map<String, Integer>> map = mockWebCrawler.crawl(URL);
    }
}
