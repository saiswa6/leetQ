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
class Solution {
    public int pairSum(ListNode head) {
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondList = prev.next;
        prev.next = null;
        ListNode secondHead = reverse(secondList);

        ListNode first = head;
        ListNode second = secondHead;

        int anser = 0;

        while (first != null && second != null) {
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