class Solution {
    public void nextPermutation(int[] nums) {
        
        int n = nums.length;
        
        // Step 1: Find the first decreasing element from right
        int i = n - 2;
        
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        // Step 2: If such element exists, find the next greater element
        if (i >= 0) {
            int j = n - 1;
            
            while (nums[j] <= nums[i]) {
                j--;
            }
            
            // Swap
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        
        // Step 3: Reverse the part after i
        int left = i + 1;
        int right = n - 1;
        
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            
            left++;
            right--;
        }
    }
}