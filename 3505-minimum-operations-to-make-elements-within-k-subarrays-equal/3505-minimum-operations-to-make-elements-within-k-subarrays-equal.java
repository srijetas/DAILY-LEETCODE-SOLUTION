import java.util.*;

class Solution {

    TreeMap<Integer, Integer> left = new TreeMap<>();
    TreeMap<Integer, Integer> right = new TreeMap<>();

    int leftSize = 0, rightSize = 0;
    long leftSum = 0, rightSum = 0;

    void addMap(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    void removeMap(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);

        if (count == 1)
            map.remove(val);
        else
            map.put(val, count - 1);
    }

    void add(int val) {

        if (left.isEmpty() || val <= left.lastKey()) {
            addMap(left, val);
            leftSize++;
            leftSum += val;
        } else {
            addMap(right, val);
            rightSize++;
            rightSum += val;
        }

        balance();
    }

    void remove(int val) {

        if (left.containsKey(val)) {
            removeMap(left, val);
            leftSize--;
            leftSum -= val;
        } else {
            removeMap(right, val);
            rightSize--;
            rightSum -= val;
        }

        balance();
    }

    void balance() {

        while (leftSize > rightSize + 1) {

            int val = left.lastKey();

            removeMap(left, val);
            leftSize--;
            leftSum -= val;

            addMap(right, val);
            rightSize++;
            rightSum += val;
        }

        while (leftSize < rightSize) {

            int val = right.firstKey();

            removeMap(right, val);
            rightSize--;
            rightSum -= val;

            addMap(left, val);
            leftSize++;
            leftSum += val;
        }
    }

    long getCost() {

        long median = left.lastKey();

        return median * leftSize - leftSum
             + rightSum - median * rightSize;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        // First window
        for (int row = 0; row < x; row++) {
            add(nums[row]);
        }

        cost[0] = getCost();

        // Sliding Window
        for (int row = x; row < n; row++) {

            remove(nums[row - x]);
            add(nums[row]);

            cost[row - x + 1] = getCost();
        }

        // Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (int row = 0; row <= n; row++) {
            Arrays.fill(dp[row], INF);
        }

        for (int row = 0; row <= n; row++) {
            dp[row][0] = 0;
        }

        for (int row = 1; row <= n; row++) {

            for (int col = 1; col <= k; col++) {

                // Skip current element
                dp[row][col] = dp[row - 1][col];

                // Select a window of size x
                if (row >= x && dp[row - x][col - 1] != INF) {

                    dp[row][col] = Math.min(
                        dp[row][col],
                        dp[row - x][col - 1] + cost[row - x]
                    );
                }
            }
        }

        return dp[n][k];
    }
}