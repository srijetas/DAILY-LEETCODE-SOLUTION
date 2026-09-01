import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sx = 0, sy = 0;
        int litterCount = 0;

        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    sx = i;
                    sy = j;
                } else if (c == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        int totalMask = (1 << litterCount) - 1;

        boolean[][][][] visited =
                new boolean[m][n][energy + 1][1 << litterCount];

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{sx, sy, energy, 0});
        visited[sx][sy][energy][0] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] cur = queue.poll();

                int x = cur[0];
                int y = cur[1];
                int e = cur[2];
                int mask = cur[3];

                if (mask == totalMask) {
                    return moves;
                }

                if (e == 0) continue;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }

                    char cell = classroom[nx].charAt(ny);

                    if (cell == 'X') continue;

                    int nextEnergy = e - 1;
                    int nextMask = mask;

                    if (cell == 'R') {
                        nextEnergy = energy;
                    }

                    if (cell == 'L') {
                        nextMask |= (1 << litterId[nx][ny]);
                    }

                    if (!visited[nx][ny][nextEnergy][nextMask]) {
                        visited[nx][ny][nextEnergy][nextMask] = true;
                        queue.offer(new int[]{
                            nx, ny, nextEnergy, nextMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}