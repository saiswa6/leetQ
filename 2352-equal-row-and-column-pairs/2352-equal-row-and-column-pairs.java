class Solution {
    public int equalPairs(int[][] grid) {
        Map<List<Integer>, Integer> rowMap = new HashMap<>();
        int answer = 0;

        int length = grid.length;
        for(int row = 0 ; row < length ; row++){
            List<Integer> list = new ArrayList<>(length);
            for(int col = 0; col < length ; col++) {
                list.add(grid[row][col]);
            }
            rowMap.put(list, rowMap.getOrDefault(list, 0) + 1);
        }

        for(int row = 0 ; row < length ; row++){
            List<Integer> list = new ArrayList<>(length);
            for(int col = 0; col < length ; col++) {
                list.add(grid[col][row]);
            }
            int count = rowMap.getOrDefault(list, 0);
            if(count > 0) {
                //rowMap.put(list, count - 1);
                answer = answer + count;
            } 
        }
        return answer;
    }
}