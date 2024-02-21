/*
Approach #2: Next Heap [Time Limit Exceeded]
Intuition
Maintain a heap of the smallest unused element of each row. Then, finding the next element is a pop operation on the heap.

Algorithm
- Our heap is going to consist of elements (val, root), where val is the next unused value of that row, and root was the starting value of that row.
- We will repeatedly find the next lowest element in the table. To do this, we pop from the heap. Then, if there's a next lowest element in that row, we'll put that element back on the heap.

Complexity Analysis
Time Complexity: O(k∗log⁡m)=O(mnlog⁡m). Our initial heapify operation is O(m). Afterwards, each pop and push is O(log⁡m), and our outer loop is O(k)=O(m∗n).
Space Complexity: O(m). Our heap is implemented as an array with mmm elements.
*/
class Solution {
    public int findKthNumber(int m, int n, int k) {
        PriorityQueue<Node> heap = new PriorityQueue<Node>(m,
            Comparator.<Node> comparingInt(node -> node.val));

        for (int i = 1; i <= m; i++) {
            heap.offer(new Node(i, i));
        }

        Node node = null;
        for (int i = 0; i < k; i++) {
            node = heap.poll();
            int nxt = node.val + node.root;
            if (nxt <= node.root * n) {
                heap.offer(new Node(nxt, node.root));
            }
        }
        return node.val;
    }
}

class Node {
    int val;
    int root;
    public Node(int v, int r) {
        val = v;
        root = r;
    }
}
