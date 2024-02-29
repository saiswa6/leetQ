/*Approach 3: Lexicographic (Binary Sorted) Subsets

Intuition
The idea is that we map each subset to a bitmask of length n, where 1 on the ith position in bitmask means the presence of nums[i] in the subset, and 0 means its absence.

For instance, the bitmask 0..00 (all zeros) corresponds to an empty subset, and the bitmask 1..11 (all ones) corresponds to the entire input array nums.
Hence to solve the initial problem, we just need to generate n bitmasks from 0..00 to 1..11.

Algorithm
1. Generate all possible binary bitmasks of length n.
2. Map a subset to each bitmask:
    1 on the ith position in bitmask means the presence of nums[i] in the subset, and 0 means its absence.
3. Return output list.

*/

 public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < 1 << nums.length; ++i) {
            List<Integer> subSet = new ArrayList<>();

            for (int b = 0; b < nums.length; ++b) {
                if (((i >> b) & 1) == 1) {
                    subSet.add(nums[b]);
                }
            }

            result.add(subSet);
        }

        return result;
    }
}

Right Shift (>>)
b = a >> n ==> b = a/2^n
256 >> 4 == 256/2^4 = 16

Leftt Shift (<<)
b = a << n ==> b = a*2^n
20 << 3 == 20*2^3 = 160

Total size range  == 1<< nums.length = 1 << 3 = 1 * 2^ 3 = 8
i = 0 , b = 0, 1, 2
0 >> 0 = 0/1 = 0
0 >> 1 = 0/2 = 0
0 >> 2 = 0/4 = 0
==> Empty

i = 1 , b = 0, 1, 2
1 >> 0 = 1/1 = 1
1 >> 1 = 1/2 = 0
1 >> 2 = 1/4 = 0
==> [1]


Explanation:
Input: nums = [1,2,3]
Output:
[3], 0 0 1
[1], 1 0 0
[2], 0 1 0
[1,2,3], 1 1 1
[1,3], 1 0 1
[2,3], 0 1 1
[1,2], 1 1 0
[] 0 0 0


LLETCODE SOLUTION USING LIBRARY FUNCTION
========================================
class Solution {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> output = new ArrayList();
    int n = nums.length;

    for (int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n + 1); ++i) {
      // generate bitmask, from 0..00 to 1..11
      String bitmask = Integer.toBinaryString(i).substring(1);

      // append subset corresponding to that bitmask
      List<Integer> curr = new ArrayList();
      for (int j = 0; j < n; ++j) {
        if (bitmask.charAt(j) == '1') curr.add(nums[j]);
      }
      output.add(curr);
    }
    return output;
  }
}

EASY SOLUTION USING LIBRARY FUNCTION
====================================
//For approach 3, you don't need a fancy trick to create left zero padding. Also it adds additional substring() operation which is unnecessary. You can just calculate the offset based on the binary string generated. For an even better, just do some bit manipulation on the number instead of converting it to a binary string.

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        int n = nums.length;

        for(int i = 0; i < (int)Math.pow(2, n); i++) {
            String bitmask = Integer.toBinaryString(i);

            List<Integer> curr = new ArrayList();
            int offset = n - bitmask.length();
            for(int j = 0; j < bitmask.length(); j++) {
                if(bitmask.charAt(j) == '1') {
                    curr.add(nums[j + offset]);
                }
            }
            output.add(curr);
        }
        return output;
    }
