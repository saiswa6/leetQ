class Solution {
    public int pivotIndex(int[] nums) {
        int length = nums.length;
        int prefixSum [] = new int[length];
        int suffixSum[] = new int [length];

        prefixSum[0] = nums[0];
        for(int i = 1; i < length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        suffixSum[length - 1] = nums[length - 1];
        for(int i = length - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + nums[i];
        }

        for(int i = 0; i < length; i++) {
            if(prefixSum[i] == suffixSum[i]) {
                return i;
            }
        }
        return -1; 
    }
}