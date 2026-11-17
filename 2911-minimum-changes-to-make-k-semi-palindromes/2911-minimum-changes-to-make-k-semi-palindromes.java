class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
                
        // g[i][j] stores the min changes to make substring s[i...j] a semi-palindrome
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(g[i], 1_000_000); // Initialize with infinity
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                                
                // Try all valid divisors d of length
                for (int d = 1; d < len; d++) {
                    if (len % d == 0) {
                        int cost = 0;
                        // Check each group formed by divisor d
                        for (int offset = 0; offset < d; offset++) {
                            int l = i + offset;
                            int r = j - d + 1 + offset;
                            while (l < r) {
                                if (chars[l] != chars[r]) {
                                    cost++;
                                }
                                l += d;
                                r -= d;
                            }
                        }
                        g[i][j] = Math.min(g[i][j], cost);
                    }
                }
            }
        }
                
        // dp[i][j] = min changes to partition prefix of length i into j semi-palindromes
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            java.util.Arrays.fill(dp[i], 1_000_000);
        }
        dp[0][0] = 0;
                
        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                for (int h = j - 1; h < i; h++) {
                    dp[i][j] = Math.min(dp[i][j], dp[h][j - 1] + g[h][i - 1]);
                }
            }
        }
                
        return dp[n][k];
    }
}