class Solution {
    public boolean sumGame(String num) {

        int n = num.length();

        int leftSum = 0;
        int rightSum = 0;

        int leftQuestion = 0;
        int rightQuestion = 0;

        for (int i = 0; i < n / 2; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                leftQuestion++;
            } else {
                leftSum += ch - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                rightQuestion++;
            } else {
                rightSum += ch - '0';
            }
        }

        // If the number of ? is odd,
        // Alice can always make the sums different.
        if ((leftQuestion + rightQuestion) % 2 == 1) {
            return true;
        }

        // Difference between existing sums
        int diff = leftSum - rightSum;

        // Alice wins if the difference cannot be balanced
        return diff * 2 != (rightQuestion - leftQuestion) * 9;
    }
}