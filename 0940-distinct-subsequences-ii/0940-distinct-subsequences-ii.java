class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;

        // last[c] = number of subsequences counted before
        // the previous occurrence of character c
        long[] last = new long[26];

        long total = 0;

        for (char c : s.toCharArray()) {
            int idx = c - 'a';

            long newSubseq = (total + 1) % MOD;

            // Remove duplicates created by previous occurrence of c
            total = (total + newSubseq - last[idx] + MOD) % MOD;

            last[idx] = newSubseq;
        }

        return (int) total;
    }
}