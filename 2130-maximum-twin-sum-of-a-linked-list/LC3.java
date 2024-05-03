/*
Approach 3: Reverse Second Half In Place
Intuition
- Another method is to flip the second half of the linked list so that the last element points to the second last element, which points to the third last element, and so on until the middle element.

- To reverse the second half of the linked list, we must first obtain the list's middle (from which the second half starts). To get to the middle of the list, we can use two pointers: slow and fast. We set their initial value to head.

- We move slow to the next node after moving fast two nodes ahead. We perform this until fast or fast.next do not become null. Because fast moves at twice the speed of slow, we will have the required middle node at slow.

- Reversing a linked list is a classic problem. We need three pointers: a) nextNode, to hold the next node so that when we reverse the next pointer of the previous node, we have access to the next node, b) slow, the node under consideration whose next must be set to the previous node, and c) prev, the previous node.

- We first perform nextNode = slow.next so we can still reach the next node after modifying slow.next. Then we set the next pointer of slow to prev.

- To set up the variables for next iteration, we set prev = slow and slow = nextNode. We continue doing while till slow is not null.

- Once we've reversed the second half of the list, prev will point to the first element of this reversed list. So we use head to iterate over the original list because the first half is unaffected, and prev to iterate over the reversed list. We add the corresponding node values, update the maximum twin sum with the current twin if possible, and then proceed to the next node in both lists.
*/

class Solution {
    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Get middle of the linked list.
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // Reverse second half of the linked list.
        ListNode nextNode, prev = null;
        while (slow != null) {
            nextNode = slow.next;
            slow.next = prev;
            prev = slow;
            slow = nextNode;
        }

        ListNode start = head;
        int maximumSum = 0;
        while (prev != null) {
            maximumSum = Math.max(maximumSum, start.val + prev.val);
            prev = prev.next;
            start = start.next;
        }

        return maximumSum;
    }
}

/*
Complexity Analysis
Here, n is the number of nodes in the linked list.

Time complexity: O(n)
It takes O(n) time to iterate over the linked list to find the middle and then reverse the second half of the linked list.
We iterate over the half of the linked list to find the maximum twin sum, which also takes O(n) time.

Space complexity: O(1)
Except for a few pointers that take up constant space, we don't take up any space.
*/
