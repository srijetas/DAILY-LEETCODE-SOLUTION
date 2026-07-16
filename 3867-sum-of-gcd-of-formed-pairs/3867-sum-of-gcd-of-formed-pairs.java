class Solution {
    public long gcdSum(int[] nums) {
        int n=nums.length;
        int[] prefixGcd = new int[n];

        int mx=0;
        for(int i=0;i<n;i++){
            mx=Math.max(mx,nums[i]);
            prefixGcd[i]=gcd (nums[i],mx);

        }

        Arrays.sort(prefixGcd);
        long ans=0;
        for (int i=0;i<n/2;i++){
            ans +=gcd(prefixGcd[i],prefixGcd[n-1-i]);

        }
        return ans;
    }
    private int gcd(int a,int b){
        while(b!=0){
            int temp=b;
            b=a%b;
            a=temp;

        }
        return a;
    }
}