/*
If an interviewer gives you this problem, your first question should be - how should I handle duplicates? Your second question, perhaps, can be about the order of inputs and outputs. Such questions manifest your problem-solving skills, and help you steer to the right solution.

The solution for the previous problem, 349. Intersection of Two Arrays, talks about approaches when each number in the output must be unique. For this problem, we need to adapt those approaches so that numbers in the result appear as many times as they do in both arrays.

====================================================
Approach 1: Hash Map
For the previous problem, we used a hash set to achieve a linear time complexity. Here, we need to use a hash map to track the count for each number.

We collect numbers and their counts from one of the arrays into a hash map. Then, we iterate along the second array, and check if the number exists in the hash map and its count is positive. If so - add the number to the result and decrease its count in the hash map.

- It's a good idea to check array sizes and use a hash map for the smaller array. It will reduce memory usage when one of the arrays is very large.
Algorithm :
1. If nums1 is larger than nums2, swap the arrays.
2. For each element in nums1:
   - Add it to the hash map m.
     - Increment the count if the element is already there.
3. Initialize the insertion pointer (k) with zero.
4. Iterate along nums2:
   - If the current number is in the hash map and count is positive:
      - Copy the number into nums1[k], and increment k.
      - Decrement the count in the hash map.
5. Return first k elements of nums1.

- For our solutions here, we use one of the arrays to store the result. As we find common numbers, we copy them to the first array starting from the beginning. This idea is from this solution by sankitgupta.

Complexity Analysis
Time Complexity: O(n+m), where nnn and mmm are the lengths of the arrays. We iterate through the first, and then through the second array; insert and lookup operations in the hash map take a constant time.

Space Complexity: O(min⁡(n,m)). We use hash map to store numbers (and their counts) from the smaller array.
*/

public int[] intersect(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
        return intersect(nums2, nums1);
    }
    HashMap<Integer, Integer> m = new HashMap<>();
    for (int n : nums1) {
        m.put(n, m.getOrDefault(n, 0) + 1);
    }
    int k = 0;
    for (int n : nums2) {
        int cnt = m.getOrDefault(n, 0);
        if (cnt > 0) {
            nums1[k++] = n;
            m.put(n, cnt - 1);
        }
    }
    return Arrays.copyOfRange(nums1, 0, k);
}
