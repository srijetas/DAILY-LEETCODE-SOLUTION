class Solution {
public int minInsertions(String s) {
  int insertions = 0;
  int open = 0;
  int index = 0;

    while (index < s.length()) {
        if (s.charAt(index) == '(') {
            open++;
            index++;
        } else {
            if (index + 1 < s.length() && s.charAt(index + 1) == ')') {
                index += 2;
            } else {
                insertions++;
                index++;
            }
            if (open > 0) {
                open--;
            } else {
                insertions++;
            }
        }
    }
    return insertions + (2 * open);
}

}