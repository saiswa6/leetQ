/*
Approach 1: Brute Force
Intuition and Algorithm
- The brute force solution is often a good starting point in an interview. While you shouldn't actually code up this approach (it's not a good use of time to do so), you should briefly explain it to your interviewer. Once you've done that, you'll then be analyzing inefficiencies and coming up with ways to optimize it.
- The brute force solution here is nothing too special: For each node in list A, traverse over list B and check whether or not the node is present in list B.
- The one thing we need to be careful of is that we're comparing objects of type Node. We don't want to compare the values within the nodes; doing this would cause our code to break when two different nodes have the same value.

Implementation
- Note that we're only showing this code for your reference. This is not a good approach for an interview, and the only reason we discussed it at all as we will be optimizing it in Approach 2. For this reason, we aren't guaranteeing that the code will pass our judge in every language.

Complexity Analysis
Let N be the length of list A and MMM be the length of list B.
Time complexity : O(N×M)
- For each of the NNN nodes in list A, we are traversing over each of the nodes in list B. In the worst case, we won't find a match, and so will need to do this until reaching the end of list B, giving a worst-case time complexity of O(N×M).

Space complexity : O(1).
We aren't allocating any additional data structures, so the amount of extra space used does not grow with the size of the input.
*/

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        while (headA != null) {
            ListNode pB = headB;
            while (pB != null) {
                if (headA == pB) return headA;
                pB = pB.next;
            }
            headA = headA.next;
        }
        return null;
    }
}
