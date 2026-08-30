class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find positions of minimum and maximum
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Put minIndex before maxIndex
        if (minIndex > maxIndex) {
            int temp = minIndex;
            minIndex = maxIndex;
            maxIndex = temp;
        }

        // 3 possibilities:
        // 1. Remove both from the left
        int bothLeft = maxIndex + 1;

        // 2. Remove both from the right
        int bothRight = n - minIndex;

        // 3. Remove min from left and max from right
        int bothSides = minIndex + 1 + (n - maxIndex);

        return Math.min(bothLeft, Math.min(bothRight, bothSides));
    }
}