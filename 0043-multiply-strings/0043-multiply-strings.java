class Solution {
    public String multiply(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int n = num1.length();
        int m = num2.length();

        int[] result = new int[n + m];

        // Multiply digit by digit
        for (int i = n - 1; i >= 0; i--) {

            for (int j = m - 1; j >= 0; j--) {

                int a = num1.charAt(i) - '0';
                int b = num2.charAt(j) - '0';

                int product = a * b;

                int pos1 = i + j;
                int pos2 = i + j + 1;

                int sum = product + result[pos2];

                result[pos2] = sum % 10;
                result[pos1] += sum / 10;
            }
        }

        // Convert array to string
        StringBuilder sb = new StringBuilder();

        int i = 0;

        // Skip leading zeroes
        while (i < result.length && result[i] == 0) {
            i++;
        }

        while (i < result.length) {
            sb.append(result[i]);
            i++;
        }

        return sb.toString();
    }
}