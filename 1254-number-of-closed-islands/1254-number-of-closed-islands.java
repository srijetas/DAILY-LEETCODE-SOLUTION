class Solution {
public int closedIsland(int[][] grid) {
int rows = grid.length;
int cols = grid[0].length;

    // Remove all islands connected to the boundary
    for (int row = 0; row < rows; row++) {
        dfs(grid, row, 0);
        dfs(grid, row, cols - 1);
    }
    for (int col = 0; col < cols; col++) {
        dfs(grid, 0, col);
        dfs(grid, rows - 1, col);
    }
    int count = 0;
    // Count remaining closed islands
    for (int row = 1; row < rows - 1; row++) {
        for (int col = 1; col < cols - 1; col++) {
            if (grid[row][col] == 0) {
                dfs(grid, row, col);
                count++;
            }
        }
    }
    return count;
}
private void dfs(int[][] grid, int row, int col) {
    int rows = grid.length;
    int cols = grid[0].length;
    if (row < 0 || row >= rows ||
        col < 0 || col >= cols ||
        grid[row][col] == 1) {
        return;
    }
    grid[row][col] = 1;
    dfs(grid, row + 1, col);
    dfs(grid, row - 1, col);
    dfs(grid, row, col + 1);
    dfs(grid, row, col - 1);
}

}