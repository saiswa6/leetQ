class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        Arrays.sort(points,
                (o1, o2) -> {
                    if (o1[1] == o2[1]) {
                        return 0;
                    } else if (o1[1] > o2[1]) {
                        return 1;
                    } else {
                        return -1;
                    }
                });

        int arrows = 1;
        int xStart, xEnd, firstEnd = points[0][1];
        for (int p[] : points) {
            xStart = p[0];
            xEnd = p[1];

            if (xStart > firstEnd) {
                firstEnd = xEnd;
                arrows++;
            }
        }

        return arrows;
    }
}