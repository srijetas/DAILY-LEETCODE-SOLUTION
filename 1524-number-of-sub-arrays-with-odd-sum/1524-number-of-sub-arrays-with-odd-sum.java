class Solution {
    public int numOfSubarrays(int[] arr) {
        long oddCount = 0;
        long evenCount = 1; // Base case: prefix sum of 0 is even
        long currentSum = 0;
        long totalOddSubarrays = 0;
        int MOD = 1_000_000_007;

        for (int num : arr) {
            currentSum += num;
            
            if (currentSum % 2 != 0) {
                // Current prefix sum is odd -> pairs with previous even prefix sums
                totalOddSubarrays = (totalOddSubarrays + evenCount) % MOD;
                oddCount++;
            } else {
                // Current prefix sum is even -> pairs with previous odd prefix sums
                totalOddSubarrays = (totalOddSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) totalOddSubarrays;
    }
}