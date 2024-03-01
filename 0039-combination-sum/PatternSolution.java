class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
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

// DIrect copy pattern 
public List<List<Integer>> combinationSum(int[] nums, int target) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, target, 0);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
    if(remain < 0) return;
    else if(remain == 0) list.add(new ArrayList<>(tempList));
    else{ 
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
            tempList.remove(tempList.size() - 1);
        }
    }
}
