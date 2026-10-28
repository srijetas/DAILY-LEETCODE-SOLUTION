class Solution {
    public int minCost(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int MAX = 1024;

        boolean[][][] dp = new boolean[rows][cols][MAX];

        dp[0][0][grid[0][0]] = true;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (row == 0 && col == 0) {
                    continue;
                }

                int value = grid[row][col];

                if (row > 0) {
                    for (int xor = 0; xor < MAX; xor++) {
                        if (dp[row - 1][col][xor]) {
                            dp[row][col][xor ^ value] = true;
                        }
                    }
                }

                if (col > 0) {
                    for (int xor = 0; xor < MAX; xor++) {
                        if (dp[row][col - 1][xor]) {
                            dp[row][col][xor ^ value] = true;
                        }
                    }
                }
            }
        }

        for (int xor = 0; xor < MAX; xor++) {
            if (dp[rows - 1][cols - 1][xor]) {
                return xor;
            }
        }

        return 0;
    }
}