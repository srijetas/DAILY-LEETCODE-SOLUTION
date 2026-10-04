class Solution {
    public boolean checkValidString(String s) {

        int minOpen = 0;
        int maxOpen = 0;

        for (int index = 0; index < s.length(); index++) {

            char current = s.charAt(index);

            if (current == '(') {
                minOpen++;
                maxOpen++;
            }
            else if (current == ')') {
                minOpen--;
                maxOpen--;
            }
            else { // current == '*'
                minOpen--;
                maxOpen++;
            }

            // Even maximum possible open brackets became negative
            if (maxOpen < 0) {
                return false;
            }

            // Minimum cannot be negative
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        return minOpen == 0;
    }
}