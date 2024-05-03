/*
Approach 2: Using Stack
Intuition
- As you may have guessed, we require a method to obtain the values of the nodes in the second half of the linked list in reverse order. Getting the values of the nodes is simple. We can do so by using head, which points to the first node in the list and then using next we can get all the next nodes, the same way we did in the previous approach.
- We can use a stack to get the values of the second half nodes in reverse order. We iterate over the linked list, pushing all of the node values into the stack.
- To compute the twin sums, we iterate from the beginning of the list with head and get the values of the nodes from the end using the stack. We find the first half nodes using next pointers and pop from the top of the stack to get the second half nodes.
*/

class Solution {
    public int pairSum(ListNode head) {
        ListNode current = head;
        Stack<Integer> st = new Stack<Integer>();

        while (current != null) {
            st.push(current.val);
            current = current.next;
        }

        current = head;
        int size = st.size(), count = 1;
        int maximumSum = 0;
        while (count <= size / 2) {
            maximumSum = Math.max(maximumSum, current.val + st.peek());
            current = current.next;
            st.pop();
            count++;
        }

        return maximumSum;
    }
}

/*
Complexity Analysis
Here, n is the number of nodes in the linked list.

Time complexity: O(n)
Iterating over the linked list and pushing all the node values in st takes O(n) time.
We iterate over the first half of the linked list to find the maximum twin sum, which also takes O(n) time.

Space complexity: O(n)
The st stack takes O(n) space as we push nnn elements into it
*/
