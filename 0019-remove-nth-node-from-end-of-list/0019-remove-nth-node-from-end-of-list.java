/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int nodeFromStart = findLength(head) - n + 1;

        if (nodeFromStart == 1) {
            head = head.next;
            return head;
        }

        int count = 1;
        ListNode mHead = head;
        ListNode prev = null;
        while(mHead != null) {
            if(nodeFromStart == count) {
                prev.next = mHead.next;
                return head;
            }
            prev = mHead;
            mHead = mHead.next;
            count++;
        }

        return head;
    }

    public int findLength(ListNode lHead) {
        int length = 0;
        while(lHead != null) {
            length++;
            lHead = lHead.next;
        }
        return length;
    }
}