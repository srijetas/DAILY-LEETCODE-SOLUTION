class Solution {
public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
int count = 0;

    for (int row = 0; row < arr1.length; row++) {
        boolean valid = true;
        for (int col = 0; col < arr2.length; col++) {
            if (Math.abs(arr1[row] - arr2[col]) <= d) {
                valid = false;
                break;
            }
        }
        if (valid) {
            count++;
        }
    }
    return count;
}

}