class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];

        dp[0] = 0;

        for (int step = 1; step <= n; step++) {

            dp[step] = dp[step - 1] + costs[step - 1] + 1;

            if (step >= 2) {
                dp[step] = Math.min(dp[step],
                        dp[step - 2] + costs[step - 1] + 4);
            }

            if (step >= 3) {
                dp[step] = Math.min(dp[step],
                        dp[step - 3] + costs[step - 1] + 9);
            }
        }

        return dp[n];
    }
}