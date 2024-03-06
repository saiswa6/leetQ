class Solution {
    public int trap(int[] height) {

        int length = height.length;
        int left = 0;
        int right = length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int answer = 0;
        while(left < right) {
            if(height[left] <= height[right]) {
                if(height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    answer += (leftMax - height[left]);
                }
                left++;
            } else {
                if(height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    answer += (rightMax - height[right]);
                }
                right--;
            }
        }
        return answer;

        /*
         * int length = height.length;
         * int leftarray[] = new int[length];
         * int rightArray[] = new int[length];
         * 
         * for (int i=0;i<length;i++)
         * {
         * if(i == 0)
         * {
         * leftarray[0] = 0;
         * }
         * else {
         * leftarray[i] = Math.max(leftarray[i-1], height[i-1]);
         * }
         * }
         * 
         * for (int i=length - 1;i>=0;i--)
         * {
         * if(i == length - 1)
         * {
         * rightArray[length - 1] = 0;
         * }
         * else {
         * rightArray[i] = Math.max(rightArray[i+1], height[i+1]);
         * }
         * }
         * int ans = 0;
         * for(int i = 0; i < length-1;i++)
         * {
         * int smallAns = Math.min(leftarray[i],rightArray[i]) - height[i];
         * ans += (smallAns<0?0:smallAns);
         * }
         * return ans;
         */
    }
}