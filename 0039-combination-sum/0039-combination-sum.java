class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();

        combo(candidates, target, answer,0, new ArrayList<Integer>(), 0);
        return answer;
    }

    void combo(int[] candidates, int target,List<List<Integer>> answer, int sum , List<Integer> processed, int index) {
        if(sum > target){
            return;
        } else if(sum == target) {
            answer.add(new ArrayList<>(processed));
            return;
        } else {
            for(int i = index; i < candidates.length; i++) {
                sum = sum + candidates[i];
                processed.add(candidates[i]);
                combo(candidates, target, answer, sum, processed,i);
                sum = sum - candidates[i];
                processed.remove(processed.size() - 1);
            }
        }
        
    }
}