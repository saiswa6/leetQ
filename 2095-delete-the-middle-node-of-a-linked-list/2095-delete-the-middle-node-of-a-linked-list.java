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
    public ListNode deleteMiddle(ListNode head) {
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) { // for fast && fast.next for odd and even elements.
            prev = slow; // To track previous node of middle.
            slow = slow.next;
            fast = fast.next.next;
        }
        if (prev == null) { // prev is null, that means only one element is there. Remove head.
            return null;
        }
        prev.next = slow.next; // connect to prev to next
        return head;
    }
}