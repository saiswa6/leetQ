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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode prev = null;
        ListNode mHead = head;

        while(mHead !=null && mHead.next != null) {
            int count = 1;
            while(mHead.next != null && mHead.val == mHead.next.val) {
                count++;
                mHead = mHead.next;
            }
            if(count > 1) {
                if(prev == null) {
                    head = mHead.next;
                } else {
                    prev.next = mHead.next;
                    //prev = mHead.next;
                }
            } else {
                if(prev == null) {
                    prev = mHead;
                } else {
                    prev = mHead;
                }
            }

            mHead = mHead.next;
        }

        return head;
    }
}