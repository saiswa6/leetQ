/*
Brute Force Solution :
First let's look at the O(N^2) solution -
Simply do a double iteration and for each ball found, compute the distance and add it to the cost. Easy peasy.
*/
class Solution {
    public int[] minOperations(String boxes) {
        int len = boxes.length();
        int[] ans = new int[len];
        for(int i=0;i<len;i++){
            int cost = 0;
            for(int j=0;j<len;j++){
                if(boxes.charAt(j)=='1') cost += Math.abs(i-j);
            }
            ans[i] = cost;
        }
        return ans;
    }
}
/////////////////////////////////////////////////////////////////////////////

class Solution {
    public int[] minOperations(String boxes) {
        /*
        Good Solution :
Now, let's take a look at the O(N) solution. The code has two passes, forward and back.
The assumption is when we travel forward we're only considering the balls that are left to the current index, similarly when we travel back we're only considering the balls that are to the right of the current index.
When moving forward, the cost of moving balls to the right for ith index can be computed if we know the total number of balls till (i-1)th index and the cost of moving balls till (i-1)th index i.e
forward[i] = forward[i-1]+number_of_balls_till_i-1
In a similar fashion,
back[i] = back[i+1]+number_of_balls_till_i+1

Once we have both these values, the answer is simply the summation of the forward and backward costs.
        */
class Solution {
    public int[] minOperations(String boxes) {
        int len = boxes.length();
        int[] forward = new int[len];
        int prev = (boxes.charAt(0)=='0'?0:1);
        for(int i=1;i<len;i++){
            forward[i] += forward[i-1]+prev;
            prev += (boxes.charAt(i)=='0'?0:1);
        }
        prev = (boxes.charAt(len-1)=='0'?0:1);
        int[] back = new int[len];
        for(int i=len-2;i>=0;i--){
            back[i] += back[i+1]+prev;
            prev += (boxes.charAt(i)=='0'?0:1);
        }
        int[] op = new int[len];
        for(int i=0;i<len;i++) op[i] = forward[i]+back[i];
        return op;
    }
}
        /*
        2nd Solution Similar :
         * Similar to 238. Product of Array Except Self and 42. Trapping Rain Water.
         * For each index, the cost to move all boxes to it is sum of the cost leftCost
         * to move all left boxes to it, and the cost rightCost to move all right boxes
         * to it.
         * 
         * leftCost for all indexes can be calculted using a single pass from left to
         * right.
         * rightCost for all indexes can be calculted using a single pass from right to
         * left.
         * 
         * Example:
         * boxes 11010
         * leftCount 01223
         * leftCost 01358
         * rightCount 21100
         * rightCost 42100
         * ans 43458
         */
        int n = boxes.length();

        int[] left = new int[n];
        int[] right = new int[n];
        int[] ans = new int[n];

        int count = boxes.charAt(0) - '0';
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] + count;
            count += boxes.charAt(i) - '0';
        }

        count = boxes.charAt(n - 1) - '0';
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] + count;
            count += boxes.charAt(i) - '0';
        }

        for (int i = 0; i < n; i++) {
            ans[i] = left[i] + right[i];
        }

        return ans;
    }
}

///////////////
// Little Bit Complicated
class Solution {
    
    public int[] minOperations(String boxes) {
        
        int[] operations = new int[boxes.length()];
        int ballCount = 0; // How many balls we're currently moving to the left or right, depending on which loop we are in
        int opsPerIncrement = 0; // How many operations per increment in array
        
        // Move balls to the right -->
        for(int i = 0; i < boxes.length(); i++) {
            operations[i] += opsPerIncrement;
            if(boxes.charAt(i) == '1') {
                ballCount++;
            }
            opsPerIncrement += ballCount;
            
        }
        
        ballCount = 0;
        opsPerIncrement = 0;
        
        // Move balls to the left <--
        for(int i = boxes.length() - 1; i >= 0; i--) {
            operations[i] += opsPerIncrement;
            if(boxes.charAt(i) == '1') {
                ballCount++;
            }
            opsPerIncrement += ballCount;
        }
        
        return operations;
    }
}
