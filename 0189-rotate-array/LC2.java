/*
Approach 3: Using Cyclic Replacements
Algorithm

We can directly place every number of the array at its required correct position.
But if we do that, we will destroy the original element. Thus, we need to store the number being replaced in a temptemptemp variable. Then, we can place the replaced number(temp) at its correct position and so on, nnn times, where n is the length of array. We have chosen nnn to be the number of replacements since we have to shift all the elements of the array(which is n).
But, there could be a problem with this method, if n%k=0 where k=k%n (since a value of kkk larger than n eventually leads to a k equivalent to k%n).
In this case, while picking up numbers to be placed at the correct position, we will eventually reach the number from which we originally started. Thus, in such a case, when we hit the original number's index again, we start the same process with the number following it.

Now let's look at the proof of how the above method works.
Suppose, we have nnn as the number of elements in the array and k is the number of shifts required. Further, assume n%k=0. Now, when we start placing the elements at their correct position, in the first cycle all the numbers with their index iii satisfying i%k=0 get placed at their required position.
This happens because when we jump k steps every time, we will only hit the numbers k steps apart.
We start with index i=0, having i%k=0. Thus, we hit all the numbers satisfying the above condition in the first cycle.
When we reach back the original index, we have placed nk elements at their correct position, since we hit only that many elements in the first cycle.
Now, we increment the index for replacing the numbers.
This time, we place other nk elements at their correct position, different from the ones placed correctly in the first cycle,
because this time we hit all the numbers satisfy the condition i%k=1.
When we hit the starting number again,
we increment the index and repeat the same process from i=1 for all the indices satisfying i%k==1.
This happens till we reach the number with the index i%k=0 again, which occurs for i=k. We will reach such a number after a total of k cycles.
Now, the total count of numbers exclusive numbers placed at their correct position will be k×n/k=n.
Thus, all the numbers will be placed at their correct position.

Look at the following example to clarify the process

Complexity Analysis
Time complexity: O(n). Only one pass is used.
Space complexity: O(1). Constant extra space is used.
*/

class Solution {
  public void rotate(int[] nums, int k) {
    k = k % nums.length;
    int count = 0;
    for (int start = 0; count < nums.length; start++) {
      int current = start;
      int prev = nums[start];
      do {
        int next = (current + k) % nums.length;
        int temp = nums[next];
        nums[next] = prev;
        prev = temp;
        current = next;
        count++;
      } while (start != current);
    }
  }
}


///////  -- Alternative
    class Solution 
    {
    public:
        void rotate(int nums[], int n, int k) 
        {
            if ((n == 0) || (k <= 0))
            {
                return;
            }
            
            int cntRotated = 0;
            int start = 0;
            int curr = 0;
            int numToBeRotated = nums[0];
            int tmp = 0;
            // Keep rotating the elements until we have rotated n 
            // different elements.
            while (cntRotated < n)
            {
                do
                {
                    tmp = nums[(curr + k)%n];
                    nums[(curr+k)%n] = numToBeRotated;
                    numToBeRotated = tmp;
                    curr = (curr + k)%n;
                    cntRotated++;
                } while (curr != start);
                // Stop rotating the elements when we finish one cycle, 
                // i.e., we return to start.
                
                // Move to next element to start a new cycle.
                start++;
                curr = start;
                numToBeRotated = nums[curr];
            }
        }
    };


//// 3rd solution
class Solution3:
    def rotate(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        k = k % len(nums)
        count = 0
        start = 0
        while count < len(nums):
            current = start 
            prev = nums[start] #store the value in the position
            
            while True:
                next = (current + k) % len(nums) #
                temp = nums[next] #store it temporaly 
                nums[next] = prev #overide the next 
                prev = temp #reset prev
                current = next #reset current
                count += 1

                if start == current:
                    break 
            
            start += 1
