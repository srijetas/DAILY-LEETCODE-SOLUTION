class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final int MOD = 1_000_000_007;
        int[] bitCount = new int[31];

        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if (((num >> bit) & 1) == 1) {
                    bitCount[bit]++;
                }
            }
        }

        long answer = 0;

        for (int count = 0; count < k; count++) {
            int num = 0;

            for (int bit = 0; bit < 31; bit++) {
                if (bitCount[bit] > 0) {
                    num |= (1 << bit);
                    bitCount[bit]--;
                }
            }

            answer = (answer + (long) num * num) % MOD;
        }

        return (int) answer;
    }
}