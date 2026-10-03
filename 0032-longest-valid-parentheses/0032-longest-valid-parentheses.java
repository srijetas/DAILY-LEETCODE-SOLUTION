class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();

        stack.push(-1);

        int maxLength = 0;

        for (int index = 0; index < s.length(); index++) {

            if (s.charAt(index) == '(') {
                stack.push(index);
            } else {
                stack.pop();

                if (stack.isEmpty()) {
                    stack.push(index);
                } else {
                    int length = index - stack.peek();
                    maxLength = Math.max(maxLength, length);
                }
            }
        }

        return maxLength;
    }
}