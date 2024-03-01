class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        combosoln(n, k, 1, new ArrayList<>(), answer);
        return answer;
    }

    void combosoln(int n, int k, int start, List<Integer> processed, List<List<Integer>> answer){
        if(processed.size() == k) {
            answer.add(new ArrayList<>(processed));
            return;
        }

        for(int i = start; i <= n; i++) {
            processed.add(i);
            combosoln(n, k, i + 1, processed, answer);
            processed.remove(processed.size() - 1);
        }
    }
}