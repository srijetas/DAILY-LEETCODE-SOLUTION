class Solution {

    int[] leftChar, rightChar, pref, suff, best;
    char[] s;

    public int[] longestRepeating(String str, String queryCharacters,
                                  int[] queryIndices) {

        s = str.toCharArray();
        int n = s.length;

        leftChar = new int[4 * n];
        rightChar = new int[4 * n];
        pref = new int[4 * n];
        suff = new int[4 * n];
        best = new int[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            int idx = queryIndices[i];
            s[idx] = queryCharacters.charAt(i);

            update(1, 0, n - 1, idx);

            ans[i] = best[1];
        }

        return ans;
    }

    void build(int node, int l, int r) {

        if (l == r) {
            leftChar[node] = rightChar[node] = s[l];
            pref[node] = suff[node] = best[node] = 1;
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        merge(node, node * 2, node * 2 + 1, l, r);
    }

    void update(int node, int l, int r, int idx) {

        if (l == r) {
            leftChar[node] = rightChar[node] = s[l];
            pref[node] = suff[node] = best[node] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid)
            update(node * 2, l, mid, idx);
        else
            update(node * 2 + 1, mid + 1, r, idx);

        merge(node, node * 2, node * 2 + 1, l, r);
    }

    void merge(int node, int a, int b, int l, int r) {

        leftChar[node] = leftChar[a];
        rightChar[node] = rightChar[b];

        pref[node] = pref[a];
        suff[node] = suff[b];

        best[node] = Math.max(best[a], best[b]);

        // If boundary characters are same
        if (rightChar[a] == leftChar[b]) {

            int mid = (l + r) / 2;

            // Entire left segment is same
            if (pref[a] == mid - l + 1)
                pref[node] = pref[a] + pref[b];

            // Entire right segment is same
            if (suff[b] == r - mid)
                suff[node] = suff[b] + suff[a];

            // Sequence crossing the middle
            best[node] = Math.max(
                best[node],
                suff[a] + pref[b]
            );
        }
    }
}