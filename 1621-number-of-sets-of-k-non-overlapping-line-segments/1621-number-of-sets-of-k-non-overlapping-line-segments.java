class Solution {
    static final int MOD = 1000000007;

    public int numberOfSets(int n, int k) {
        long[][] dp = new long[k + 1][n];

        // With 0 segments, there is 1 way
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int seg = 1; seg <= k; seg++) {
            long sum = 0;

            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    sum = (sum + dp[seg - 1][i - 1]) % MOD;
                }

                dp[seg][i] = (dp[seg][i] + sum) % MOD;

                if (i > 0) {
                    dp[seg][i] = (dp[seg][i] + dp[seg][i - 1]) % MOD;
                }
            }
        }

        return (int) dp[k][n - 1];
    }
}