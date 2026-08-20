import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();

        if (s == null || words == null || words.length == 0) {
            return ans;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) {
            return ans;
        }

        Map<String, Integer> required = new HashMap<>();

        for (String word : words) {
            required.put(word, required.getOrDefault(word, 0) + 1);
        }

        for (int start = 0; start < wordLen; start++) {

            int left = start;
            int right = start;
            int count = 0;

            Map<String, Integer> current = new HashMap<>();

            while (right + wordLen <= s.length()) {

                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (required.containsKey(word)) {

                    current.put(word, current.getOrDefault(word, 0) + 1);
                    count++;

                    while (current.get(word) > required.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);

                        current.put(leftWord, current.get(leftWord) - 1);

                        left += wordLen;
                        count--;
                    }

                    if (count == wordCount) {
                        ans.add(left);

                        String leftWord = s.substring(left, left + wordLen);
                        current.put(leftWord, current.get(leftWord) - 1);

                        left += wordLen;
                        count--;
                    }

                } else {
                    current.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return ans;
    }
}