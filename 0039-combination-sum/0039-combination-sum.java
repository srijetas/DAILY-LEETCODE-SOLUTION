import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        backtrack(candidates, target, 0, new ArrayList<>(), ans);

        return ans;
    }

    private void backtrack(
        int[] candidates,
        int target,
        int start,
        List<Integer> current,
        List<List<Integer>> ans
    ) {
        // Target achieved
        if (target == 0) {
            ans.add(new ArrayList<>(current));
            return;
        }

        // Try every candidate from start
        for (int i = start; i < candidates.length; i++) {

            // Candidate is too large
            if (candidates[i] > target) {
                continue;
            }

            current.add(candidates[i]);

            // i, not i + 1
            // because same number can be reused
            backtrack(
                candidates,
                target - candidates[i],
                i,
                current,
                ans
            );

            // Backtrack
            current.remove(current.size() - 1);
        }
    }
}