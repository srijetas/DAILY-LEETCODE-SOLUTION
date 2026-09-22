class Solution {

    static class Node {
        int product;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    int n, k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Update persists
            update(1, 0, n - 1, index, value);

            // Get information for nums[start ... n-1]
            Node res = query(1, 0, n - 1, start, n - 1);

            ans[i] = res.cnt[x];
        }

        return ans;
    }

    // ---------------- BUILD ----------------

    void build(int node, int l, int r, int[] nums) {

        if (l == r) {
            tree[node] = new Node(k);

            int rem = nums[l] % k;

            tree[node].product = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(tree[node * 2],
                           tree[node * 2 + 1]);
    }

    // ---------------- UPDATE ----------------

    void update(int node, int l, int r,
                int index, int value) {

        if (l == r) {

            tree[node] = new Node(k);

            int rem = value % k;

            tree[node].product = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2],
                           tree[node * 2 + 1]);
    }

    // ---------------- MERGE ----------------

    Node merge(Node left, Node right) {

        Node res = new Node(k);

        // Product of complete segment
        res.product =
            (left.product * right.product) % k;

        // Case 1:
        // Prefix ends inside left segment
        for (int r = 0; r < k; r++) {
            res.cnt[r] += left.cnt[r];
        }

        // Case 2:
        // Prefix contains all of left
        // and some prefix of right
        for (int r = 0; r < k; r++) {

            int newRemainder =
                (left.product * r) % k;

            res.cnt[newRemainder] += right.cnt[r];
        }

        return res;
    }

    // ---------------- QUERY ----------------

    Node query(int node, int l, int r,
               int ql, int qr) {

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(node * 2,
                         l, mid,
                         ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1,
                         mid + 1, r,
                         ql, qr);
        }

        Node left =
            query(node * 2,
                  l, mid,
                  ql, qr);

        Node right =
            query(node * 2 + 1,
                  mid + 1, r,
                  ql, qr);

        return merge(left, right);
    }
}