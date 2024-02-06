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
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> hs = new HashSet<>();
        ListNode iteratorA = headA;
        while(iteratorA != null) {
            hs.add(iteratorA);
            iteratorA = iteratorA.next;
        }

        ListNode iteratorB = headB;
        while(iteratorB != null) {
            if(hs.contains(iteratorB)) {
                return iteratorB;
            }
            iteratorB = iteratorB.next;
        }

        return null;
    }
}