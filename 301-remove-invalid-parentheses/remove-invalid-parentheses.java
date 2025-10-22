class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int n = s.length();
        if (s == null || n == 0) return Arrays.asList("");

        // Step 1: Calculate minimum removals needed
        int openRem = 0, closeRem = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openRem++;
            } else if (c == ')') {
                if (openRem == 0) {
                    closeRem++;
                } else {
                    openRem--;
                }
            }
        }

        // Step 2: Count total parentheses in original string
        int total = 0;
        for (char c : s.toCharArray()) {
            if (c == ')') total++;
        }

        //OR pass total-openRem with counting open total -> if(c=='(') total++;

        Set<String> set = new HashSet<>();
        getAllValidStrings(0, 0, 0, new StringBuilder(), set, s, total-closeRem);

        List<String> ans = new ArrayList<>(set);
        if (ans.size() == 0) ans.add("");
        return ans;
    }

    void getAllValidStrings(int i, int open, int close, StringBuilder str, Set<String> set, String s, int min) {
        if (i == s.length()) {
            
            if (open + close == 2*min) {
                set.add(str.toString());
            }
            return;
        }

        char curr = s.charAt(i);
        int len = str.length();
        if (curr != '(' && curr != ')') {
            // Always include non-parenthesis characters
            getAllValidStrings(i + 1, open, close, str.append(curr), set, s, min);
     
        } else {
            // Option 1: Exclude current parenthesis
            getAllValidStrings(i + 1, open, close, str, set, s, min);

            // Option 2: Include current parenthesis if valid
            if (curr == '(' && open < min) {
                getAllValidStrings(i + 1, open + 1, close, str.append("("), set, s, min);
            } else if (curr == ')' && close < open) {
                getAllValidStrings(i + 1, open, close + 1, str.append(")"), set, s, min);
            }
        }
        str.setLength(len);
    }
}