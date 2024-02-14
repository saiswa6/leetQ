/*
Approach #1 (Linear Scan) [Time Limit Exceeded]
The straight forward way is to brute force it by doing a linear scan.

Complexity analysis

Time complexity : O(n).
Assume that isBadVersion(version) takes constant time to check if a version is bad. It takes at most n−1 checks, therefore the overall time complexity is O(n)

Space complexity : O(1)
*/

public int firstBadVersion(int n) {
    for (int i = 1; i < n; i++) {
        if (isBadVersion(i)) {
            return i;
        }
    }
    return n;
}
