class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>(Collections.nCopies(k, 0));
        backtrack(n, k, 1, current, result);
        return result;
    }

    private void backtrack(int n, int k, int next, List<Integer> current, List<List<Integer>> result) {
        //Order of the below two if blocks matters
        if (n==0 && k==0) { //Found a solution
            result.add(new ArrayList<>(current));
            return;
        }   
        if (n<0 || k==0) // Not a solution
            return;

        for (int i=next; i<=9; i++) {
            current.set(current.size()-k, i);
            backtrack(n-i, k-1, i+1, current, result);
        }
    }
}
