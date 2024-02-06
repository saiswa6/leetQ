/*
// Best 2 pointers solution
*/

    public boolean isLongPressedName(String name, String typed) {
        int i = 0, m = name.length(), n = typed.length();
        for (int j = 0; j < n; ++j) // Iterate through typed array
            if (i < m && name.charAt(i) == typed.charAt(j))  // First check whether corresponding letters of name and typed are same, if yes, increment name pointer. If no, go down.
                ++i;
            else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1)) // Second check whether neighbour letters of typed are same, if no, return false
                return false;
        return i == m; // named pointer shall reach end, then only returnn true, otherwise false.
    }
