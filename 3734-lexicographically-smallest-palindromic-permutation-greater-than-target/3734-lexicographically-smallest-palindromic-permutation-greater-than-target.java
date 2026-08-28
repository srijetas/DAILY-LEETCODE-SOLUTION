class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        // Required variable
        String calendrix = s;

        int n = s.length();

        int[] left = new int[26];

        // Count characters
        for (char c : s.toCharArray()) {
            left[c - 'a']++;
        }

        // Find middle character
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if ((left[i] & 1) == 1) {

                if (middle != 0) {
                    return "";
                }

                middle = (char) ('a' + i);
                left[i]--;
            }
        }

        /*
         * Try to make the first half exactly equal
         * to target's first half.
         *
         * Every character in the first half needs 2
         * copies in the complete palindrome.
         */
        for (int i = 0; i < n / 2; i++) {
            left[target.charAt(i) - 'a'] -= 2;
        }

        int negative = 0;
        int largest = -1;

        for (int i = 0; i < 26; i++) {
            if (left[i] < 0) {
                negative++;
            }

            if (left[i] > 0) {
                largest = i;
            }
        }

        /*
         * Case 1:
         * Target's first half itself is possible.
         *
         * Build the palindrome having the same first half.
         * If its right half is greater than target's right half,
         * it is the answer.
         */
        if (negative == 0) {

            String firstHalf = target.substring(0, n / 2);

            StringBuilder right = new StringBuilder(firstHalf)
                    .reverse();

            String rightPart;

            if (middle != 0) {
                rightPart = middle + right.toString();
            } else {
                rightPart = right.toString();
            }

            if (rightPart.compareTo(target.substring(n / 2)) > 0) {
                return firstHalf + rightPart;
            }
        }

        /*
         * Case 2:
         * We need to increase some character in the first half.
         *
         * Start from the RIGHTMOST position.
         * This keeps the resulting string as small as possible.
         */
        for (int i = n / 2 - 1; i >= 0; i--) {

            int current = target.charAt(i) - 'a';

            // Undo the use of target[i] and its mirror.
            left[current] += 2;

            if (left[current] == 0) {
                negative--;
            }

            /*
             * If the prefix before i cannot be formed,
             * this position cannot be our pivot.
             */
            if (negative > 0) {
                continue;
            }

            /*
             * Find the smallest character greater than target[i]
             * that we can use.
             */
            int next = current + 1;

            while (next < 26 && left[next] <= 0) {
                next++;
            }

            if (next == 26) {
                continue;
            }

            // Use two copies of 'next'
            left[next] -= 2;

            /*
             * Construct first half:
             *
             * target[0 ... i-1]
             * target[i] -> next
             * remaining characters in sorted order
             */
            StringBuilder firstHalf = new StringBuilder();

            firstHalf.append(target, 0, i);
            firstHalf.append((char) ('a' + next));

            for (int c = 0; c < 26; c++) {
                for (int k = 0; k < left[c] / 2; k++) {
                    firstHalf.append((char) ('a' + c));
                }
            }

            /*
             * Mirror the first half.
             */
            StringBuilder answer = new StringBuilder();

            answer.append(firstHalf);

            if (middle != 0) {
                answer.append(middle);
            }

            for (int j = firstHalf.length() - 1; j >= 0; j--) {
                answer.append(firstHalf.charAt(j));
            }

            return answer.toString();
        }

        return "";
    }
}