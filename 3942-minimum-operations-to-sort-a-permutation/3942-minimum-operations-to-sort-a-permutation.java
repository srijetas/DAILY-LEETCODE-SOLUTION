class Solution {
public int minOperations(int[] nums) {
int n = nums.length;

    boolean increasing = true;
    boolean decreasing = true;
    int start = nums[0];
    for (int i = 0; i < n; i++) {
        if (nums[i] != (start + i) % n) {
            increasing = false;
        }
        if (nums[i] != (start - i + n) % n) {
            decreasing = false;
        }
    }
    if (increasing) {
        int rotations = (n - start) % n;
        return Math.min(rotations, n - rotations + 2);
    }
    if (decreasing) {
        int rotations = (n - nums[n - 1]) % n;
        return 1 + Math.min(rotations, n - rotations);
    }
    return -1;
}

}