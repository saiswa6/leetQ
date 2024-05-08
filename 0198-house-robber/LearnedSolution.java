class Solution {

// Striver Good Solution : https://takeuforward.org/data-structure/maximum-sum-of-non-adjacent-elements-dp-5/  -- Follw this & completely taken from the above.
    // Recursion

    /*
    Striver :
    Algorithm / Intuition
    As we need to find the sum of subsequences, one approach that comes to our mind is to generate all subsequences and pick the one with the maximum sum. 
    
    To generate all the subsequences, we can use the pick/non-pick technique. This technique can be briefly explained as follows:
    
    At every index of the array, we have two options.
    First, to pick the array element at that index and consider it in our subsequence.
    Second, to leave the array element at that index and not to consider it in our subsequence.

    First, we will try to form the recursive solution to the problem with the pick/non-pick technique. There is one more catch, the problem wants us to have only non-adjacent elements of the array in the subsequence, therefore we need to address that too.
    
    Steps to form the recursive solution
    ====================================
    We will use the steps mentioned in the article Dynamic Programming Introduction in order to form our recursive solution.
    
    Step 1: Form the function in terms of indexes: 
    ==============================================
     - We are given an array which can be easily thought of in terms of indexes. 
     - We can define our function f(ind) as : Maximum sum of the subsequence starting from index 0 to index ind.
     - We need to return f(n-1) as our final answer.
    Step 2: Try all the choices to reach the goal.
     As mentioned earlier we will use the pick/non-pick technique to generate all subsequences. We also need to take care of the non-adjacent elements in this step.
     -  If we pick an element then, pick = arr[ind] + f(ind-2). The reason we are doing f(ind-2) is because we have picked the current index element so we need to pick a non-adjacent element so we choose the index ‘ind-2’ instead of ‘ind-1’.
     -  Next we need to ignore the current element in our subsequence. So nonPick= 0 + f(ind-1). As we don’t pick the current element, we can consider the adjacent element in the subsequence.
    
    
    */
    /*
     * public int rob(int[] nums) {
     * int n = nums.length;
     * int dp[] = new int[n];
     * return robHelper(n - 1 , nums);
     * }
     * 
     * public int robHelper(int index, int nums[]) {
     * if(index == 0) {
     * return nums[index];
     * }
     * if(index < 0) {
     * return 0;
     * }
     * 
     * int pick = nums[index] + robHelper(index - 2, nums);
     * int notPick = 0 + robHelper(index - 1, nums);
     * 
     * return Math.max(pick, notPick);
     * }
     */


    /*
    Memoization approach :
    If we observe the recursion tree, we will observe a number of overlapping subproblems. Therefore the recursive solution can be memoized to reduce the time complexity.
    */

        public int rob(int[] nums) {
        int n = nums.length;
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        return robHelper(n - 1, nums, dp);
    }

    public int robHelper(int index, int nums[], int dp[]) {
        if (index == 0) {
            return nums[index];
        }
        if (index < 0) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }

        int pick = nums[index] + robHelper(index - 2, nums, dp);
        int notPick = 0 + robHelper(index - 1, nums, dp);

        dp[index] = Math.max(pick, notPick);
        return dp[index];
    }
}

/*
Complexity Analysis
Time Complexity: O(N)
Reason: The overlapping subproblems will return the answer in constant time O(1). Therefore the total number of new subproblems we solve is ‘n’. Hence total time complexity is O(N).

Space Complexity: O(N)
Reason: We are using a recursion stack space(O(N)) and an array (again O(N)). Therefore total space complexity will be O(N) + O(N) ≈ O(N)
*/


/*
Tabulation approach
======================
- Declare a dp[] array of size n.
- First initialize the base condition values, i.e dp[0] as 0.
- Set an iterative loop which traverses the array( from index 1 to n-1) and for every index calculate pick  and nonPick
-  And then we can set dp[i] = max (pick, nonPick)
*/
import java.util.*;

class TUF {
    // This function uses dynamic programming to find the maximum possible sum of non-adjacent elements.
    static int solveUtil(int n, int[] arr, int[] dp) {
        // Initialize the dp array with the first element of the input array.
        dp[0] = arr[0];

        // Iterate through the input array to fill the dp array.
        for (int i = 1; i < n; i++) {
            // Calculate the maximum sum by either picking the current element or not picking it.
            int pick = arr[i];
            
            // If there are at least two elements before the current element, add the value from dp[i-2].
            if (i > 1)
                pick += dp[i - 2];
            
            // The non-pick option is to use the maximum sum from the previous element.
            int nonPick = dp[i - 1];

            // Store the maximum of the two options in the dp array for the current index.
            dp[i] = Math.max(pick, nonPick);
        }

        // The final element of the dp array contains the maximum possible sum.
        return dp[n - 1];
    }

    // This function initializes the dp array and calls the solver function.
    static int solve(int n, int[] arr) {
        int dp[] = new int[n];
        
        // Initialize the dp array with -1 to indicate that values are not calculated yet.
        Arrays.fill(dp, -1);
        
        // Call the solver function to find the maximum possible sum.
        return solveUtil(n, arr, dp);
    }

    public static void main(String args[]) {
        // Input array with elements.
        int arr[] = {2, 1, 4, 9};
        
        // Get the length of the array.
        int n = arr.length;
        
        // Call the solve function to find the maximum possible sum.
        int result = solve(n, arr);
        
        // Print the result.
        System.out.println(result);
    }
}

/*
Complexity Analysis
Time Complexity: O(N)
Reason: We are running a simple iterative loop

Space Complexity: O(N)
Reason: We are using an external array of size ‘n+1’.
*/


// SPace Optimization Approach
/*
Part 3: Space Optimization

If we closely look at the values required at every iteration,
dp[i], dp[i-1], and  dp[i-2]

we see that for any i, we do need only the last two values in the array. So is there a need to maintain a whole array for it? 
The answer is ‘No’. Let us call dp[i-1] as prev and dp[i-2] as prev2. 

- Each iteration’s cur_i and prev become the next iteration’s prev and prev2 respectively.
- Therefore after calculating cur_i, if we update prev and prev2 according to the next step, we will always get the answer. 
- After the iterative loop has ended we can simply return prev as our answer.
*/

import java.util.*;

class TUF {
    // This function finds the maximum possible sum of non-adjacent elements in an array
    // using a more space-efficient dynamic programming approach.
    static int solve(int n, int[] arr) {
        // Initialize variables to keep track of the maximum sums at the current and previous positions.
        int prev = arr[0];
        int prev2 = 0;

        // Iterate through the array starting from the second element.
        for (int i = 1; i < n; i++) {
            // Calculate the maximum sum by either picking the current element or not picking it.
            int pick = arr[i];

            // If there are at least two elements before the current element, add the value from prev2.
            if (i > 1)
                pick += prev2;

            // The non-pick option is to use the maximum sum from the previous position.
            int nonPick = prev;

            // Calculate the maximum sum for the current position and update prev and prev2.
            int cur_i = Math.max(pick, nonPick);
            prev2 = prev;
            prev = cur_i;
        }

        // The 'prev' variable now holds the maximum possible sum.
        return prev;
    }

    public static void main(String args[]) {
        // Input array with elements.
        int arr[] = {2, 1, 4, 9};

        // Get the length of the array.
        int n = arr.length;

        // Call the solve function to find the maximum possible sum.
        int result = solve(n, arr);

        // Print the result.
        System.out.println(result);
    }
}

/*
Complexity Analysis
Time Complexity: O(N)
Reason: We are running a simple iterative loop

Space Complexity: O(1)
Reason: We are not using any extra space.
*/
