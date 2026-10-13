import java.util.Arrays;

class Solution {
public long maxAlternatingSum(int[] nums) {
int n = nums.length;

    for (int row = 0; row < n; row++) {
        nums[row] *= nums[row];
    }
    Arrays.sort(nums);
    int mid = n / 2;
    long ans = 0;
    for (int row = 0; row < mid; row++) {
        ans -= nums[row];
    }
    for (int row = mid; row < n; row++) {
        ans += nums[row];
    }
    return ans;
}

}