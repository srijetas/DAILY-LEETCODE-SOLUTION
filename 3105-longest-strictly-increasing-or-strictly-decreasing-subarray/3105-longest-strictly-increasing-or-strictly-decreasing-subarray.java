class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int inc = 1, dec = 1;
        int ans = 1;

        for (int row = 1; row < nums.length; row++) {

            if (nums[row] > nums[row - 1]) {
                inc++;
                dec = 1;
            } 
            else if (nums[row] < nums[row - 1]) {
                dec++;
                inc = 1;
            } 
            else {
                inc = 1;
                dec = 1;
            }

            ans = Math.max(ans, Math.max(inc, dec));
        }

        return ans;
    }
}