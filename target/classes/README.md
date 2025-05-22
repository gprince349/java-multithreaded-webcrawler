# Multithreaded Java Web Crawler

A high-performance, concurrent web crawler built in Java, designed to practice and demonstrate advanced multithreading concepts. This project utilizes core Java concurrency tools like `ExecutorService`, `BlockingQueue`, and `ConcurrentHashMap` to efficiently crawl and parse web pages in parallel.

## 📄 **Description**

**Multithreaded Java Web Crawler** is a multi-threaded application that simulates a web crawler. It fetches and parses HTML pages from the web concurrently, respecting various policies such as rate limiting, URL deduplication, and optional depth-limiting.

The main objective is to **practice multithreading** and **concurrency** through real-world challenges while making sure the application is scalable and thread-safe.

## 🛠️ **Features**

- **Concurrent Crawling**: Uses `ExecutorService` for parallel processing.
- **Thread-Safety**: Implements `BlockingQueue` for URL queue management and `ConcurrentHashMap` for tracking visited URLs.
- **HTML Parsing**: Uses **JSoup** to parse HTML content and extract links.
- **Rate Limiting**: Implements rate-limiting logic using `Semaphore` or **Guava RateLimiter**.
- **URL Normalization**: Utilizes custom utilities to normalize and deduplicate URLs.
- **Graceful Shutdown**: Handles thread pool shutdown with proper error handling.

## ⚙️ **Setup & Installation**

### Requirements
- **Java 11+**
- **Maven** (for dependency management)

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/<your-username>/multithreaded-webcrawler.git
   cd multithreaded-webcrawler
   mvn clean install
   java -jar target/multithreaded-webcrawler.jar
1. **Example (Running with Custom Parameters):**:
   ```bash
   java -cp target/multithreaded-webcrawler.jar Main https://example.com --maxDepth 5 --threads 10 --rateLimit 2


#Flow Diagram:
```bash
+------------------+
|     Main.java    |
+------------------+
         |
         | Initializes
         v
+------------------+     creates     +------------------------+
|   Crawler.java   |---------------->| ExecutorService (Pool) |
+------------------+                 +------------------------+
         |
         | Injects shared components
         v
+--------------------------+
|   Shared Resources:      |
| - BlockingQueue<String> |<-----------------------------+
| - ConcurrentHashMap      |                              |
|   (visited URLs)         |                              |
+--------------------------+                              |
         |                                               (new URLs)
         |                                                |
         | Dequeues                                       |
         v                                                |
+---------------------------+     fetch/parse     +------------------+
|  CrawlerWorker (Runnable) |-------------------->|  HtmlParser.java |
+---------------------------+                     +------------------+
         |
         | Extracted URLs (cleaned by UrlUtils)
         v
+------------------+
|  UrlUtils.java   |
+------------------+
         |
         | Enqueues new unvisited URLs
         v
+--------------------------+
|   BlockingQueue<String>  |
+--------------------------+

