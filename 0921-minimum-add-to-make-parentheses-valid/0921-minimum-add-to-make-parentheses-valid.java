class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int answer = 0;

        for (int index = 0; index < s.length(); index++) {
            char current = s.charAt(index);

            if (current == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    answer++;
                }
            }
        }

        return answer + open;
    }
}