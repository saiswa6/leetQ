class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        long answer = 0;
        PriorityQueue<int []> pq = new PriorityQueue<>((p1, p2) -> {
            if(p1[0] == p2[0]) {
                return p1[1] - p2[1];
            }
            return p1[0] - p2[0];
        });
        
        for(int i = 0; i < candidates; i++) {
            pq.offer(new int[] {costs[i], 0});
        }

        for(int i = Math.max(candidates, costs.length - candidates); i < costs.length; i++) {
            pq.offer(new int[] {costs[i], 1});
        }

        int nextHead = candidates;
        int nextTail = costs.length - candidates - 1;

        for(int i = 0; i < k ; i++) {
            int[] currWorker = pq.poll();
            int curCost = currWorker[0];
            int curSectionId = currWorker[1];

            answer += curCost;

            if(nextHead <= nextTail) {
                if(curSectionId == 0) {
                    pq.offer(new int[] {costs[nextHead], 0});
                    nextHead++;
                } else {
                    pq.offer(new int[] {costs[nextHead], 1});
                    nextTail--;
                }
            }
        }
        return answer;
    }
}