/*
Approach 3: Trie
// Learn Trie , check clear solution from LCEditorial.

- Trie, also known as prefix tree, is a tree-like data structure which is often used to store strings (In this problem, we store arrays of integers instead of strings). 
  The key advantage of trie is its efficient search time, which can be achieved in O(n)) time where nnn is the length of the array. Trie works by storing each element of the array in a separate node, 
  and each node has an array of children representing the possible characters that can follow the current element.

- Depending on the requirements, we can modify the original trie by adding more elements. In this problem, we need to determine the frequency of each row, so we add a variable called count into the trie node. 
  To construct the trie, we traverse each row of the grid and insert the row into the trie by traversing down the trie based on each element in the row. At the end of the row, we increment count associated with 
  the last node in the trie to indicate that we have recorded the occurrence of this row.
*/

class TrieNode {
    int count;
    Map<Integer, TrieNode> children;

    public TrieNode() {
        this.count = 0;
        this.children = new HashMap<>();
    }
}

class Trie {
    TrieNode trie;

    public Trie() {
        this.trie = new TrieNode();
    }

    public void insert(int[] array) {
        TrieNode myTrie = this.trie;
        for (int a : array) {
            if (!myTrie.children.containsKey(a)) {
                myTrie.children.put(a, new TrieNode());
            }
            myTrie = myTrie.children.get(a);
        }
        myTrie.count += 1;
    }

    public int search(int[] array) {
        TrieNode myTrie = this.trie;
        for (int a : array) {
            if (myTrie.children.containsKey(a)) {
                myTrie = myTrie.children.get(a);
            } else {
                return 0;
            }
        }
        return myTrie.count;
    }
}

class Solution {
    public int equalPairs(int[][] grid) {
        Trie myTrie = new Trie();
        int count = 0, n = grid.length;
        
        for (int[] row : grid) {
            myTrie.insert(row);
        }
        
        for (int c = 0; c < n; ++c) {
            int[] colArray = new int[n];
            for (int r = 0; r < n; ++r) {
                colArray[r] = grid[r][c];
            }
            count += myTrie.search(colArray);
        }
    
        return count;
    }
}

/*
Complexity Analysis
Let n×n be the size of grid.
Time complexity: O(n2)

The length of input rows is fixed to nnn, the time complexity of building a trie for nnn rows is O(n2), since we need to traverse each element in the array to insert it into the trie.
The time complexity of search an array of length nnn is O(n) as we need to iterate over the entire array in the worst-case scenario.

Space complexity: O(n2)
In a trie, each node represents a number. Therefore, for n rows of length n, the trie has n2 nodes in the worst-case scenario.

*/
