class Solution {
    int n;
    int[] suffix;
    int[][] dp;

    public int stoneGameII(int[] piles) {
        n = piles.length;

        // Suffix sum
        suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        dp = new int[n][n + 1];

        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }

        return solve(0, 1);
    }

    private int solve(int i, int M) {
        if (i >= n) {
            return 0;
        }

        // Can take all remaining piles
        if (i + 2 * M >= n) {
            return suffix[i];
        }

        if (dp[i][M] != -1) {
            return dp[i][M];
        }

        int ans = 0;

        for (int x = 1; x <= 2 * M; x++) {
            int opponent = solve(i + x, Math.max(M, x));

            int current = suffix[i] - opponent;

            ans = Math.max(ans, current);
        }

        return dp[i][M] = ans;
    }
}