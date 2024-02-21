/*
Approach #1: Brute Force [Memory Limit Exceeded]
Intuition and Algorithm:
Create the multiplication table and sort it, then take the kth element.

Complexity Analysis
Time Complexity: O(m∗n) to create the table, and O(m∗nlog⁡(m∗n)) to sort it.
Space Complexity: O(m∗n) to store the table.
*/

class Solution {
    public int findKthNumber(int m, int n, int k) {
        int[] table = new int[m*n];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                table[(i - 1) * n + j - 1] = i * j;
            }
        }
        Arrays.sort(table);
        return table[k-1];
    }
}
