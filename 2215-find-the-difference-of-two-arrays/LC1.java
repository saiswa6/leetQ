/*
We are given two integer arrays, nums1 and nums2, and need to return a list of two lists. The first list has the elements that are present only in nums1, 
while the second list has the elements that are present only in nums2.


Approach 1: Brute Force
Intuition
- To find the elements in a list that are not present in another list, we can loop over every element in the first list and for each element we loop over the elements in the second list to check if it's present or not. 
  If we find the element, we will not store it in the answer list; otherwise, we can store it.
- This way, we will have to apply the above method twice once for the elements that are only in nums1 and then again for the elements that are only present in nums2.
*/

class Solution {
    // Returns the elements in the first arg nums1 that don't exist in the second arg nums2.
    List<Integer> getElementsOnlyInFirstList(int[] nums1, int[] nums2) {
        Set<Integer> onlyInNums1 = new HashSet<> ();   // to avoid duplicates
        
        // Iterate over each element in the list nums1.
        for (int num : nums1) {
            boolean existInNums2 = false;
            // Check if num is present in the second arg nums2.
            for (int x : nums2) {
                if (x == num) {
                    existInNums2 = true;
                    break;
                }
            }
            
            if (!existInNums2) {
                onlyInNums1.add(num);
            }
        }
        
        // Convert to vector.
        return new ArrayList<>(onlyInNums1);
    }
    
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        return Arrays.asList(getElementsOnlyInFirstList(nums1, nums2), getElementsOnlyInFirstList(nums2, nums1));
    }
}

/*Complexity Analysis
- Here, N is the length of list nums1, and MMM is the length of nums2.
  Time complexity: O(N∗M)
  In the first call to getElementsOnlyInFirstList, we iterate over the first list and, for each element, iterate over the second, which costs us N∗M operations. 
  Then again, doing it for the other pair (nums2, nums1), the total operations will be M∗NM * NM∗N. Hence the total time complexity would be O(N∗M)

Space complexity: O(1)
- The only space required (two lists of size NNN and MMM) is to store the output list that is not considered part of the space complexity. Hence, the total space complexity would be constant.
  */
