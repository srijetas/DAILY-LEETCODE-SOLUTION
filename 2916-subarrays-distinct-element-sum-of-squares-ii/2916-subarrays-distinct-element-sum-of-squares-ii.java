class Solution {
    private static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        long[] sum = new long[4 * n];
        long[] sq = new long[4 * n];
        long[] lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int left = last[nums[i]] + 1;

            update(1, 0, n - 1, left, i, sum, sq, lazy);

            last[nums[i]] = i;
            ans = (ans + sq[1]) % MOD;
        }

        return (int) ans;
    }

    private void update(
        int node, int start, int end, int l, int r,
        long[] sum, long[] sq, long[] lazy
    ) {
        if (l > end || r < start) {
            return;
        }

        if (l <= start && end <= r) {
            apply(node, start, end, sum, sq, lazy);
            return;
        }

        push(node, start, end, sum, sq, lazy);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, l, r, sum, sq, lazy);
        update(node * 2 + 1, mid + 1, end, l, r, sum, sq, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    private void apply(
        int node, int start, int end,
        long[] sum, long[] sq, long[] lazy
    ) {
        long add = 1;
        long len = end - start + 1L;

        sq[node] = (
            sq[node] + 2L * add * sum[node] + add * add * len
        ) % MOD;

        sum[node] = (sum[node] + add * len) % MOD;
        lazy[node] = (lazy[node] + add) % MOD;
    }

    private void push(
        int node, int start, int end,
        long[] sum, long[] sq, long[] lazy
    ) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;

        applyLazy(node * 2, start, mid, sum, sq, lazy, lazy[node]);
        applyLazy(node * 2 + 1, mid + 1, end, sum, sq, lazy, lazy[node]);

        lazy[node] = 0;
    }

    private void applyLazy(
        int node, int start, int end,
        long[] sum, long[] sq, long[] lazy,
        long add
    ) {
        long len = end - start + 1L;

        sq[node] = (
            sq[node] + 2L * add * sum[node] + add * add * len
        ) % MOD;

        sum[node] = (sum[node] + add * len) % MOD;
        lazy[node] = (lazy[node] + add) % MOD;
    }
}