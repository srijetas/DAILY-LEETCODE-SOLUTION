class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n=nums.length;

        Set<Integer> pairXor= new HashSet<> ();
        Set<Integer> ans= new HashSet<> ();

        for(int x:nums){
            ans.add(x);
        }
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                pairXor.add(nums[i]^nums[j]);
            }
        }

        for(int px:pairXor){
            for(int x:nums){
                ans.add(px^x);
            }
        }

        return ans.size();

    }
}