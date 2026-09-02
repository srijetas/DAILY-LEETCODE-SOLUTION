class Solution {
    public boolean isMatch(String s, String p) {

        int i = 0;          // pointer for s
        int j = 0;          // pointer for p

        int starIndex = -1; // last '*' position in p
        int matchIndex = 0; // position in s when '*' was found

        while (i < s.length()) {

            // Characters match or pattern has '?'
            if (j < p.length() &&
                    (p.charAt(j) == '?' ||
                     p.charAt(j) == s.charAt(i))) {

                i++;
                j++;
            }

            // Found '*'
            else if (j < p.length() && p.charAt(j) == '*') {

                starIndex = j;
                matchIndex = i;
                j++;
            }

            // Mismatch, but previous '*' exists
            else if (starIndex != -1) {

                // Let '*' match one more character
                j = starIndex + 1;
                matchIndex++;
                i = matchIndex;
            }

            // No match possible
            else {
                return false;
            }
        }

        // Remaining pattern characters must all be '*'
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }

        return j == p.length();
    }
}