class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();
        int[] freq = new int[26];

        // Frequency of characters in s
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int bestPos = -1;
        int bestChar = -1;
        int[] bestFreq = null;

        // Try to keep prefix same as target
        for (int i = 0; i < n; i++) {

            int t = target.charAt(i) - 'a';

            // Find smallest character greater than target[i]
            for (int c = t + 1; c < 26; c++) {

                if (freq[c] > 0) {
                    bestPos = i;
                    bestChar = c;

                    // Save remaining frequency
                    bestFreq = freq.clone();

                    break;
                }
            }

            // Match target[i] so we can continue
            if (freq[t] == 0) {
                break;
            }

            freq[t]--;
        }

        // No permutation of s is greater than target
        if (bestPos == -1) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        // Prefix same as target
        ans.append(target, 0, bestPos);

        // Put the smallest possible greater character
        ans.append((char) ('a' + bestChar));
        bestFreq[bestChar]--;

        // Put all remaining characters in sorted order
        for (int c = 0; c < 26; c++) {
            while (bestFreq[c] > 0) {
                ans.append((char) ('a' + c));
                bestFreq[c]--;
            }
        }

        return ans.toString();
    }
}