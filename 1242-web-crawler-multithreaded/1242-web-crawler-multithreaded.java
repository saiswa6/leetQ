/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 * public List<String> getUrls(String url) {}
 * }
 */
class Solution {
    private Set<String> set;
    private String host;
    private HtmlParser htmlParser;

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        set = ConcurrentHashMap.newKeySet();
        host = getHost(startUrl);
        this.htmlParser = htmlParser;
        crawlR(startUrl);
        return new ArrayList<>(set);
    }

    private void crawlR(String startUrl) {
        if (set.contains(startUrl) || !getHost(startUrl).equals(host)) return;
        set.add(startUrl);
        htmlParser.getUrls(startUrl).parallelStream().forEach(this::crawlR);
    }

    private static String getHost(String url) {
        return url.split("/")[2];
    }
}