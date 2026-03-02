class Solution {
    public boolean halvesAreAlike(String s) {
        int count1 = 0, count2 = 0;
        String vowels = "aeiouAEIOU";
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            if (vowels.indexOf(s.charAt(i)) != -1) {
                count1++;
            }
        }
        for (int i = n / 2; i < n; i++) {
            if (vowels.indexOf(s.charAt(i)) != -1) {
                count2++;
            }
        }
        return count1 == count2;
    }
}