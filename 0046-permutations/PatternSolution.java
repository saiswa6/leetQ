class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        permuteCalc(new ArrayList<Integer>(), nums, ans);
        return ans;
    }

    public void permuteCalc(List<Integer> processed, int nums[], List<List<Integer>> ans) {
        if(processed.size() == nums.length) {
            ans.add(new ArrayList<>(processed));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(processed.contains(nums[i])) {
                continue;
            }
            processed.add(nums[i]);
            permuteCalc(processed, nums, ans);
            processed.remove(processed.size() - 1);
        }
    }
}

// Direct Pattern Solution
public List<List<Integer>> permute(int[] nums) {
   List<List<Integer>> list = new ArrayList<>();
   // Arrays.sort(nums); // not necessary
   backtrack(list, new ArrayList<>(), nums);
   return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
   if(tempList.size() == nums.length){
      list.add(new ArrayList<>(tempList));
   } else{
      for(int i = 0; i < nums.length; i++){ 
         if(tempList.contains(nums[i])) continue; // element already exists, skip
         tempList.add(nums[i]);
         backtrack(list, tempList, nums);
         tempList.remove(tempList.size() - 1);
      }
   }
} 


/*
- Good but tmpList.contains(nums[i]) is a O(N) operation
- I suggest adding a hashset for checking if nums[i] exist in the tmp number list.
- In this way, tmpSet.contains(nums[i]) only costs O(1). The algorithm will be faster in the long run.
- The space complexity should remain the same.
*/

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        permuteCalc(new ArrayList<Integer>(), new HashSet<>(),nums, ans);
        return ans;
    }

    public void permuteCalc(List<Integer> processed, Set<Integer> hs,int nums[], List<List<Integer>> ans) {
        if(processed.size() == nums.length) {
            ans.add(new ArrayList<>(processed));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(hs.contains(nums[i])) {
                continue;
            }
            processed.add(nums[i]);
            hs.add(nums[i]);
            permuteCalc(processed, hs, nums, ans);
            int lastElement = processed.get(processed.size() - 1);
            hs.remove(lastElement);
            processed.remove(processed.size() - 1);
        }
    }
}
