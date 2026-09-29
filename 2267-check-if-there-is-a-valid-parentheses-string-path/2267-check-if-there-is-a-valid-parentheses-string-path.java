class Solution {

    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Total cells in path = m + n - 1
        // Valid parentheses string must have even length
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // dp[row][col][balance]
        boolean[][][] dp = new boolean[m][n][m + n];

        // Starting cell must be '('
        if (grid[0][0] == ')') {
            return false;
        }

        dp[0][0][1] = true;

        for (int row = 0; row < m; row++) {

            for (int col = 0; col < n; col++) {

                // Starting cell already initialized
                if (row == 0 && col == 0) {
                    continue;
                }

                for (int balance = 0; balance < m + n; balance++) {

                    int newBalance;

                    if (grid[row][col] == '(') {
                        newBalance = balance + 1;
                    } else {
                        newBalance = balance - 1;
                    }

                    // Balance negative means invalid
                    if (newBalance < 0 || newBalance >= m + n) {
                        continue;
                    }

                    // Come from top
                    if (row > 0 && dp[row - 1][col][balance]) {
                        dp[row][col][newBalance] = true;
                    }

                    // Come from left
                    if (col > 0 && dp[row][col - 1][balance]) {
                        dp[row][col][newBalance] = true;
                    }
                }
            }
        }

        // At the end, balance must be exactly 0
        return dp[m - 1][n - 1][0];
    }
}