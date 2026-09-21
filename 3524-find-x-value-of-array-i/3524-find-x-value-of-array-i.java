class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] ans = new long[k];

        // dp[r] = current position par end hone wale
        // subarrays jinka product % k = r
        long[] dp = new long[k];

        for (int num : nums) {

            long[] newDp = new long[k];

            // 1. Sirf current element ka subarray
            int rem = num % k;
            newDp[rem]++;

            // 2. Purane subarrays ke end mein current element add karo
            for (int r = 0; r < k; r++) {
                int newRem = (r * rem) % k;
                newDp[newRem] += dp[r];
            }

            // Current ending-at-i subarrays ko answer mein add karo
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            dp = newDp;
        }

        return ans;
    }
}