package com.sparo.leetcode.str;

/**
 * description: leetcode  5. 最长回文子串
 * Created by sdh on 2020-06-20
 */
public class longestPalindrome {

   /* 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。


    示例 1：
    输入: "babad"
    输出: "bab"
    注意: "aba" 也是一个有效答案。
    示例 2：
    输入: "cbbd"
    输出: "bb"*/


    public String longestPalindrome(String s) {

        //子串 的隐含含义是指：字符相连的才叫子串

        // 暴力
        // return lpdBrute(s);
        // 动态规划
        // return lpdDp(s);
        // 中心散列
        // return lpdCenter(s);
        // 中心散列 未改良版（截取字符串版）
        return lpdCenterOriginal(s);
    }
    //暴力/动态规划/中心散列法
    private String lpdBrute(String s) {
        // 健壮性判定的 base case
        if(s == null|| s.length()<2) return s;
        //双指针前后索引对应区间内的字符串 判断是否为回文,并记录长度 比较得实时max值
        int len = s.length();
        //记录对应最长回文子串的 开始index
        int startIndex = 0;
        int maxLen = 1; //记录最长回文子串，对于任意字符串 只要长度不小于2，即字符超过2则最小的回文子串长度为1，这里以1 为初始化数据的base case
        char[] ch = s.toCharArray();
        for(int i = 0; i<len; i++) {
            for(int j = 1; j<len; j++) {
                //回文且 长度大于当前最大len 则覆盖赋值,记录起始位置
                if(isPalindrome(ch, i, j) && (j-i+1>maxLen)) {
                    maxLen = j-i+1;
                    startIndex = i;
                }
            }
        }
        //截取返回最后以 startIndex为初始index 且长度为maxLen 的字符串
        return s.substring(startIndex, startIndex+maxLen); //记住api的含义&如何使用&api非驼峰命名(不需要+1 哦)
    }
    //判定ch 字符数组中 i 到 j区间内的字符串是否为回文串
    private boolean isPalindrome(char[] ch, int i, int j) {
        while(i<j) {
            if(ch[i]!=ch[j])return false;
            i++;
            j--;
        }
        return true;
    }

    private String lpdDp(String s) {
        //base case
        if(s == null || s.length()<2) return s;
        //初始化状态变量
        int len = s.length();
        char[] ch = s.toCharArray();
        int maxLen = 1;
        int startIndex = 0;
        boolean[][] dp = new boolean[len][len];
        //初始化dp数组的base case 即 对角线元素为回文子串
        for(int k = 0; k < len; k++) {
            dp[k][k] = true;
        }
        //double for 遍历 判定 dp 对应i，j位置是否为回文子串
        //除了需要对动规的dp数组含义明确外还需要知道正确遍历赋值的顺序，即如何遍历赋值给对应位置的数组元素，此时需要依赖参考动归的转移方程
        // dp[i][j] = dp[i+1][j-1] && ch[i]==ch[j],即依赖于当前位置元素的左下角（↙️）元素，才能获取正确的当前状态
        for(int i = len-1; i>=0; i--) {
            for(int j = i; j<len; j++) {

                //❌ 遗漏了 边界判定 忘了写
                if(ch[i] == ch[j]) {
                    if(j-i<=2) { //最多三个元素 则必为true
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if(dp[i][j] && (j-i+1>maxLen)) {
                    maxLen = j-i+1;
                    startIndex = i;
                }
            }
        }
        return s.substring(startIndex, startIndex + maxLen);
    }

    int startIndex = 0;
    int maxLen = 1;
    //思路：从前到后遍历，判定并记录以当前index为中心的最长回文串
    private String lpdCenter(String s) {
        if(s==null || s.length()<2) return s;
        int len = s.length();
        char[] ch = s.toCharArray();
        for(int i = 0; i<len; i++) {
            isPalindromeCenterIndex(ch, i, i);
            isPalindromeCenterIndex(ch, i, i+1);
        }
        return s.substring(startIndex, startIndex+maxLen);
    }

    public void isPalindromeCenterIndex(char[] ch, int l, int r) {
        while(l>=0 && r<ch.length && ch[l]==ch[r]) {
            l--;
            r++;
        }
        //小心处理此时不符合循环的 边界值，此时的l/r不能直接用于计算初始index和对应长度
        //start位置改为 l+1 多-1，结束位置为r-1 多+1，如果是截取字符串则再+1，改为r 相对于之前中心散列法少了截取字符串的过程，优化了效率
        if(r-1-l>maxLen) {
            maxLen = r-l-1;
            startIndex = l+1;
        }
    }

    private String lpdCenterOriginal(String s) {
        if(s == null || s.length()<2) return s;
        int len = s.length();
        char[] ch = s.toCharArray();
        String res = ""; //返回结果字符串
        for(int i = 0; i < len-1; i++) {//可以不减，下面while 里已经保护,即使减-1 也不影响结果，因为以最后为中心的回文子串最长为1！
            String s1 = isPalindromeCenterIndexOriginal(ch, s, i, i);
            String s2 = isPalindromeCenterIndexOriginal(ch, s, i, i+1);
            res = res.length()>s1.length()?res:s1;
            res = res.length()>s2.length()?res:s2;
        }
        return res;
    }
    //每次返回以l，r为中心的最大回文子串（可改良为上面的 每次只记录 最后返回最大）
    private String isPalindromeCenterIndexOriginal(char[] ch, String s, int l, int r) {
        while(l>=0&& r<s.length()&&ch[l]==ch[r]) {
            l--;
            r++;
        }
        //r-1为end index， 因为前开后闭 so +1，为r
        return s.substring(l+1, r);
    }
}
