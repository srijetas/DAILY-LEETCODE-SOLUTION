import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> answer = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (isValid(current)) {
                answer.add(current);
                found = true;
            }

            // If a valid string is found at this level,
            // don't remove any more characters.
            if (found) {
                continue;
            }

            for (int position = 0; position < current.length(); position++) {

                // Only remove parentheses.
                if (current.charAt(position) != '(' &&
                    current.charAt(position) != ')') {
                    continue;
                }

                String next = current.substring(0, position)
                             + current.substring(position + 1);

                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }

        return answer;
    }

    private boolean isValid(String s) {
        int balance = 0;

        for (int position = 0; position < s.length(); position++) {
            char current = s.charAt(position);

            if (current == '(') {
                balance++;
            } 
            else if (current == ')') {
                balance--;

                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}