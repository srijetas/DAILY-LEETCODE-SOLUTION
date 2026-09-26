import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        HashMap<String, String> map = new HashMap<>();

        // Store key-value pairs in HashMap
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                int j = i + 1;

                // Find closing bracket
                while (s.charAt(j) != ')') {
                    j++;
                }

                String key = s.substring(i + 1, j);

                // If key exists, use its value; otherwise '?'
                if (map.containsKey(key)) {
                    ans.append(map.get(key));
                } else {
                    ans.append("?");
                }

                i = j;
            } 
            else {
                ans.append(s.charAt(i));
            }
        }

        return ans.toString();
    }
}