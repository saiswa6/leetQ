/*
APPROACH 1 
We can apply Two Sum's solutions directly to get O(n2) time, O(1) space using brute force

Approach 2
O(n) time, O(n) space using hash table.
However, both existing solutions do not make use of the property that the input array is sorted. We can do better.

Approach 3: Two Pointers
Non-decreasing ==> Increasing but some element can be equal in middle . Ex : -5,-4,-4,-2,-2,-2,0,3,6,6,9.
We use two indices, initially pointing to the first and the last element, respectively. Compare the sum of these two elements with target. If the sum is equal to target, we found the exactly only solution. If it is less than target, we increase the smaller index by one. If it is greater than target, we decrease the larger index by one. Move the indices and repeat the comparison until the solution is found.

Let [...,a,b,c,...,d,e,f,...] be the input array that is sorted in ascending order and let the elements bbb and eee be the exactly only solution. Because we are moving the smaller index from left to right, and the larger index from right to left, at some point, one of the indices must reach either b or e. Without loss of generality, suppose the smaller index reaches b first. At this time, the sum of these two elements must be greater than target. Based on our algorithm, we will keep moving the larger index to the left until we reach the solution.

Complexity Analysis
Time complexity: O(n)
The input array is traversed at most once. Thus the time complexity is O(n)

Space complexity: O(1)
We only use additional space to store two indices and the sum, so the space complexity is O(1)

Follow-Up
=========
What if the problem constraints were different and we needed to consider integer overflow when adding numbers[low] and numbers[high]? In that case, to prevent an overflow error, we could cast our numbers from int data type to long data type before adding them together, 
e.g.: long sum = static_cast<long>(numbers[low]) + numbers[high] for C++. 
- Casting ensures that we will not get the overflow error since the signed long data type supports numbers up to 2^63 - 1. 
- Alternatively, if we cannot use long integers, then we can check if numbers[low] > (1 << 31) - 1 - numbers[high] at the beginning of each iteration. 
  If this condition is true, then numbers[low] + numbers[high] will result in integer overflow, and so we would move the larger index to the left.
-  To handle overflow, use like this numbers[startPointer] = target - numbers[endPointer] or numbers[startPointer] > target - numbers[endPointer]

Intuition
  Start with the full array. Let L be the leftmost element, R the rightmost.

If L + R = target: We're done
If L + R > target: The rightmost element is too big, because it exceeds the target, even when combined with the smallest available element. Since no element can be smaller than L, this proves that the rightmost element is useless and must be removed.
If L + R < target: The leftmost element is too small, because it is smaller than the target, even when combined with the biggest available element. Therefore it must be removed.
Once you removed the leftmost or the rightmost, the algorithm starts over on the remaining array
*/





class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int startPointer = 0;
        int endPointer = numbers.length - 1;
        while(startPointer < endPointer)
        {
            int sum = numbers[startPointer] + numbers[endPointer];
            if( sum == target)
            {
                return new int[] {startPointer + 1, endPointer + 1};
            }
            else if (sum > target)
            {
                endPointer--;
            }
            else
            {
                startPointer++;
            }
        }
        return new int[] {-1, -1};
    }
}
