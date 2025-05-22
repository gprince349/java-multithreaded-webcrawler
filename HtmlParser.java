import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HtmlParser {

    public static List<String> extractLinks(String url){
        Set<String> urls = new HashSet<>(Collections.singleton(url));
        try {
            Document doc = Jsoup.connect(url).get();
            Elements anchorTags = doc.select("a[href]");
            for (Element element : anchorTags) {
                String absHref = element.absUrl("href");
                if (!absHref.isEmpty()) {
                    urls.add(absHref);
                }
            }
        } catch (IOException e) {
            System.out.println("Html Parser failed due to exception");
            throw new RuntimeException(e);
        }

        return urls.stream().toList();
    }
}
