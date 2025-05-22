import java.util.Set;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String seedUrl = "https://www.wikipedia.org/";
        int maxThreads = 3;

        // Shared thread-safe data structures
        BlockingQueue<String> urlQueue = new LinkedBlockingQueue<>();
        Set<String> visitedUrls = ConcurrentHashMap.newKeySet();

        Crawler crawler = new Crawler(urlQueue, visitedUrls, maxThreads, seedUrl);
        crawler.crawl();
    }
}
