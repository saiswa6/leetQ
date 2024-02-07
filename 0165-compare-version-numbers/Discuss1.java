/*
. means any character, "\\." means "."
*/

public int compareVersion(String version1, String version2) {
    String[] levels1 = version1.split("\\.");
    String[] levels2 = version2.split("\\.");
    
    int length = Math.max(levels1.length, levels2.length);
    for (int i=0; i<length; i++) {
    	Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
    	Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
    	int compare = v1.compareTo(v2);
    	if (compare != 0) {
    		return compare;
    	}
    }
    
    return 0;
}

// Submission 0ms Solution
// Directly handle with chars only instead of split.
// This handles in 1 iteration only.
class Solution {
    public int compareVersion(String version1, String version2) {
        int i = 0, j = 0, m = version1.length(), n = version2.length();
        while (i < m || j < n) {
            int temp1 = 0, temp2 = 0;
            while (i < m && version1.charAt(i) != '.')
                temp1 = temp1 * 10 + version1.charAt(i++) - '0';
            while (j < n && version2.charAt(j) != '.')
                temp2 = temp2 * 10 + version2.charAt(j++) - '0';
            if (temp1 > temp2)
                return 1;
            if (temp1 < temp2)
                return -1;
            i++;
            j++;
        }
        return 0;
    }


  /*
- In regex, Dot(.) is a special meta-character which matches everything.

- Since String.split works on Regex, so you need to escape it with backslash if you want to match a dot.

- System.out.println(input.split("\\."))
  */
}
