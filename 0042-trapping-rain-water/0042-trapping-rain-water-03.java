/*
//Complicated Solution - Leave it

Intuition
- Instead of storing the largest bar upto an index as in Approach 2, we can use stack to keep track of the bars that are bounded by longer bars and hence, may store water. Using the stack, we can do the calculations in only one iteration.
We keep a stack and iterate over the array. We add the index of the bar to the stack if bar is smaller than or equal to the bar at top of stack, which means that the current bar is bounded by the previous bar in the stack. If we found a bar longer than that at the top, we are sure that the bar at the top of the stack is bounded by the current bar and a previous bar in the stack, hence, we can pop it and add resulting trapped water to ans.

Algorithm
1. Use stack to store the indices of the bars.
2. Iterate the array:
     While stack is not empty and height[current]>height[st.top()]
        It means that the stack element can be popped. Pop the top element as top.
        Find the distance between the current element and the element at top of stack, which is to be filled.
           distance=current−st.top()−1
        Find the bounded height
           bounded_height=min⁡(height[current],height[st.top()])−height[top]
        Add resulting trapped water to answer ans+=distance×bounded_height\text{ans} \mathrel{+}= \text{distance} \times \text{bounded\_height}ans+=distance×bounded_height
     Push current index to top of the stack
     Move current to the next position

Complexity analysis :
Time complexity: O(n)
Single iteration of O(n) in which each bar can be touched at most twice(due to insertion and deletion from stack) and insertion and deletion from stack takes O(1) time.
Space complexity: O(n). Stack can take upto O(n) space in case of stairs-like or flat structure.

*/

class Solution {
public:
    int trap(vector<int>& height)
    {
        int ans = 0, current = 0;
        stack<int> st;
        while (current < height.size()) {
            while (!st.empty() && height[current] > height[st.top()]) {
                int top = st.top();
                st.pop();
                if (st.empty())
                    break;
                int distance = current - st.top() - 1;
                int bounded_height = min(height[current], height[st.top()]) - height[top];
                ans += distance * bounded_height;
            }
            st.push(current++);
        }
        return ans;
    }
};
