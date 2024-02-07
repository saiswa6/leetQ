/*
Approach 2: Two Pointers, One Pass
Intuition
- Rather than splitting the string all at once with the split() function, we could also split the string on the fly, through which we only need to iterate through the revisions once.
- The idea is that we split the string chunk by chunk, i.e. each trunk represents a revision in the version number. The moment we retrieve a trunk from each string, we then compare them.
- In this way, one could move along both strings in parallel, and retrieve and compare corresponding chunks. Once both strings are parsed, the comparison is done as well.
- As a result, the process can be done in a single pass.

Algorithm
- First, we define a function named get_next_chunk(version, n, p), which is to retrieve the next chunk in the string.
- This function takes three arguments: the input string version, its length n, and a pointer p set to the first character of the chunk to retrieve. It returns an integer chunk in between the pointer p and the next dot. To help with the iteration, it returns a pointer set to the first character of the next chunk.

Here is how one could solve the problem using this function:
1. Set a pointer p1 pointed to the beginning of string version1 and a pointer p2 to the beginning of string version2: p1 = p2 = 0.
2. Iterate over both strings in parallel. While p1 < n1 or p2 < n2:
   - Retrieve the next chunk i1 from string version1 and next chunk i2 from string version2 using the above-defined get_next_chunk function.
   - Compare i1 and i2. If they are not equal, return 1 or -1.
3. If we're here, the versions are equal. Return 0.

Now let's implement our get_next_chunk(version, n, p) function:
- The beginning of the chunk is marked by the pointer p. If p is set to the end of the string, the string is already parsed. To continue the comparison, let's add a virtual .0 at the end of this string by returning 0.
- If p is not at the end of the string, move the pointer p_end along the string to find the end of the chunk.
- Return the chunk version.substring(p, p_end).

Complexity Analysis
Time complexity : O(max⁡(N,M)), where NNN and MMM are the lengths of the input strings respectively. It's a one-pass solution.
Space complexity : O(max⁡(N,M)).
   - Despite the fact that we did not keep arrays of revision numbers, we still need some additional space to store a substring of the input string for integer conversion.
     In the worst case, the substring could be of the original string as well.
*/
class Solution {
  public Pair<Integer, Integer> getNextChunk(String version, int n, int p) {
    //If the pointer is set to the end of the string, return 0
    if (p > n - 1) {
      return new Pair(0, p);
    }

    // Find the end of chunk
    int i, pEnd = p;
    while (pEnd < n && version.charAt(pEnd) != '.') {
      ++pEnd;
    }

    // Retrieve the chunk
    if (pEnd != n - 1) {
      i = Integer.parseInt(version.substring(p, pEnd));
    } else {
      i = Integer.parseInt(version.substring(p, n));
    }

    // Find the beginning of the next chunk
    p = pEnd + 1;

    return new Pair(i, p);
  }

  public int compareVersion(String version1, String version2) {
    int p1 = 0, p2 = 0;
    int n1 = version1.length(), n2 = version2.length();

    // Compare versions
    int i1, i2;
    Pair<Integer, Integer> pair;
    while (p1 < n1 || p2 < n2) {
      pair = getNextChunk(version1, n1, p1);
      i1 = pair.getKey();
      p1 = pair.getValue();

      pair = getNextChunk(version2, n2, p2);
      i2 = pair.getKey();
      p2 = pair.getValue();
      if (i1 != i2) {
        return i1 > i2 ? 1 : -1;
      }
    }

    // The versions are equal
    return 0;
  }
}
