class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(new ArrayList<>(), nums, new boolean[nums.length], answer);
        return answer;
    }

    void backtrack(List<Integer> processedList, int [] nums, boolean [] used, List<List<Integer>> answer) {
        if(processedList.size() == nums.length) {
            answer.add(new ArrayList<>(processedList));
            return;
        }

        for(int i=0; i < nums.length; i++) {
            if(used[i] || i>0 && nums[i] == nums[i-1] && !used[i-1]) {
                continue;
            }
            used[i] = true;
            processedList.add(nums[i]);
            backtrack(processedList, nums, used, answer);
            used[i] = false;
            processedList.remove(processedList.size() - 1);
        }
    }
}



//Direct copy
public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
    if(tempList.size() == nums.length){
        list.add(new ArrayList<>(tempList));
    } else{
        for(int i = 0; i < nums.length; i++){
            if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
            used[i] = true; 
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, used);
            used[i] = false; 
            tempList.remove(tempList.size() - 1);
        }
    }
}
