class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        combosoln(n, k, 1, new ArrayList<>(), answer);
        return answer;
    }

    void combosoln(int n, int k, int start, List<Integer> processed, List<List<Integer>> answer){
        if(k == 0) {
            answer.add(new ArrayList<>(processed));
            return;
        }

        for(int i = start; i <= n; i++) { // n-k+1 is used instaed n , T. C ms reduces but check why ?
            processed.add(i);
            combosoln(n, k - 1, i + 1, processed, answer);
            processed.remove(processed.size() - 1);
        }
    }
}
