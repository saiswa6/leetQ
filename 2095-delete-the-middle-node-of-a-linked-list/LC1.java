/*
We are also given the definition of middle node. Suppose there are n nodes in the linked list, then the index of the middle node middleIndex is: middleIndex = floor(n/2) − 1 , if we use 0-base indexing.

Approach 1: 2 Passes
Intuition
- We make the first iteration, starting from head, goint through the entire linked list and getting the total number of nodes (let's say count). 
  According to the definition provided, the index of the middle node is middleIndex=floor(count / 2)−1
- Now we make a second iteration to the predecessor node of the middle node, which means that we stop at index middleIndex−1
- Once we reach the predecessor node of the middle node, we can remove the middle node from the linked list.
*/

class Solution {
    public ListNode deleteMiddle(ListNode head) {
        // Edge case: return None if there is only one node
        if (head.next == null)
            return null;
            
        int count = 0;
        ListNode p1 = head, p2 = head;
        
        // First pass, count the number of nodes in the linked list using 'p1'.
        while (p1 != null) {
            count += 1;
            p1 = p1.next;
        }
        
        // Get the index of the node to be deleted.
        int middleIndex = count / 2;
        
        // Second pass, let 'p2' move toward the predecessor of the middle node.
        for (int i = 0; i < middleIndex - 1; ++i)
            p2 = p2.next;
        
        // Delete the middle node and return 'head'.
        p2.next = p2.next.next;
        return head;
    }
}

/*
Complexity Analysis
Let n be the length of the input linked list.
Time complexity: O(n)
 - We iterate over the linked list twice, the first time traversing the entire linked list and the second traversing half of it. Hence there are a total of O(n) steps.
 - In each step, we move a pointer forward by one node, which takes constant time.
 - Remove the middle node takes a constant amount of time.
 - In summary, the overall time complexity is O(n)

Space complexity: O(1)
We only need two pointers, thus the space complexity is O(1)
*/
