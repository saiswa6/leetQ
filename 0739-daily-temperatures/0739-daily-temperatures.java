class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        int length = temperatures.length;
        int result[] = new int[length];

        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && stack.peek().getKey() < temperatures[i]) {
                result[stack.peek().getValue()] = i - stack.peek().getValue();
                stack.pop();
            }

            stack.push(new Pair<>(temperatures[i], i));
        }

        return result;
    }
}