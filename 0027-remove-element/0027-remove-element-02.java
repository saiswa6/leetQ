/*
Approach 2: Two Pointers - when elements to remove are rare
Intuition
Now consider cases where the array contains few elements to remove. For example, nums=[1,2,3,5,4],val=4. The previous algorithm will do unnecessary copy operation of the first four elements. Another example is nums=[4,1,2,3,5],val=4. It seems unnecessary to move elements [1,2,3,5] one step left as the problem description mentions that the order of elements could be changed.

Algorithm
When we encounter nums[i]=val, we can swap the current element out with the last element and dispose the last one. This essentially reduces the array's size by 1.
Note that the last element that was swapped in could be the value you want to remove itself. But don't worry, in the next iteration we will still check this element.

Complexity analysis
Time complexity: O(n).
Both i and n traverse at most n steps. In this approach, the number of assignment operations is equal to the number of elements to remove. So it is more efficient if elements to remove are rare.

Space complexity: O(1).
*/
public int removeElement(int[] nums, int val) {
    int i = 0;
    int n = nums.length;
    while (i < n) {
        if (nums[i] == val) {
            nums[i] = nums[n - 1];
            //Reduce array size by one
            n--;
        } else {
            i++;
        }
    }
    return n;
}

/*
My SOLUTION 
1. If length is 0 then 0.
2. If length is 1 and val is unequal, then one element can't be removed.
3. Now, firstIndex is to proceed for not val and stop exactly at val for swap and secondIndex is for vice-versa. 
4. Each time check array index conditions carefully which might cause array out of bounds exception.
5. swap is an unnecessary step.
    */

class Solution {
    public int removeElement(int[] nums, int val) {
        if(nums.length == 0)
        {
            return 0;
        }
        if(nums.length == 1 && nums[0] != val)
        {
            return 1;
        }
        int firstMatchIndex = 0;
        int secondMatchIndex = nums.length - 1;
        int notEqualAns = 0;
        while(firstMatchIndex < secondMatchIndex)
        {
            while( firstMatchIndex < nums.length && nums[firstMatchIndex] != val)
            {
                firstMatchIndex++;
                notEqualAns++;
            }

            while(secondMatchIndex >= 0 && nums[secondMatchIndex] == val )
            {
                secondMatchIndex--;
            }

            if(firstMatchIndex < secondMatchIndex)
            {
                int swap = nums[firstMatchIndex];
                nums[firstMatchIndex] = nums[secondMatchIndex];
                nums[secondMatchIndex] = swap;
            }
        }
           if(firstMatchIndex < secondMatchIndex)
            {
                int swap = nums[firstMatchIndex];
                nums[firstMatchIndex] = nums[secondMatchIndex];
                nums[secondMatchIndex] = swap;
            }
        return notEqualAns;
    }
}
