import java.util.Arrays;

class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int[] half = new int[26];
        String mid = "";
        int totalLen = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            totalLen += half[i];
            if ((freq[i] & 1) == 1) {
                mid = String.valueOf((char) ('a' + i));
            }
        }

        // Check if there are enough palindromic permutations
        if (countWays(half, k) < k) {
            return "";
        }

        StringBuilder left = new StringBuilder();

        // Construct character by character
        while (left.length() < totalLen) {
            for (int i = 0; i < 26; i++) {
                if (half[i] == 0) continue;

                half[i]--;
                long ways = countWays(half, k);

                if (ways >= k) {
                    left.append((char) ('a' + i));
                    break;
                } else {
                    k -= ways;
                    half[i]++; // Backtrack
                }
            }
        }

        String right = new StringBuilder(left).reverse().toString();
        return left.toString() + mid + right;
    }

    // Calculates multinomial coefficient without BigInteger, capped at limit k
    private long countWays(int[] counts, long cap) {
        int n = 0;
        for (int c : counts) n += c;

        long res = 1;
        int currentN = 1;

        for (int count : counts) {
            for (int i = 1; i <= count; i++) {
                res = res * currentN / i;
                currentN++;
                if (res >= cap) return cap; // Early exit on overflow/limit match
            }
        }
        return res;
    }
}