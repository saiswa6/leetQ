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
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode oddList = null;
        ListNode evenList = null;
        ListNode oddHead = null;
        ListNode evenHead = null;

        int i = 1;
        ListNode t = head;
        while (t != null) {
            if (i % 2 == 1) {
                if (oddHead == null) {
                    oddList = t;
                    oddHead = t;
                } else {
                    oddList.next = t;
                    oddList = oddList.next;
                }
            } else {
                if (evenHead == null) {
                    evenList = t;
                    evenHead = t;
                } else {
                    evenList.next = t;
                    evenList = evenList.next;
                }
            }
            t = t.next;
            i++;
        }

        evenList.next = null;
        ListNode newHead = oddHead;
        oddList.next = evenHead;
        return newHead;
    }
}