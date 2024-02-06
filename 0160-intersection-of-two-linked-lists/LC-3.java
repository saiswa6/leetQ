/*
Approach 3: Two Pointers
Intuition
- Interview Tip: Approach 3 is essentially a "medium" solution to an "easy" problem. Note that approach 2 is probably sufficient for an interview if you are fairly new to programming (for example, you're applying for an internship during your early years of college). If you're more experienced, it might also be sufficient, but your safest bet would be to also know Approach 3, and to be able to apply the intuition behind it to similar problems. While it might initially look scary, you'll be fine with it once you have a think about it and try and draw a few examples.
- We know that we've now fully optimized the time complexity: it's impossible to do better than O(N+M) as, in the worst case, we'll need to look at every node at least once. But, is there a way we can get the space complexity down to O(1) while maintaining that awesome O(N+M) time complexity that we just achieved? It turns out that there is!

Observe that while list A and list B could be different lengths, that the shared "tail" following the intersection has to be the same length.

- Imagine that we have two linked lists, A and B, and we know that their lengths are N and M respectively (these can be calculated with O(1) space and in time proportional to the length of the list). We'll imagine that N=5 and M=8
- Because the "tails" must be the same length, we can conclude that if there is an intersection, then the intersection node will be one of these 5 possibilities.
= So, to check for each of these pairs, we would start by setting a pointer at the start of the shorter list, and a pointer at the first possible matching node of the longer list. The position of this node is simply the difference between the two lengths, that is, ∣M−N∣.
- Then, we just need to step the two pointers through the list, each time checking whether or not the nodes are the same.
- In code, we could write this algorithm with 4 loops, one after the other, each doing the following:
  1. Calculate N; the length of list A.
  2. Calculate M; the length of list B.
  3. Set the start pointer for the longer list.
  4. Step the pointers through the list together.

- While this would have a time complexity of O(N+M) and a space complexity of O(1) and would be fine for an interview, we can still simplify the code a bit! As some quick reassurance, most people will struggle to come up with this next part by themselves. It takes practice and seeing lots of linked list and other math problems.
- If we say that ccc is the shared part, aaa is exclusive part of list A and bbb is exclusive part of list B, then we can have one pointer that goes over a + c + b and the other that goes over b + c + a. Have a look at the diagram below, and this should be fairly intuitive.
- This is the above algorithm in disguise - one pointer is essentially measuring the length of the longer list, and the other is measuring the length of the shorter list, and then placing the start pointer for the longer list. Then both are stepping through the list together. By seeing the solution in this way though, we can now implement it as a single loop.

Algorithm
1. Set pointer pA to point at headA.
2. Set pointer pB to point at headB.
3. While pA and pB are not pointing at the same node:
   1. If pA is pointing to a null, set pA to point to headB.
   2. Else, set pA to point at pA.next.
   3. If pB is pointing to a null, set pB to point to headA.
   4. Else, set pB to point at pB.next.
4. return the value pointed to by pA (or by pB; they're the same now).

Complexity Analysis
Let N be the length of list A and MMM be the length of list B.
Time complexity : O(N+M)).
- In the worst case, each list is traversed twice giving 2⋅M+2⋅N2 \cdot M + 2 \cdot N2⋅M+2⋅N, which is equivalent to O(N+M)O(N + M)O(N+M). This is because the pointers firstly go down each list so that they can be "lined up" and then in the second iteration, the intersection node is searched for.
- An interesting observation you might have made is that when the lists are of the same length, this algorithm only traverses each list once. This is because the pointers are already "lined up" from the start, so the additional pass is unnecessary.

Space complexity : O(1).
- We aren't allocating any additional data structures, so the amount of extra space used does not grow with the size of the input. For this reason, Approach 3 is better than Approach 2.
*/

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
        // Note: In the case lists do not intersect, the pointers for A and B
        // will still line up in the 2nd iteration, just that here won't be
        // a common node down the list and both will reach their respective ends
        // at the same time. So pA will be NULL in that case.
    }
}
