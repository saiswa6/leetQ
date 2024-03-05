/*
Optimal Strategy to Solve the Problem
- Root-to-left traversal is so-called DFS preorder traversal. To implement it, one has to follow a straightforward strategy Root->Left->Right.
- Since one has to visit all nodes, the best possible time complexity here is linear. Hence all interest here is to improve the space complexity.
- There are 3 ways to implement preorder traversal: iterative, recursive, and Morris.
- Iterative and recursive approaches here do the job in one pass, but they both need up to O(H) space to keep the stack, where HHH is a tree height.
- Morris's approach is a two-pass approach, but it's a constant-space one.

-----------------------
Approach 1: Iterative Preorder Traversal.
Intuition
Here we implement standard iterative preorder traversal with the stack:
- Push root into the stack.
- While the stack is not empty:
   - Pop out a node from the stack and update the current number.
   - If the node is a leaf, update the root-to-leaf sum.
   - Push right and left child nodes into the stack.
- Return root-to-leaf sum.

Note, that
Javadocs recommends to use ArrayDeque and not Stack as a stack implementation
*/
class Solution {
  public int sumNumbers(TreeNode root) {
    int rootToLeaf = 0, currNumber = 0;
    Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque();
    stack.push(new Pair(root, 0));

    while (!stack.isEmpty()) {
      Pair<TreeNode, Integer> p = stack.pop();
      root = p.getKey();
      currNumber = p.getValue();

      if (root != null) {
        currNumber = currNumber * 10 + root.val;
        // if it's a leaf, update root-to-leaf sum
        if (root.left == null && root.right == null) {
          rootToLeaf += currNumber;
        } else {
          stack.push(new Pair(root.right, currNumber));
          stack.push(new Pair(root.left, currNumber));
        }
      }
    }
    return rootToLeaf;
  }
}

/*
Complexity Analysis
Time complexity: O(N) since one has to visit each node.
Space complexity: up to O(H) to keep the stack, where HHH is a tree height.
*/


// Similar
def sumNumbers(self, root: TreeNode) -> int:
        if root is None:
            return 0
        q = deque()
        
        q.append((root, root.val))
        total = 0
        while q:
            
            node, path_sum = q.popleft()
            print(path_sum)
            if node.left is None and node.right is None:
                total += path_sum
                
            if node.left is not None:
                q.append((node.left, path_sum*10+node.left.val))
            if node.right is not None:
                q.append((node.right, path_sum*10+node.right.val))
                    
        return total
