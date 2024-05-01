class Solution {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int prefix [] = new int[length];
        int suffix [] = new int[length];
        int result [] = new int[length];

        int prefixProduct = 1;
        prefix[0] = prefixProduct;
        for(int i = 1; i < length; i++) {
            prefix[i] = prefixProduct * nums[i - 1];
            prefixProduct = prefix[i];
        }
        
        int suffixProduct = 1;
        suffix[length - 1] = suffixProduct;
        for(int i = length - 2; i >= 0; i--) {
            suffix[i] = suffixProduct * nums[i + 1];
            suffixProduct = suffix[i];
        } 

        for(int k = 0; k < length ; k++) {
            result[k] = prefix[k] * suffix[k];
        }

        return result;
    }
}