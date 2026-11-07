import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        PriorityQueue<int[]> available = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        for (int i = 0; i < n; i++) {
            available.add(new int[]{servers[i], i});
        }

        long time = 0;

        for (int task = 0; task < m; task++) {

            time = Math.max(time, task);

            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] server = busy.poll();
                available.add(new int[]{servers[server[1]], server[1]});
            }

            if (available.isEmpty()) {
                time = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    int[] server = busy.poll();
                    available.add(new int[]{servers[server[1]], server[1]});
                }
            }

            int[] server = available.poll();

            ans[task] = server[1];

            busy.add(new int[]{
                (int)(time + tasks[task]),
                server[1]
            });
        }

        return ans;
    }
}