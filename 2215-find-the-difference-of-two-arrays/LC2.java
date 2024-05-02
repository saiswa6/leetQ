/*
Approach 2: HashSet
Intuition
- Instead of iterating over each element in the second array to check if it exists in the list or not, we can store the elements in a HashSet. 
  Then we can find if an element exists in the list or not in O(1)O(1)O(1) time compared to O(N)O(N)O(N) time in the previous approach.
- In this approach, we follow the above intuition. To find the elements that only exist in nums1, we first store the elements in nums2 in the HashSet. 
  Then we iterate over each element in the list nums1, and for each element, we check if it's there in the HashSet; if yes, we skip the element; otherwise, we store it in the list onlyInNums1.
*/

class Solution {
    // Returns the elements in the first arg nums1 that don't exist in the second arg nums2.
    List<Integer> getElementsOnlyInFirstList(int[] nums1, int[] nums2) {
        Set<Integer> onlyInNums1 = new HashSet<> (); 
        
        // Store nums2 elements in an unordered set. 
        Set<Integer> existsInNums2 = new HashSet<> (); 
        for (int num : nums2) {
            existsInNums2.add(num);
        }
        
        // Iterate over each element in the list nums1.
        for (int num : nums1) {
            if (!existsInNums2.contains(num)) {
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

/*
Complexity Analysis
- Here, N is the length of list nums1, and MMM is the length of nums2.
  Time complexity: O(N+M)
- For each of the two calls to getElementsOnlyInFirstList we create a hash set from the elements in the second list, which takes linear time. 
  Then we iterate over the elements in the first list and check in the set if it's present. The find operation in the set takes O(1) time. Hence, the total time complexity would be O(N+M)

Space complexity: O(max(N,M)
- The method getElementsOnlyInFirstList need to store elements in the set. In the first call, it would be nums1 elements and in the second call, it would be nums2 elements. 
  The space required to store the output list is not considered part of space complexity, and hence the total space complexity would be equal to O(max(N,M))
*/
