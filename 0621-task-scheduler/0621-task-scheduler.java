class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for(Character c : tasks){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a,b) -> 
            b - a
        );

        maxHeap.addAll(map.values());

        Queue<Pair<Integer, Integer>> q = new ArrayDeque();
        int time = 0;

        while (!maxHeap.isEmpty() || !q.isEmpty()) {
            time++;

            if(!maxHeap.isEmpty()){
                Integer temp = maxHeap.poll();
                if(temp - 1 > 0) {
                    
                    q.add(new Pair(temp -1 , time + n));
                }

            }
            if(!q.isEmpty() && q.peek().getValue() == time){
                maxHeap.add(q.peek().getKey());
                q.poll();
            }
        }
        
        return time;


    }
}