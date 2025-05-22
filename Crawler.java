import java.util.Set;
import java.util.concurrent.*;

public class Crawler {
    private final String seedUrl;
    private final BlockingQueue<String> urlQueue;
    private final Set<String> visitedUrls;
    private Integer noOfThreads = 5;

    public Crawler(BlockingQueue<String> urlQueue , Set<String> visitedUrls, Integer noOfThreads, String seedUrl){
        this.seedUrl = seedUrl;
        this.urlQueue = urlQueue;
        this.visitedUrls = visitedUrls;
        this.noOfThreads = noOfThreads;
    }

    public void crawl() throws InterruptedException {
        System.out.println("Crawl Started");
        ExecutorService executor = new ThreadPoolExecutor(noOfThreads, noOfThreads+2, 2, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        // Seed URL
        urlQueue.put(seedUrl) ;
        // Submit initial crawler workers
        for (int i = 0; i < 5; i++) {
            executor.submit(new CrawlerWorker(urlQueue, visitedUrls));
        }

        // Optional: graceful shutdown after some time
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown initiated...");
            executor.shutdownNow();
        }));

        // Wait for termination (optional timeout)
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
            System.out.println("Here are the final crawler urls: ");
            for(String url: visitedUrls){
                System.out.println(url);
            }

            for(String url: urlQueue){
                System.out.println(url);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
