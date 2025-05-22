import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class CrawlerWorker implements Runnable{
    private final BlockingQueue<String> urlQueue;
    private final Set<String> visitedUrls;

    public CrawlerWorker(BlockingQueue<String> urlQueue, Set<String> visitedUrls) {
        this.urlQueue = urlQueue;
        this.visitedUrls = visitedUrls;
    }

    @Override
    public void run() {
        try {
            System.out.println("Crawler Worker Started");

            String url = urlQueue.take();
            System.out.println("pulled " + url);
            if(!visitedUrls.contains(url)){
                List<String> refUrls = HtmlParser.extractLinks(url);
                System.out.println("refurls" + refUrls);
                visitedUrls.add(url);
                // new urls in urlQueue if not visited already
                for(String refUrl: refUrls){
                    if(!visitedUrls.contains(refUrl)){
                        urlQueue.put(refUrl);
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Crawler failed with exception" +  e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
