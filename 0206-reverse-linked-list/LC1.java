/*
Approach 1: Iterative
Intuition
Assume that we have linked list 1 → 2 → 3 → Ø, we would like to change it to Ø ← 1 ← 2 ← 3.

 - While traversing the list, we can change the current node's next pointer to point to its previous element. Since a node does not have reference to its previous node, we must store its previous element beforehand. 
We also need another pointer to store the next node before changing the reference. Do not forget to return the new head reference at the end!
*/
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}

/*
Complexity analysis
Time complexity : O(n)
Assume that nnn is the list's length, the time complexity is O(n)

Space complexity : O(1)
*/
