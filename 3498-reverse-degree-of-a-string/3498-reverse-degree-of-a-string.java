class Solution {
    public int reverseDegree(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            int pos = i + 1;

            // Normal alphabet position: a=1, b=2, ..., z=26
            int normal = s.charAt(i) - 'a' + 1;

            // Reverse alphabet position: a=26, b=25, ..., z=1
            int reverse = 27 - normal;

            ans += reverse * pos;
        }

        return ans;
    }
}