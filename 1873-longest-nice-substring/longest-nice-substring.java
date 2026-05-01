class Solution {
    public String longestNiceSubstring(String s) {
        return helper(s);
    }

    String helper(String s) {
        if (s.length() < 2) return "";

        char[] arr = s.toCharArray();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            char c = arr[i];

            if (!hasBoth(s, c)) {
                String left = helper(s.substring(0, i));
                String right = helper(s.substring(i + 1));

                return left.length() >= right.length() ? left : right;
            }
        }

        return s;
    }

    boolean hasBoth(String s, char c) {
        boolean lower = false, upper = false;

        for (char ch : s.toCharArray()) {
            if (ch == Character.toLowerCase(c)) lower = true;
            if (ch == Character.toUpperCase(c)) upper = true;
        }

        return lower && upper;
    }
}