class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        int start = 0, end = nums.length - 1;
        int arr [] = new int[2];
        int nums2 [] = nums.clone();
        Arrays.sort(nums2);
        int value1 =0, value2 =0;
        while(start < end)
        {
            if(nums2[start] + nums2[end] == target)
            {
                
                value1 = nums2[start];
                value2 = nums2[end];
                break;
            }
            else if (nums2[start] + nums2[end] > target)
            {
                end--;
            }
            else {
                start++;
            }
        }
        int j = 0;
        for(int i = 0;i < nums.length;i++)
        {
            if(nums[i] == value1 || nums[i] == value2)
            {
                arr[j] = i; 
                j++;
            }
        }
        return arr;
    }
}