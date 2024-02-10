/*
Approach 1: Brute Force
Intuition
- Find every possible set of triplets (i,j,k) such that i<j<k and test for the condition.

Complexity analysis
- Time complexity: O(n3)
The total number of such triplets is (n3), which is n!(n−3)!×3!=n×(n−1)×(n−2)/6. Therefore, the time complexity of the brute force approach is O(n3).

Space complexity: O(1)
*/
