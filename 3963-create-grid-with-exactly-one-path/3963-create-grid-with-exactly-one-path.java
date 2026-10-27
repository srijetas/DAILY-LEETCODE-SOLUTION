class Solution {
public String[] createGrid(int m, int n) {
char[][] grid = new char[m][n];

    // Fill the entire grid with obstacles
    for (int row = 0; row < m; row++) {
        for (int col = 0; col < n; col++) {
            grid[row][col] = '#';
        }
    }
    // Make the first row free
    for (int col = 0; col < n; col++) {
        grid[0][col] = '.';
    }
    // Make the last column free
    for (int row = 0; row < m; row++) {
        grid[row][n - 1] = '.';
    }
    // Convert char[][] into String[]
    String[] result = new String[m];
    for (int row = 0; row < m; row++) {
        result[row] = new String(grid[row]);
    }
    return result;
}

}