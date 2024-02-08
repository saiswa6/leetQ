/*
Approach 1 : Check Subsequence
For each string, macth it with the pattern and pass the result.

The match process uses i for query pointer and j for pattern pointer, each iteration;
  1. If current char query[i] matches pattern[j], increase pattern pointer;
  2. if does not match and query[i] is lowercase, keep going;
  3. if does not match and query[i] is captalized, we should return false.
If this pattern matches, j should equal length of pattern at the end.

Possible improvement, special thanks to @Sithis:
1. Use new ArrayList<>(queries.length) to allocate capacity up-front. This can avoid resizing and copying as the size of the array grows.
2. queryArr[i] >= 'A' && queryArr[i] <= 'Z' can be replaced by built-in static method Character.isUpperCase().

Note: It might seem wrong to match the first character that matches pattern rather than one that occurs later, but it doesn't make a difference as we can add lowercase letters at any index thus we pick the first matched letter.
eg: pattern="NoT" query="NropoT"

We can pick either the first 'o' from query to match the pattern or the second 'o', it doesn't matter which one we choose in this case and hence the solution above works. We pick the first 'o' and wait for the next letter to match the pattern and consider all other lowercase letters in beween as injected.
*/

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList<>();
        
        char[] patternArr = pattern.toCharArray();
        for (String query : queries) {
            boolean isMatch = match(query.toCharArray(), patternArr);
            res.add(isMatch);
        }
        
        return res;
    }
    
    private boolean match(char[] queryArr, char[] patternArr) {
        int j = 0;
        for (int i = 0; i < queryArr.length; i++) {
            if (j < patternArr.length && queryArr[i] == patternArr[j]) {
                j++;
            } else if (queryArr[i] >= 'A' && queryArr[i] <= 'Z') {
                return false;
            }
        }
        
        return j == patternArr.length;
    }
}

/// Similar using while 
private boolean match(String query, String pattern) {
        char[] q = query.toCharArray();
        char[] p = pattern.toCharArray();
        int i = 0, j = 0;
        while(i < q.length) {
            if(j < p.length && q[i] == p[j]) {
                i++;
                j++;
            }else if(q[i] >= 'A' && q[i] <= 'Z') {
                return false;
            }else {
                i++;
            }
            
        }

//// Similar

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList<>();
        for (String query : queries)
            res.add(isMatch(query, pattern));
        return res;
    }

    private boolean isMatch(String query, String pattern) {
        int i = 0;
        for (char c: query.toCharArray()) {
            if (i < pattern.length() && c == pattern.charAt(i))
                i++;
            else if (c < 'a')
                return false;
        }
        return i == pattern.length();
    }
