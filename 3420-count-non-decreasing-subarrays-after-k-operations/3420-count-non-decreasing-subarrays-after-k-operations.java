import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long ans = 0;
        long cost = 0;

        Deque<int[]> dq = new ArrayDeque<>();

        int right = nums.length - 1;

        for (int left = nums.length - 1; left >= 0; left--) {

            int num = nums[left];
            int count = 1;

            while (!dq.isEmpty() && dq.peekLast()[0] < num) {

                int[] curr = dq.pollLast();

                count += curr[1];

                cost += (long) (num - curr[0]) * curr[1];
            }

            dq.offerLast(new int[]{num, count});

            while (cost > k) {

                int[] curr = dq.pollFirst();

                cost -= curr[0] - nums[right];

                right--;

                if (curr[1] > 1) {
                    dq.offerFirst(new int[]{curr[0], curr[1] - 1});
                }
            }

            ans += right - left + 1;
        }

        return ans;
    }
}