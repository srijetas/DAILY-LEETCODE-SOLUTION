class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Prime factorize t into factors 2, 3, 5, 7
        int[] counts = new int[4]; // c2, c3, c5, c7
        int[] primes = {2, 3, 5, 7};
        long temp = t;

        for (int i = 0; i < 4; i++) {
            while (temp % primes[i] == 0) {
                counts[i]++;
                temp /= primes[i];
            }
        }

        // If t has prime factors greater than 7, it's impossible using non-zero digits
        if (temp > 1) {
            return "-1";
        }

        int n = num.length();
        int[][] prefixFactors = new int[n + 1][4];
        boolean hasZero = false;
        int firstZeroIdx = -1;

        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';
            if (d == 0) {
                hasZero = true;
                firstZeroIdx = i;
                break;
            }
            int[] fc = getFactorContrib(d);
            for (int j = 0; j < 4; j++) {
                prefixFactors[i + 1][j] = prefixFactors[i][j] + fc[j];
            }
        }

        // Check if `num` itself is valid (if it contains no zeros)
        if (!hasZero) {
            boolean valid = canFit(
                counts[0] - prefixFactors[n][0],
                counts[1] - prefixFactors[n][1],
                counts[2] - prefixFactors[n][2],
                counts[3] - prefixFactors[n][3],
                0
            );
            if (valid) return num;
        }

        // Try prefix length i, then pick digit d > num[i]
        int maxI = hasZero ? firstZeroIdx : n - 1;

        for (int i = maxI; i >= 0; i--) {
            int remC2 = counts[0] - prefixFactors[i][0];
            int remC3 = counts[1] - prefixFactors[i][1];
            int remC5 = counts[2] - prefixFactors[i][2];
            int remC7 = counts[3] - prefixFactors[i][3];

            int startDigit = num.charAt(i) - '0' + 1;
            int remLen = n - 1 - i;

            for (int d = startDigit; d <= 9; d++) {
                int[] fc = getFactorContrib(d);
                if (canFit(remC2 - fc[0], remC3 - fc[1], remC5 - fc[2], remC7 - fc[3], remLen)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i).append(d);
                    sb.append(buildSuffix(remC2 - fc[0], remC3 - fc[1], remC5 - fc[2], remC7 - fc[3], remLen));
                    return sb.toString();
                }
            }
        }

        // If no number of length n works, find the minimal length > n needed
        int minLenNeeded = minDigitsNeeded(counts[0], counts[1], counts[2], counts[3]);
        int minLen = Math.max(n + 1, minLenNeeded);
        return buildSuffix(counts[0], counts[1], counts[2], counts[3], minLen);
    }

    private boolean canFit(int c2, int c3, int c5, int c7, int remLen) {
        return minDigitsNeeded(c2, c3, c5, c7) <= remLen;
    }

    private int minDigitsNeeded(int c2, int c3, int c5, int c7) {
        c2 = Math.max(0, c2);
        c3 = Math.max(0, c3);
        c5 = Math.max(0, c5);
        c7 = Math.max(0, c7);

        int cnt9 = c3 / 2;
        int rem3 = c3 % 2;

        int cnt8 = c2 / 3;
        int rem2 = c2 % 3;

        int cnt7 = c7;
        int cnt5 = c5;
        int cnt6 = 0, cnt4 = 0, cnt3 = 0, cnt2 = 0;

        if (rem3 == 1 && rem2 == 1) {
            cnt6 = 1;
        } else if (rem3 == 1 && rem2 == 2) {
            cnt6 = 1;
            cnt4 = 1;
        } else if (rem3 == 1 && rem2 == 0) {
            cnt3 = 1;
        } else if (rem3 == 0 && rem2 == 2) {
            cnt4 = 1;
        } else if (rem3 == 0 && rem2 == 1) {
            cnt2 = 1;
        }

        return cnt9 + cnt8 + cnt7 + cnt6 + cnt5 + cnt4 + cnt3 + cnt2;
    }

    private int[] getFactorContrib(int digit) {
        switch (digit) {
            case 2: return new int[]{1, 0, 0, 0};
            case 3: return new int[]{0, 1, 0, 0};
            case 4: return new int[]{2, 0, 0, 0};
            case 5: return new int[]{0, 0, 1, 0};
            case 6: return new int[]{1, 1, 0, 0};
            case 7: return new int[]{0, 0, 0, 1};
            case 8: return new int[]{3, 0, 0, 0};
            case 9: return new int[]{0, 2, 0, 0};
            default: return new int[]{0, 0, 0, 0};
        }
    }

    private String buildSuffix(int c2, int c3, int c5, int c7, int remLen) {
        StringBuilder sb = new StringBuilder();
        int currC2 = c2, currC3 = c3, currC5 = c5, currC7 = c7;

        for (int r = remLen; r > 0; r--) {
            for (int d = 1; d <= 9; d++) {
                int[] fc = getFactorContrib(d);
                if (canFit(currC2 - fc[0], currC3 - fc[1], currC5 - fc[2], currC7 - fc[3], r - 1)) {
                    sb.append(d);
                    currC2 -= fc[0];
                    currC3 -= fc[1];
                    currC5 -= fc[2];
                    currC7 -= fc[3];
                    break;
                }
            }
        }
        return sb.toString();
    }
}