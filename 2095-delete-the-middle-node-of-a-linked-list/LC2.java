/*
Approach 2: Fast and Slow Pointers
Intuition
- The key of this approach is that we have two pointers fast and slow traversing the linked list at the same time, and fast traverses twice as fast as slow. 
  Therefore, when fast reaches the end of the linked list, slow is right in the middle! We only need one iteration to reach the middle node!
- All that needs to be determined are a few lookup details. If there is only one node in the linked list, this node is also the one to be deleted and there are no nodes left after the deletion. 
  Therefore, instead of initializing two pointers for the following procedure, we can just return null.

Why we initialize fast = head.next.next at the begining?
- The reason for this is that we want to deleted the middle node instead of finding it. Therefore, we are actually looking for the predecessor of the middle node, not the middle node itself, or rather, this is like moving slow backward one node after the iteration stops.

Certainly, we can't move a pointer backward on a singly linked list, thus we can show this one less step on slow by letting fast moves forward one more step (by two nodes, of course). Hence, slow will also point to the predecessor node of the middle node (rather than the middle node) at the end of the iteration.
*/

class Solution {
    public ListNode deleteMiddle(ListNode head) {
        // Edge case: return nullptr if there is only one node.
        if (head.next == null)
            return null;
        
        // Initialize two pointers, 'slow' and 'fast'.
        ListNode slow = head, fast = head.next.next;
        
        // Let 'fast' move forward by 2 nodes, 'slow' move forward by 1 node each step.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // When 'fast' reaches the end, remove the next node of 'slow' and return 'head'.
        slow.next = slow.next.next;
        return head;
    }
}

/*
Complexity Analysis
Let n be the length of the input linked list.
- Time complexity: O(n)

- We stop the iteration when the pointer fast reaches the end, fast moves forward 2 nodes per step, so there are at most n/2 steps.
- In each step, we move both fast and slow, which takes a constant amount of time.
- Removing the middle node also takes constant time.
- In summary, the overall time complexity is O(n)

Space complexity: O(1)
- We only need two pointers, so the space complexity is O(1)
*/
