/*
This is a Facebook interview question.
They ask for the intersection, which has a trivial solution using a hash or a set.

Then they ask you to solve it under these constraints:
O(n) time and O(1) space (the resulting array of intersections is not taken into consideration).
You are told the lists are sorted.

Cases to take into consideration include:
duplicates, negative values, single value lists, 0's, and empty list arguments.
Other considerations might include
sparse arrays.


*/

Java. O(n) time and O(1) space

public int[] intersection(int[] nums1, int[] nums2) {
    HashSet<Integer> set = new HashSet<>();
    
    int i = 0;
    int j = 0;

    while(i < nums1.length && j < nums2.length) {
        if(nums1[i] == nums2[j]) {
            set.add(nums2[j]);
            i++;
            j++;
        } else if(nums1[i] > nums2[j]) {
            j++;
        } else {
            i++;
        }
    }
    return set.stream().mapToInt(Number::intValue).toArray();
}

// 2nd
class Solution:
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1 = sorted(nums1)
        nums2 = sorted(nums2)
        p1 = 0
        p2 = 0
        
        r = set()
        while p1 < len(nums1) and p2 < len(nums2):
            val1 = nums1[p1]
            val2 = nums2[p2]
            if val1 == val2:
                r.add(val1)
                p1 += 1
            elif val1 < val2:
                p1 +=1
            elif val1 > val2:
                p2 += 1
                
        return r
