class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> answer = new ArrayList<>();
        int candidates [] = {1,2,3,4,5,6,7,8,9};
        //Arrays.sort(candidates); 
        combo(candidates, n, answer, 0, new ArrayList<Integer>(), 0, k);
        return answer;
    }

    void combo(int[] candidates, int target,List<List<Integer>> answer, int sum , List<Integer> processed, int index, int digits) {
        if(sum > target){
            return;
        } else if(sum == target & processed.size() == digits) {
            answer.add(new ArrayList<>(processed));
            return;
        } else {
            for(int i = index; i < candidates.length; i++) {
                if(target-sum < candidates[i]) {
                    break;
                }
                sum = sum + candidates[i];
                processed.add(candidates[i]);
                combo(candidates, target, answer, sum, processed,i + 1, digits); //2nd diff from comb sum1
                sum = sum - candidates[i];
                processed.remove(processed.size() - 1);
            }
        }
        
    }
}