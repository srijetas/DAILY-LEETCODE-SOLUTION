class Solution {
public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
    int n = nums1.length;
    long k = (long) k1 + k2;

    long[] diff = new long[n];
    long sum = 0;
    long maxDiff = 0;
    for (int index = 0; index < n; index++) {
        diff[index] = Math.abs((long) nums1[index] - nums2[index]);
        sum += diff[index];
        maxDiff = Math.max(maxDiff, diff[index]);
    }
    if (sum <= k) {
        return 0;
    }
    long low = 0;
    long high = maxDiff;
    while (low < high) {
        long mid = low + (high - low) / 2;
        long required = 0;
        for (long difference : diff) {
            if (difference > mid) {
                required += difference - mid;
                if (required > k) {
                    break;
                }
            }
        }
        if (required <= k) {
            high = mid;
        } else {
            low = mid + 1;
        }
    }
    long level = low;
    long used = 0;
    long answer = 0;
    for (long difference : diff) {
        if (difference > level) {
            used += difference - level;
            difference = level;
        }
        answer += difference * difference;
    }
    long remaining = k - used;
    for (int index = 0; index < n && remaining > 0; index++) {
        if (diff[index] >= level && diff[index] > 0) {
            answer -= level * level;
            answer += (level - 1) * (level - 1);
            remaining--;
        }
    }
    return answer;
}

}