class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i != 0) {
                list1.add(nums[i]);
            }
            if (i != n - 1) {
                list2.add(nums[i]);
            }
        }

        return Math.max(robSolver(list1), robSolver(list2));
    }

    private int robSolver(List<Integer> list) {
        int len = list.size();

        int prev = list.get(0);
        int prev2 = 0;
        int current = 0;

        for (int i = 1; i < len; i++) {
            int take = list.get(i);

            take = take + prev2;
            int nonTake = 0 + prev;
            current = Math.max(take, nonTake);
            prev2 = prev;
            prev = current;
        }

        return prev;
    }
}