class Solution {
public int maxSelectedElements(int[] nums) {
Arrays.sort(nums);

    HashMap<Integer, Integer> dp = new HashMap<>();
    int ans = 0;
    for (int num : nums) {
        // Option 1: Increase num to num + 1
        dp.put(num + 1,
            Math.max(dp.getOrDefault(num + 1, 0),
                     dp.getOrDefault(num, 0) + 1));
        // Option 2: Keep num unchanged
        dp.put(num,
            Math.max(dp.getOrDefault(num, 0),
                     dp.getOrDefault(num - 1, 0) + 1));
        ans = Math.max(ans, dp.get(num));
        ans = Math.max(ans, dp.get(num + 1));
    }
    return ans;
}

}