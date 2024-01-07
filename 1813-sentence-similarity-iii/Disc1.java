/*Method 1: Scan the shorter sentence compare with the longer.

1. Split the two sentences into words;
2. Scan the shorter one from left and compare word by word with the longer; Whenever the words from the 2 sentences are not equal, compare the tails of the 2 sentences word by word till the end or they do not match;
3. Confirm if the pointer reaches the end of shorter sentence. If yes, return true; otherwise, return false.
  */

    public boolean areSentencesSimilar(String s1, String s2) {
        String[] words1 = s1.split(" "), words2 = s2.split(" ");
        int i = 0, n1 = words1.length, n2 = words2.length;
        if (n1 > n2) {
            return areSentencesSimilar(s2, s1);
        }
        while (i < n1 && words1[i].equals(words2[i])) {
            ++i;
        }
        while (i < n1 && words1[i].equals(words2[n2 - n1 + i])) {
            ++i;
        }
        return i == n1;
    }

/*
Method 2: deque:
1. Split the two sentences and put into Deques;
2. Keep omparing the first words of the 2 Deques, if same, poll them out; Whenever they are not equal, then compare the tails of the 2 sentences word by word till the end or they do not match;
3. Check if either of the 2 Deques is empty, If yes, return true; otherwise, return false.

Analysis:
Time & space: O(n1 + n2), where n1 = sentence1.length(), n2 = sentence2.length().
*/

    public boolean areSentencesSimilar(String s1, String s2) {
        Deque<String> dq1 = new ArrayDeque<>(Arrays.asList(s1.split(" ")));
        Deque<String> dq2 = new ArrayDeque<>(Arrays.asList(s2.split(" ")));
        while (!dq1.isEmpty() && !dq2.isEmpty() && dq1.peek().equals(dq2.peek())) {
            dq1.poll();
            dq2.poll();
        }
        while (!dq1.isEmpty() && !dq2.isEmpty() && dq1.peekLast().equals(dq2.peekLast())) {
            dq1.pollLast();
            dq2.pollLast();
        }
        return dq1.isEmpty() || dq2.isEmpty();
    }
