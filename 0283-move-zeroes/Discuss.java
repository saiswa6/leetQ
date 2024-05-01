// SNow ball method - Link : https://leetcode.com/problems/move-zeroes/solutions/172432/the-easiest-but-unusual-snowball-java-solution-beats-100-o-n-clear-explanation/?envType=study-plan-v2&envId=leetcode-75
class Solution {
     public void moveZeroes(int[] nums) {
        int snowBallSize = 0; 
        for (int i=0;i<nums.length;i++){
	        if (nums[i]==0){
                snowBallSize++; 
            }
            else if (snowBallSize > 0) {
	            int t = nums[i];
	            nums[i]=0;
	            nums[i-snowBallSize]=t;
            }
        }
    }
}
