class Solution {
    public int longestValidParentheses(String s) {

        int maxLength = 0;

        int[] stack = new int[s.length() + 1];
        int top = -1;

        stack[++top] = -1;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                stack[++top] = i;
            } else {
                top--;

                if (top == -1) {
                    stack[++top] = i;
                } else {
                    maxLength = Math.max(maxLength, i - stack[top]);
                }
            }
        }

        return maxLength;
    }
}