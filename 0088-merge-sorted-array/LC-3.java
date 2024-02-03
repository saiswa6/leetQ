/*
Approach 3: Three Pointers (Start From the End)
Intuition :
Interview Tip: This is a medium-level solution to an easy-level problem. Many of LeetCode's easy-level problems have more difficult solutions, and good candidates are expected to find them.

Approach 2 already demonstrates the best possible time complexity, O(n+m)\mathcal{O}(n + m)O(n+m), but still uses additional space. This is because the elements of array nums1 have to be stored somewhere so that they aren't overwritten.

So, what if instead we start to overwrite nums1 from the end, where there is no information yet?
The algorithm is similar to before, except this time we set p1 to point at index m - 1 of nums1, p2 to point at index n - 1 of nums2, and p to point at index m + n - 1 of nums1. This way, it is guaranteed that once we start overwriting the first m values in nums1, we will have already written each into its new position. In this way, we can eliminate the additional space.

Interview Tip: Whenever you're trying to solve an array problem in place, always consider the possibility of iterating backwards instead of forwards through the array. It can completely change the problem, and make it a lot easier.

Complexity Analysis
Time complexity: O(n+m).
Same as Approach 2.

Space complexity: O(1)
Unlike Approach 2, we're not using an extra array.

Proof (optional)
***************************************
- You might be a bit skeptical of this claim. Does it really work in every case? Is it safe to be making such a bold claim?
- This way, it is guaranteed that once we start overwriting the first m values in nums1,
we will have already written each into its new position. In this way, we can eliminate the additional space.

- Great question! So, why does this work? To prove it, we need to ensure that p never overwrites a value in nums1 that p1 hasn't yet read from nums1.
- Words of Advice: Terrified of proofs? Many software engineers are. Good proofs are simply a series of logical assertions, each building on the next. In this way, we can go from "obvious" statements, all the way to the one we want to prove. I recommend reading each statement one by one, ensuring that you understand each before moving to the next.

1. We know that upon initialization, p is n steps ahead of p1 (in other words, p1 + n = p).
2. We also know that during each of the p iterations this algorithm performs, p is always decremented by 1, and either p1 or p2 is decremented by 1.
3. We can deduce that when p1 decremented, the gap between p and p1 stays the same, so there can't be an "overtake" in that case.
4. We can also deduce that when p2 is decremented though, the gap between p and p1 shrinks by 1 as p moves, but not p1.
5. And from that, we can deduce that the maximum number of times that p2 can be decremented is n. In other words, the gap between p and p1 can shrink by 1, at most n times.
6. In conclusion, it's impossible for an overtake to occur, as they started n apart. And when p = p1, the gap has to have shrunk n times. This means that all of nums2 have been merged in, so there is nothing more to do.
*/


class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Set p1 and p2 to point to the end of their respective arrays.
        int p1 = m - 1;
        int p2 = n - 1;
        
        // And move p backward through the array, each time writing
        // the smallest value pointed at by p1 or p2.
        for (int p = m + n - 1; p >= 0; p--) {
            if (p2 < 0) {
                break;
            }
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1--];
            } else {
                nums1[p] = nums2[p2--];
            }
        }
    }
}
