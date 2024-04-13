/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */
class Solution {
    private Set<String> visited = ConcurrentHashMap.newKeySet();
    private String domain;
    private HtmlParser htmlParser;
    private ExecutorService executor = Executors.newFixedThreadPool(6);
    private AtomicInteger activeTasks = new AtomicInteger(0);

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        this.htmlParser = htmlParser;
        this.domain = startUrl.split("/")[2];
        visited.add(startUrl);
        activeTasks.set(1);
        executor.execute(new Task(startUrl));

        while(activeTasks.get() > 0) {
            try{
                Thread.sleep(80);
            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        executor.shutdown();
        return new ArrayList<>(visited);
    }

    private class Task implements Runnable {
        private String url;
        public Task(String startUrl) {
            this.url = startUrl;
        }

        public void run() {
            for(String link : htmlParser.getUrls(url)) {
                if(link.split("/")[2].equals(domain) && visited.add(link)) {
                    activeTasks.incrementAndGet();
                    executor.execute(new Task(link));
                }
            }
            activeTasks.decrementAndGet();
        }
    }

    
}