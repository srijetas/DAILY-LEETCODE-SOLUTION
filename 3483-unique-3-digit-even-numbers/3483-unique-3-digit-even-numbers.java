class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        // Count occurrences of each digit
        for (int digit : digits) {
            freq[digit]++;
        }

        int count = 0;

        // Check every 3-digit number
        for (int num = 100; num <= 998; num += 2) {
            int ones = num % 10;
            int tens = (num / 10) % 10;
            int hundreds = num / 100;

            // Temporarily use the digits
            freq[ones]--;
            freq[tens]--;
            freq[hundreds]--;

            // If all frequencies remain >= 0, number can be formed
            if (freq[ones] >= 0 &&
                freq[tens] >= 0 &&
                freq[hundreds] >= 0) {
                count++;
            }

            // Restore frequencies
            freq[ones]++;
            freq[tens]++;
            freq[hundreds]++;
        }

        return count;
    }
}