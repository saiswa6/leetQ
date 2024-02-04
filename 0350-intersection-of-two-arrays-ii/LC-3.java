/*
Approach 3: Built-in Intersection
- This is similar to Approach 2. Instead of iterating with two pointers, we use a built-in function to find common elements. In C++, we can use set_intersection for sorted arrays (or multisets).
- The retainAll method in Java, unfortunately, does not care how many times an element occurs in the other collection. You can use the retainOccurrences method of the multiset implementation in Guava.

Algorithm
- Note that set_intersection returns the position past the end of the produced range, so it can be used as an input for the erase function. The idea is from this solution by StefanPochmann.

Complexity Analysis :
Same as for approach 2 above.
*/

vector<int> intersect(vector<int>& nums1, vector<int>& nums2) {
    sort(begin(nums1), end(nums1));
    sort(begin(nums2), end(nums2));
    nums1.erase(set_intersection(begin(nums1), end(nums1),
        begin(nums2), end(nums2), begin(nums1)), end(nums1));
    return nums1;
}

/*
Follow-up Questions
1. What if the given array is already sorted? How would you optimize your algorithm?
   - We can use either Approach 2 or Approach 3, dropping the sort of course. It will give us linear time and constant memory complexity.

2. What if nums1's size is small compared to nums2's size? Which algorithm is better?
   - Approach 1 is a good choice here as we use a hash map for the smaller array.

3. What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
   - If nums1 fits into the memory, we can use Approach 1 to collect counts for nums1 into a hash map. Then, we can sequentially load and process nums2.
   - If neither of the arrays fit into the memory, we can apply some partial processing strategies:
      - Split the numeric range into subranges that fits into the memory. Modify Approach 1 to collect counts only within a given subrange, and call the method multiple times (for each subrange).
      - Use an external sort for both arrays. Modify Approach 2 to load and process arrays sequentially.
*/
