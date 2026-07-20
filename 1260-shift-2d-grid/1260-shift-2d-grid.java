import java.util.*;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        k %= total;

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            ans.add(new ArrayList<>());
        }

        for (int i = 0; i < total; i++) {

            int oldRow = i / n;
            int oldCol = i % n;

            int newIndex = (i + k) % total;
            int newRow = newIndex / n;
            int newCol = newIndex % n;

            while (ans.get(newRow).size() < newCol + 1) {
                ans.get(newRow).add(0);
            }

            ans.get(newRow).set(newCol, grid[oldRow][oldCol]);
        }

        return ans;
    }
}