class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Case 1: k == 1
        if (k == 1) {
            int[] freq = new int[51];

            for (int num : nums) {
                freq[num]++;
            }

            int ans = -1;

            for (int num = 0; num <= 50; num++) {
                if (freq[num] == 1) {
                    ans = num;
                }
            }

            return ans;
        }

        // Case 2: k == n
        if (k == n) {
            int ans = 0;

            for (int num : nums) {
                ans = Math.max(ans, num);
            }

            return ans;
        }

        // Case 3: 1 < k < n
        int[] freq = new int[51];

        for (int num : nums) {
            freq[num]++;
        }

        int ans = -1;

        // First element
        if (freq[nums[0]] == 1) {
            ans = Math.max(ans, nums[0]);
        }

        // Last element
        if (freq[nums[n - 1]] == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}