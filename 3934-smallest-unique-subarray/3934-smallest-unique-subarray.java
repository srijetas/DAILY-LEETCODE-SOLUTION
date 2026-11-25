class Solution {

public int smallestUniqueSubarray(int[] nums) {
    int n = nums.length;
    int[] sa = new int[n];
    int[] rank = new int[n];
    int[] temp = new int[n];
    for (int i = 0; i < n; i++) {
        sa[i] = i;
        rank[i] = nums[i];
    }
    // Build Suffix Array
    int classes = 100001;
    for (int k = 1; k < n; k *= 2) {
        countingSort(sa, temp, rank, k, classes);
        countingSort(temp, sa, rank, 0, classes);
        int[] newRank = new int[n];
        int newClasses = 1;
        newRank[sa[0]] = 0;
        for (int i = 1; i < n; i++) {
            int a = sa[i - 1];
            int b = sa[i];
            if (rank[a] != rank[b] ||
                (a + k < n ? rank[a + k] : -1) !=
                (b + k < n ? rank[b + k] : -1)) {
                newClasses++;
            }
            newRank[b] = newClasses - 1;
        }
        rank = newRank;
        classes = newClasses;
        if (classes == n) {
            break;
        }
    }
    // Kasai's Algorithm for LCP
    int[] pos = new int[n];
    for (int i = 0; i < n; i++) {
        pos[sa[i]] = i;
    }
    int[] lcp = new int[n];
    int h = 0;
    for (int i = 0; i < n; i++) {
        int r = pos[i];
        if (r == 0) {
            continue;
        }
        int j = sa[r - 1];
        while (i + h < n && j + h < n &&
               nums[i + h] == nums[j + h]) {
            h++;
        }
        lcp[r] = h;
        if (h > 0) {
            h--;
        }
    }
    // Find Minimum Unique Subarray Length
    int ans = n;
    for (int i = 0; i < n; i++) {
        int common = lcp[i];
        if (i + 1 < n) {
            common = Math.max(common, lcp[i + 1]);
        }
        int len = common + 1;
        if (len <= n - sa[i]) {
            ans = Math.min(ans, len);
        }
    }
    return ans;
}
// Counting Sort
void countingSort(int[] input, int[] output,
                  int[] rank, int offset, int classes) {
    int n = input.length;
    int[] count = new int[classes + 1];
    for (int x : input) {
        int key = (x + offset < n)
                ? rank[x + offset] + 1 : 0;
        count[key]++;
    }
    for (int i = 1; i <= classes; i++) {
        count[i] += count[i - 1];
    }
    for (int i = n - 1; i >= 0; i--) {
        int x = input[i];
        int key = (x + offset < n)
                ? rank[x + offset] + 1 : 0;
        output[--count[key]] = x;
    }
}

}