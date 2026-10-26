class Solution {
public int countIslands(int[][] grid, int k) {
int rows = grid.length;
int cols = grid[0].length;
int count = 0;

    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
            if (grid[row][col] > 0) {
                long sum = dfs(grid, row, col, rows, cols);
                if (sum % k == 0) {
                    count++;
                }
            }
        }
    }
    return count;
}
private long dfs(int[][] grid, int row, int col,
                 int rows, int cols) {
    if (row < 0 || row >= rows ||
        col < 0 || col >= cols ||
        grid[row][col] == 0) {
        return 0;
    }
    long value = grid[row][col];
    // Mark this cell as visited
    grid[row][col] = 0;
    value += dfs(grid, row - 1, col, rows, cols);
    value += dfs(grid, row + 1, col, rows, cols);
    value += dfs(grid, row, col - 1, rows, cols);
    value += dfs(grid, row, col + 1, rows, cols);
    return value;
}

}