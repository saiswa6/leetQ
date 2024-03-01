class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(candidates); //--> No need of sorting
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
                if(i > index && candidates[i] == candidates[i-1]) {
                    continue;
                }
                sum = sum + candidates[i];
                processed.add(candidates[i]);
                combo(candidates, target, answer, sum, processed,i + 1);
                sum = sum - candidates[i];
                processed.remove(processed.size() - 1);
            }
        }
        
    }
}