// Java Concise O(n) Time O(1) Space
//Iterate from left to right, keeping track of the length of the latest segment of ones, including the one to the left of the last zero seen and the one to the right of the last zero seen.

public int findMaxConsecutiveOnes(int[] nums) {
     int maxConsecutive = 0, zeroLeft = 0, zeroRight = 0;
     for (int i=0;i<nums.length;i++) {
         zeroRight++;
         if (nums[i] == 0) {
             zeroLeft = zeroRight;
             zeroRight = 0;
         }
         maxConsecutive = Math.max(maxConsecutive, zeroLeft+zeroRight); 
     }
     return maxConsecutive;
}
  
