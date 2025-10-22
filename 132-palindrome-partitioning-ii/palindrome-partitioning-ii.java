class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        int[] minCuts = new int[n];
        // Step 1
        for (int end = 0; end < n; end++) {
            for (int start = 0; start <= end; start++) {
                if (s.charAt(start) == s.charAt(end) &&
                    (end - start <= 2 || isPalindrome[start + 1][end - 1])) {
                    isPalindrome[start][end] = true;
                }
            }
        }
        // Step 2:
        for (int i = 0; i < n; i++) {
            if (isPalindrome[0][i]) {
                minCuts[i] = 0; // the whole substring is a palindrome
            } else {
                int min = i;
                for (int j = 0; j < i; j++) {
                    if (isPalindrome[j + 1][i]) {
                        min = Math.min(min, minCuts[j] + 1);
                    }
                }
                minCuts[i] = min;
            }
        }
        return minCuts[n - 1];
    }
}