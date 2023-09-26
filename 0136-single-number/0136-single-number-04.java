/*
Approach 4: Bit Manipulation
Concept

-If we take XOR of zero and some bit, it will return that bit
  a⊕0=a
-If we take XOR of two same bits, it will return 0
  a⊕a=0
-a⊕b⊕a=(a⊕a)⊕b=0⊕b=b
So we can XOR all bits together to find the unique number.

Complexity Analysis

Time complexity : O(n). We only iterate through nums, so the time complexity is the number of elements in nums
Space complexity : O(1).
    */
class Solution {
  public int singleNumber(int[] nums) {
    int a = 0;
    for (int i : nums) {
      a ^= i;
    }
    return a;
  }
}

//MY SOL'n

class Solution {
    public int singleNumber(int[] nums) {
        int num = nums[0];
        for(int i = 1; i < nums.length; i++)
        {
            num = num ^ nums[i];
        }
        return num;
    }
}
