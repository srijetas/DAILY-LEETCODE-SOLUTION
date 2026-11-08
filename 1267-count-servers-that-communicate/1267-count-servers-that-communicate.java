class Solution {
    public int countServers(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[] row = new int[rows];
        int[] col = new int[cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    row[r]++;
                    col[c]++;
                }
            }
        }

        int ans = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1 && (row[r] > 1 || col[c] > 1)) {
                    ans++;
                }
            }
        }

        return ans;
    }
}