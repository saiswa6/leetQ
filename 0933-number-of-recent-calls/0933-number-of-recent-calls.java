class RecentCounter {

    LinkedList<Integer> sildingWindowList;

    public RecentCounter() {
        sildingWindowList = new LinkedList<>(); // No need to main start and end pointer as it is a Queue support remove
                                                // first and addLast
        // If ArrayList is taken, use start and end pointer for range.
    }

    public int ping(int t) {
        sildingWindowList.addLast(t);

        int startRange = t - 3000; // Valid Starting Range

        while (sildingWindowList.getFirst() < startRange) {
            sildingWindowList.removeFirst();
        }
        return sildingWindowList.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */