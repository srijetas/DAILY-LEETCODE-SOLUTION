import java.util.*;

class Solution {

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    static boolean lexicographicallySmaller(
            List<Integer> a, List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }

    static State better(State a, State b) {

        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        return lexicographicallySmaller(a.indices, b.indices)
                ? a : b;
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // [start, end, weight, original index]
        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        // Sort by end time
        Arrays.sort(a, (x, y) -> {
            if (x[1] != y[1]) {
                return Integer.compare(x[1], y[1]);
            }
            return Integer.compare(x[3], y[3]);
        });

        // prev[i] = last interval with end < current start
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {

            int left = 0;
            int right = i - 1;
            int ans = -1;

            while (left <= right) {

                int mid = left + (right - left) / 2;

                // Boundary sharing = overlapping
                if (a[mid][1] < a[i][0]) {
                    ans = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            prev[i] = ans;
        }

        /*
         * dp[k][i] =
         * best answer using first i intervals
         * and selecting at most k intervals.
         */
        State[][] dp = new State[5][n + 1];

        // Initialize ALL states
        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                dp[k][i] = new State(
                        0,
                        new ArrayList<>()
                );
            }
        }

        for (int k = 1; k <= 4; k++) {

            for (int i = 1; i <= n; i++) {

                // 1. Don't take current interval
                State skip = dp[k][i - 1];

                // 2. Take current interval
                int idx = i - 1;

                State previous;

                if (prev[idx] == -1) {
                    previous = dp[k - 1][0];
                } else {
                    previous = dp[k - 1][prev[idx] + 1];
                }

                List<Integer> newIndices =
                        new ArrayList<>(previous.indices);

                newIndices.add(a[idx][3]);

                // Keep indices sorted
                Collections.sort(newIndices);

                State take = new State(
                        previous.score + a[idx][2],
                        newIndices
                );

                dp[k][i] = better(skip, take);
            }
        }

        // Convert List<Integer> to int[]
        List<Integer> result = dp[4][n].indices;

        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}