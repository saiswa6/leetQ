/*
Approach 1: Split + Parse, Two Pass
Intuition
- The first idea is to split both strings by dot character into chunks and then compare the chunks one by one

- That works fine if the number of chunks is the same for both versions. If not, we need to pad the shorter string by adding .0 at the end of the string with fewer chunks one or several times, so that the number of chunks will be the same.

Algorithm
1. Split both strings by dot character into two arrays.
2. Iterate over the longest array and compare chunks one by one. If one of the arrays is over, virtually add as many zeros as needed to continue the comparison with the longer array.
   - If two chunks are not equal, return 1 or -1.
3. If we're here, the versions are equal. Return 0.


Complexity Analysis
Time complexity : O(N+M+max⁡(N,M)), where NNN and MMM are lengths of input strings.
Space complexity : O(N+M) to store arrays nums1 and nums2.
*/

class Solution {
  public int compareVersion(String version1, String version2) {
    String[] nums1 = version1.split("\\.");
    String[] nums2 = version2.split("\\.");
    int n1 = nums1.length, n2 = nums2.length;

    // compare versions
    int i1, i2;
    for (int i = 0; i < Math.max(n1, n2); ++i) {
      i1 = i < n1 ? Integer.parseInt(nums1[i]) : 0;
      i2 = i < n2 ? Integer.parseInt(nums2[i]) : 0;
      if (i1 != i2) {
        return i1 > i2 ? 1 : -1;
      }
    }
    //The versions are equal
    return 0;
  }
}
