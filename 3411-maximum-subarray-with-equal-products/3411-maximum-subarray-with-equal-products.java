class Solution {
public int maxLength(int[] nums) {
int n = nums.length;
int ans = 0;

    for (int i = 0; i < n; i++) {
        long prod = 1;
        long gcd = nums[i];
        long lcm = nums[i];
        for (int j = i; j < n; j++) {
            prod *= nums[j];
            gcd = findGCD(gcd, nums[j]);
            lcm = (lcm * nums[j]) / findGCD(lcm, nums[j]);
            if (prod == gcd * lcm) {
                ans = Math.max(ans, j - i + 1);
            }
        }
    }
    return ans;
}
public long findGCD(long a, long b) {
    if (b == 0)
        return a;
    return findGCD(b, a % b);
}

}