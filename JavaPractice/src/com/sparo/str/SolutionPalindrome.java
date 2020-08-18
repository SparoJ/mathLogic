package com.sparo.str;

class SolutionPalindrome {


    public static void main(String[] args) {
        SolutionPalindrome sp = new SolutionPalindrome();
        String test = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String str = sp.longestPalindrome(test);
        System.out.println("longestPalindrome->str->" + str);
    }
    public String longestPalindrome(String s) {
        return longestPalindromeBrute(s);
    }


    //暴力方案解决回文子串
    //判断字符串s是否是回文子串
    public boolean isPalindrome(String str) {
        int length = str.length();
        for(int i = 0; i < length/2; i++) {
            if(str.charAt(i) != str.charAt(length-i-1)) {
                return false;
            }
        }
        return true;
    }

    public String longestPalindromeBrute(String s) {

        int max = 0;
        int length = s.length();
        int index = 0;
        for(int i = 0; i < length; i++) {
            //截取是包头不包尾
            for(int j = i+1; j<=length;j++) {
                //api 名称问题 subString ❌ substring
                String str = s.substring(i, j);
                if(isPalindrome(str) && str.length()>max) {
                    max = str.length();
                    index = i;
                }
            }
    }
    // index = 1 max = 2  0/1/2/3
    return s.substring(index, index+max);
}
}