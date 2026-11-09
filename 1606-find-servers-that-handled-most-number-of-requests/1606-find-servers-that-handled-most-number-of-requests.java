import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int[] count = new int[k];

        for (int server = 0; server < k; server++) {
            available.add(server);
        }

        for (int i = 0; i < arrival.length; i++) {

            // Release servers that have finished
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= arrival[i]) {

                int[] temp = busy.poll();
                available.add(temp[1]);
            }

            // All servers are busy
            if (available.isEmpty()) {
                continue;
            }

            // Find the next available server
            int server = i % k;

            Integer chosen = available.ceiling(server);

            // Wrap around if necessary
            if (chosen == null) {
                chosen = available.first();
            }

            available.remove(chosen);

            // Update request count
            count[chosen]++;

            // Store finishing time and server ID
            busy.offer(new int[]{
                arrival[i] + load[i], chosen
            });
        }

        // Find maximum requests handled
        int max = 0;

        for (int server = 0; server < k; server++) {
            max = Math.max(max, count[server]);
        }

        // Collect busiest servers
        List<Integer> ans = new ArrayList<>();

        for (int server = 0; server < k; server++) {
            if (count[server] == max) {
                ans.add(server);
            }
        }

        return ans;
    }
}