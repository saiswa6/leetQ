/*
Intuition
Put the odd nodes in a linked list and the even nodes in another. Then link the evenList to the tail of the oddList.

Algorithm
- A well-formed LinkedList need two pointers head and tail to support operations at both ends. 
  The variables head and odd are the head pointer and tail pointer of one LinkedList we call oddList; the variables evenHead and even are the head pointer and tail pointer of another LinkedList we call evenList. 
  The algorithm traverses the original LinkedList and put the odd nodes into the oddList and the even nodes into the evenList. To traverse a LinkedList we need at least one pointer as an iterator for the current node. 
  But here the pointers odd and even not only serve as the tail pointers but also act as the iterators of the original list.
*/

public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}

/*
Complexity Analysis
Time complexity : O(n)O(n)O(n). There are total nnn nodes and we visit each node once.
Space complexity : O(1)O(1)O(1). All we need is the four pointers.
*/

//Other Good Solution (https://leetcode.com/problems/odd-even-linked-list/solutions/1606975/java-2-solutions-explanation-using-image-without-space/?envType=study-plan-v2&envId=leetcode-75)
class Solution {
    public ListNode oddEvenList(ListNode head) {
        
		if(head==null) return head;
		
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        
        while(even!=null && even.next!=null){
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        
        odd.next = evenHead;
        
        return head;
    }
}
