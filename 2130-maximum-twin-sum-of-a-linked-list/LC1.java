/*
Overview
- We are given the head of a linked list with even length.
- The problem mentions that the ith  node (0-indexed) of the linked list is known as the twin of the (n−1−i)th node, if 0 <= i <= (n / 2) - 1.
- Our task is to return the maximum sum of a node and its twin among all the node and twin pairs.
*/

/*
Approach 1: Using List Of Integers
Intuition
- We can see that the ith   node from the start is the twin of the ith node from the end. The first node is the twin of the last node, the second node is the twin of the second last node, and so on. Because we are guaranteed an even number of nodes in the linked list, each node in the first half has a twin in the second half.

- An intuitive solution is to iterate over the entire linked list and push the value of each node into a list of integers. The list of integers is then iterated over using two pointers, i and j. The pointer i points to the beginning of the list, while j points to the end.

- To get the twin sum of the pair under consideration, we add the values indicated by the pointers. To get the next pair of twins, we increment i and decrement j and try to update the answer wherever we can with the twin sum. We repeat this process until we have covered all of the twin pairs, i.e., until i >= j.
*/

class Solution {
    public int pairSum(ListNode head) {
        ListNode current = head;
        List<Integer> values = new ArrayList<>();

        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        int i = 0, j = values.size() - 1;
        int maximumSum = 0;
        while (i < j) {
            maximumSum = Math.max(maximumSum, values.get(i) + values.get(j));
            i++;
            j--;
        }

        return maximumSum;
    }
}

/*
Complexity Analysis
Here, n is the number of nodes in the linked list.

Time complexity: O(n)
Iterating over the entire linked list and pushing all the node values in values takes O(n) time.
We iterate over the first half of the linked list to find the maximum twin sum, which also takes O(n) time

Space complexity: O(n)
The values list takes O(n)) space as we push nnn elements into it.
*/
