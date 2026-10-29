import java.util.*;

class Solution {
    private int n, timer, LOG;
    private int[][] up, adj;
    private int[] tin, tout, depth, parent, bit;
    private char[] chars;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;
        timer = 0;
        LOG = 1;

        while ((1 << LOG) <= n) {
            LOG++;
        }

        int[] degree = new int[n];

        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        adj = new int[n][];

        for (int node = 0; node < n; node++) {
            adj[node] = new int[degree[node]];
        }

        Arrays.fill(degree, 0);

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj[u][degree[u]++] = v;
            adj[v][degree[v]++] = u;
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        up = new int[LOG][n];
        chars = s.toCharArray();

        buildTree();

        bit = new int[n + 2];

        for (int node = 0; node < n; node++) {
            int mask = 1 << (chars[node] - 'a');
            rangeXor(tin[node], tout[node], mask);
        }

        List<Boolean> result = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {
                int node = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);

                int oldMask = 1 << (chars[node] - 'a');
                int newMask = 1 << (newChar - 'a');

                int change = oldMask ^ newMask;

                if (change != 0) {
                    rangeXor(tin[node], tout[node], change);
                    chars[node] = newChar;
                }

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                int maskU = prefixXor(tin[u]);
                int maskV = prefixXor(tin[v]);

                // Correct path mask calculation
                int lcaMask = 1 << (chars[ancestor] - 'a');

                int pathMask = maskU ^ maskV ^ lcaMask;

                // At most one character may have an odd frequency
                boolean possible =
                        (pathMask & (pathMask - 1)) == 0;

                result.add(possible);
            }
        }

        return result;
    }

    private void buildTree() {
        int[] stack = new int[n];
        int[] next = new int[n];
        int top = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int node = stack[top - 1];

            if (next[node] == 0) {
                tin[node] = timer++;

                up[0][node] = parent[node];

                for (int k = 1; k < LOG; k++) {
                    up[k][node] =
                            up[k - 1][up[k - 1][node]];
                }
            }

            if (next[node] < adj[node].length) {
                int child = adj[node][next[node]++];

                if (child == parent[node]) {
                    continue;
                }

                parent[child] = node;
                depth[child] = depth[node] + 1;

                stack[top++] = child;

            } else {
                tout[node] = timer - 1;
                top--;
            }
        }
    }

    private void rangeXor(int left, int right, int mask) {
        update(left + 1, mask);
        update(right + 2, mask);
    }

    private void update(int index, int mask) {
        while (index < bit.length) {
            bit[index] ^= mask;
            index += index & -index;
        }
    }

    private int prefixXor(int index) {
        int result = 0;
        index++;

        while (index > 0) {
            result ^= bit[index];
            index -= index & -index;
        }

        return result;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int difference = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++) {
            if ((difference & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return parent[u];
    }
}