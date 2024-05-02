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

// My Solution. See clear solution below.

class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
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

// Clear Solution 
/*
Intution: Basic idea is just group all the nodes one for even indices and one for odd indices and then the even indices group will come after odd indices group.

Approach 1: Let Split our linked list into odd even index element list then concat even index element list head to odd index element list tail (It is still efficient...beats 100% submission)
Time Complexity : O(n)
Space Complexity : O(1)
*/
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head==null || head.next==null) return head;
        
        ListNode oddHead = null, oddTail = null;
        ListNode evenHead = null, evenTail = null;
        ListNode curr = head;
        int i = 1;
        while(curr!=null){
			// generate the odd indices list
            if(i%2==1){
                if(oddHead==null){
                    oddHead = curr;
                    oddTail = curr;
                }
                else{
                    oddTail.next = curr;
                    oddTail = oddTail.next;
                }
            }
			// generate the even indices list
            else{
                if(evenHead==null){
                    evenHead = curr;
                    evenTail = curr;
                }
                else{
                    evenTail.next = curr;
                    evenTail = evenTail.next;
                }
            }
            curr = curr.next;
            i++;
        }
        
        evenTail.next = null;     // there should not be any node after even tail
        oddTail.next  = evenHead;   // join even list after odd
        return oddHead;
    
    }
}
