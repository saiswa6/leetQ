/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

// Using HAsh Set
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> hs = new HashSet<>();
        ListNode iteratorA = headA;
        while(iteratorA != null) {  // Pass all the elements of Linked List1 to Hash Set
            hs.add(iteratorA);
            iteratorA = iteratorA.next;
        }

        ListNode iteratorB = headB;
        while(iteratorB != null) { // Iterate through Linked List2 and see if any element matches with the HashSet. If yes, that's the intersection point.
            if(hs.contains(iteratorB)) {
                return iteratorB;
            }
            iteratorB = iteratorB.next;
        }

        return null;
    }
}
