/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// Approach : Find middle , break into 2 lists. reverse 2nd half. traverse and
// compare.
class Solution {
    public int pairSum(ListNode head) {
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) { // Find middle element - start of 2nd LL.
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondList = prev.next; // 2nd List
        prev.next = null;
        ListNode secondHead = reverse(secondList); // Reverse

        ListNode first = head;
        ListNode second = secondHead;

        int anser = 0;

        while (first != null && second != null) { // Add twin Sums
            anser = Math.max(anser, first.val + second.val);
            first = first.next;
            second = second.next;
        }

        return anser;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) { // next, prev, curr.
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev; // Finally prev becomes head. & current becomes null.
    }
}