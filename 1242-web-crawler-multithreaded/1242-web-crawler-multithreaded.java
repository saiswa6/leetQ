/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 * public List<String> getUrls(String url) {}
 * }
 */
class Solution {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        AsyncCrawler crawler = new AsyncCrawler(htmlParser);
        List<String> result = crawler.crawl(startUrl);
        return result;
    }
}

class AsyncCrawler {
    private final HtmlParser htmlParser;
    private final ExecutorService threadPool = Executors.newCachedThreadPool(r -> {
        Thread t = new Thread(r);
        // Leetcode doesn't allow executor.shutdown().
        // Use daemon threads so the program can exit.
        t.setDaemon(true);
        return t;
    });
    private final Set<String> visited = ConcurrentHashMap.newKeySet();

    AsyncCrawler(HtmlParser htmlParser) {
        this.htmlParser = htmlParser;
    }

    public List<String> crawl(String startUrl) {
        String host = getHostname(startUrl);
        // System.out.println("Crawl asynchronously. Host: " + host);
        return new ArrayList<>(async(startUrl, host).join());
    }

    CompletableFuture<Set<String>> async(String url, String host) {
        // System.out.println("Async crawling. Url: " + url);
        visited.add(url);

        return CompletableFuture
                .supplyAsync(() -> htmlParser.getUrls(url), threadPool)
                .thenCompose(subUrls -> {
                    Set<String> initData = ConcurrentHashMap.newKeySet();
                    initData.add(url);

                    CompletableFuture<Set<String>> result = CompletableFuture.completedFuture(initData);

                    for (String subUrl : subUrls) {
                        // System.out.println("Parse subUrl: " + subUrl);
                        if (visited.contains(subUrl) || !isSameHostname(subUrl, host)) {
                            continue;
                        }

                        result = result.thenCombine(async(subUrl, host), (prev, next) -> {
                            prev.addAll(next);
                            return prev;
                        });
                    }

                    return result;
                });
    }

    String getHostname(String url) {
        int idx = url.indexOf('/', 7);
        return (idx != -1) ? url.substring(0, idx) : url;
    }

    boolean isSameHostname(String url, String hostname) {
        if (!url.startsWith(hostname)) {
            return false;
        }
        return url.length() == hostname.length() || url.charAt(hostname.length()) == '/';
    }
}