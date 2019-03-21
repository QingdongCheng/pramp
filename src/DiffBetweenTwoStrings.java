import java.util.*;
import java.io.*;

public class DiffBetweenTwoStrings {


    /*

    def diffBetweenTwoStrings(source, target):
    memo = new Array(source.length, target.length)
    # dp(i, j) is the minimum edits to transform
    # string source[i:] into string target[j:].
  function dp(i, j):
        # If one of the strings is empty, we know
        # the answer already.
        if i == source.length OR j == target.length:
            return target.length - j

        # Otherwise, if we don't have a cached answer,
        # then find one.
        else if memo[i][j] == null:
            if source[i] == target[j]:
                memo[i][j] = dp(i+1, j+1)
            else:
                memo[i][j] = 1 + min(dp(i+1, j), dp(i, j+1))

        return memo[i][j]
    ans = []
    i = 0
    j = 0
    # We are always considering strings source[i:] and target[j:]
    while i < source.length AND j < target.length:
        if source[i] == target[j]:
            # Write the string with no edits
            ans.push(source[i])
            i += 1
            j += 1
        else:
            # We have to decide whether to subtract source[i],
            # or add target[j].
            if dp(i+1, j) <= dp(i, j+1):
                ans.push('-' + source[i])
                i += 1
            else:
                ans.push('+' + target[j])
                j += 1

    while j < target.length:
        ans.push('+' + target[j])
        j += 1

    return " ".join(ans)
     */

    static String[] test(String source, String target) {
        Integer[][] memo = new Integer[source.length()][target.length()];


        List<String> ans = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < source.length() && j < target.length()) {
            if (source.charAt(i) == target.charAt(j)) {
                ans.add("" + source.charAt(i));
                i += 1;
                j += 1;
            } else {

                if (dp(i + 1, j, source, target, memo) <= dp(i, j + 1, source, target, memo)) {
                    ans.add("-" + source.charAt(i));
                    i += 1;
                } else {
                    ans.add("+" + target.charAt(j));
                    j += 1;
                }
            }
        }

        while (j < target.length() ) {
            ans.add("+" + target.charAt(j));
            j += 1;
        }

        String[] result = new String[ans.size()];
        int idx = 0;
        for (String s : ans) {
            result[idx++] = s;
        }

        return result;
    }

    static int dp(int i, int j, String source, String target, Integer[][] memo) {

        if (i == source.length() || j ==target.length()) {
            return target.length() - j;
        } else if (memo[i][j] == null) {
            if (source.charAt(i) == target.charAt(j)) {
                memo[i][j] = dp(i + 1, j + 1, source, target, memo);
            }
            else
                memo[i][j] = 1 + Math.min(dp(i + 1, j, source, target, memo), dp(i, j + 1, source, target, memo));
        }
        return memo[i][j];
    }

    static String[] diffBetweenTwoStringsRecursion(String source, String target) {
        // your code goes here
        List<String> list = helper(source, target);

        String[] ans = new String[list.size()];
        int idx = 0;
        for (String s : list) {
            ans[idx++] = s;
        }


        return ans;
    }

    static List<String> helper(String s, String t) {
        List<String> res = new ArrayList<>();
        if (s.length() == 0 && t.length() == 0) return res;
        if (s.length() == 0) {
            for (int i = 0; i < t.length(); i++) {
                res.add("+" + t.charAt(i));
            }
            return res;
        }
        if (t.length() == 0) {
            for (int i = 0; i < s.length(); i++)
                res.add("-" + s.charAt(i));
            return res;
        }

        if (s.charAt(0) == t.charAt(0)) {
            res.add("" + s.charAt(0));
            List<String> remain = helper(s.substring(1), t.substring(1));
            res.addAll(remain);
            return res;
        } else {
            List<String> ans1 = helper(s.substring(1), t);
            List<String> ans2 = helper(s, t.substring(1));
            if (ans1.size() <= ans2.size()) {
                res.add("-" + s.charAt(0));
                res.addAll(ans1);

            } else {
                res.add("+" + t.charAt(0));
                res.addAll(ans2);
            }
        }
        return res;
    }


    static String[] diffBetweenTwoStringsRecursion2(String source, String target) {


        Integer[][] dp = new Integer[source.length() + 1][target.length() + 1];
        List<String> list = helper2(source, 0, target, 0, dp);
        String[] ans = new String[list.size()];
        int idx = 0;
        for (String s : list) {
            ans[idx++] = s;
        }


        return ans;
    }

    static List<String> helper2(String s, int si, String t, int tj, Integer[][] dp) {

        return null;
    }

    static String[] diffBetweenTwoStrings(String source, String target) {
        int m = source.length() + 1;
        int n = target.length() + 1;
        int[][] dp = new int[m][n];
        // dp[i][j] means min edits of source[0...i] target[0...j]

        for (int i = 0; i < m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < m ; i++) {
            for (int j = 1; j < n; j++) {
                char s = source.charAt(i - 1);
                char t = target.charAt(j - 1);
                if (s == t) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }


        for (int i = 0; i < m; i++) {
            if (i == 0 ) {
                System.out.print("\t\t");
              for (int k = 0; k < target.length(); k++) {
                  System.out.print("" + target.charAt(k) + "\t");
              }
                System.out.println();
            }
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    if (j == 0) {
                        System.out.print(source.charAt(i - 1) + "\t");
                    }
                } else if (j == 0) {

                    System.out.print("\t");
                }
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }

        List<String> ans = new ArrayList<>();
        int i = source.length() - 1, j = target.length() - 1;
        while (i >= 0 && j >= 0) {
            char s = source.charAt(i);
            char t = target.charAt(j);
            int si = i + 1;
            int tj = j + 1;
            int dia = dp[si - 1][tj - 1];
            int left = dp[si][tj - 1];
            int up = dp[si - 1][tj];
            if (s == t) {
                if (dia < left && dia < up) {
                    i--;
                    j--;
                    ans.add("" + s);
                } else {
                    if (left < up) {
                        j--;
                        ans.add("+" + t);
                    } else {
                        i--;
                        ans.add("-" + s);
                    }
                }
            } else {
                if (left < up) {
                    j--;
                    ans.add("+"+ t);

                } else {
                    i--;
                    ans.add("-"+s);
                }
            }
        }

        while (j >= 0) {
            ans.add("+" + target.charAt(j));
            j--;
        }

        String[] res = new String[ans.size()];
        int idx = ans.size() - 1;
        for (String s : ans) {
            res[idx--] = s;
        }

        return res;
    }

    
//    public static void GetAnswer(String s, String t, int i, int j, int[][] dp) {
//        if (i == 0) {
//            if (j != 0) {
//                GetAnswer(s, t, i, j - 1, dp);
//                output.add("+" + t.charAt(j - 1));
//            }
//            return;
//        }
//        if (j == 0) {
//            GetAnswer(s, t,i - 1, j, dp);
//            output.add("-" + t.charAt(i - 1));
//            return;
//        }
//        if (s.charAt(i - 1) == t.charAt(j - 1)) {
//            GetAnswer(s, t,i - 1, j - 1, dp);
//            output.add( "" + s.charAt(i - 1));
//        } else  if (dp[i][j] == dp[i - 1][j] + 1) {
//            GetAnswer(s, t,i - 1, j, dp);
//            output.add("-" + s.charAt(i - 1));
//        } else if (dp[i][j] == dp[i - 1][j - 1] + 2) {
//            GetAnswer(s, t,i - 1, j - 1, dp);
//            output.add("-" + s.charAt(i - 1));
//            output.add("+" + t.charAt(j - 1));
//        } else {
//            GetAnswer(s, t,i, j - 1, dp);
//            output.add("+" + t.charAt(j - 1));
//        }
//    }
//

    static String[] diffBetweenTwoStrings3(String source, String target) {

        // let dp(i, j) = the minimum number of edits required for the problem with strings source[i:] and target[j:]
        int m = source.length() + 1;
        int n = target.length() + 1;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][target.length()] = 0;
        }
        for (int i = 0; i < n; i++) {
            dp[source.length()][i] = target.length() - i;
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                char s = source.charAt(i);
                char t = target.charAt(j);
                if (s == t) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + 1;
                }
            }
        }
        for (int i = 0; i < m - 1; i++) {
            if (i == 0) {
                System.out.print("\t");
            }
            System.out.print(source.charAt(i) + "\t");
        }
        System.out.println();
        for (int i = 0; i < m; i++) {
            if (i == 0) {
                System.out.print("\t");
            } else {
                if ( i < n )
                    System.out.print(target.charAt(i - 1) + "\t");
            }

            for (int j = 0; j < n; j++) {

                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }

        List<String> edits = new ArrayList<>();
        int i = 0, j = 0;
        while (i < source.length() && j < target.length()) {
            char s = source.charAt(i);
            char t = target.charAt(j);
            if (s == t) {
                edits.add("" + s);
                i++;
                j++;
            } else {
                if (dp[i + 1][j] <= dp[i ][j + 1]) {
                    edits.add("-" + s);
                    i++;
                } else {
                    edits.add("+" + t);
                    j++;
                }
            }
        }
        while (j < target.length()) {
            edits.add("+" + target.charAt(j));
            j++;
        }
        String[] res = new String[edits.size()];
        int idx = 0;
        for (String s : edits) {
            res[idx++] = s;
        }
        return res;
        // return null;
    }

    static String[] diffBetweenTwoStrings2(String source, String target) {
        int m = source.length();
        int n = target.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            dp[i][n] = 0;
        }
        for (int j = 0; j < n; j++) {
            dp[m][j] = n - j;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                char s = source.charAt(i);
                char t = target.charAt(j);
                if (s == t) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                System.out.print(dp[i][j]);
            }
            System.out.println();
        }

        List<String> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            char s = source.charAt(i);
            char t = target.charAt(j);
            if (s == t) {
                list.add("" + s);
                i++;
                j++;
            } else {
                if (dp[i + 1][j] <= dp[i][j + 1]) {
                    list.add("-" + s);
                    i++;
                } else {
                    list.add("+" + t);
                    j++;
                }
            }
        }
        while (j < n) {
            list.add("+" + target.charAt(j));
            j++;
        }
        String[] res = new String[list.size()];
        int idx = 0;
        for (String s : list) {
            res[idx++] = s;
        }
        return res;
    }



    public static void main(String[] args) {

        //"ABCDEFG", "ABDFFGH"
        String[] res = DiffBetweenTwoStrings.diffBetweenTwoStrings("ABCDEFG", "ABDFFGH");
        String[] ans = {"A","B","-C", "D", "-E", "F", "+F", "G", "+H"};
        System.out.println("Expected: " + Arrays.toString(ans));
        System.out.println("Actual:   " + Arrays.toString(res));
        System.out.println();

        //
        //input:
        //
        //"CCBC", "CCBC"
        //Expected:
        //
        //["C","C","B","C"]
        //Actual:
        //
        //[C, C, B, C]

        String[] res2 = DiffBetweenTwoStrings.diffBetweenTwoStrings("CCBC", "CCBC");
        String[] ans2 = {"C","C","B", "C"};
        System.out.println("Expected: " + Arrays.toString(ans2));
        System.out.println("Actual:   " + Arrays.toString(res2));
        System.out.println();


        // "CBBC", "CABAABBC"
        String[] res3 = DiffBetweenTwoStrings.diffBetweenTwoStrings("CBBC", "CABAABBC");
        String[] ans3 = {"C","+A","B","+A","+A","B","+B","C"};

        System.out.println("Expected: " + Arrays.toString(ans3));
        System.out.println("Actual:   " + Arrays.toString(res3));
        System.out.println();

        // "CABAAABBC", "CBBC"

        String[] res4 = DiffBetweenTwoStrings.diffBetweenTwoStrings("CABAAABBC", "CBBC");
        String[] ans4 = {"C","-A","B","-A","-A","-A","B","-B","C"};

        System.out.println("Expected: " + Arrays.toString(ans4));
        System.out.println("Actual:   " + Arrays.toString(res4));
        System.out.println();


        // "AABACC", "BABCAC"
        String[] res5 = DiffBetweenTwoStrings.diffBetweenTwoStrings("AABACC", "BABCAC");
        String[] ans5 = {"-A","-A","B","A","+B","C","+A","C"};

        System.out.println("Expected: " + Arrays.toString(ans5));
        System.out.println("Actual:   " + Arrays.toString(res5));
        System.out.println();


        // "H","-M","-X","+L","+Z","P","-H","-H","+L","U","-M","+P","+H"

        String[] res6 = DiffBetweenTwoStrings.diffBetweenTwoStrings("HMXPHHUM", "HLZPLUPH");
        String[] ans6 = {"H","-M","-X","+L","+Z","P","-H","-H","+L","U","-M","+P","+H"};

        System.out.println("Expected: " + Arrays.toString(ans6));
        System.out.println("Actual:   " + Arrays.toString(res6));
        System.out.println();


        //
        //"GHMXGHUGXL", "PPGGXHHULL"

        String[] res7 = DiffBetweenTwoStrings.diffBetweenTwoStrings("GHMXGHUGXL", "PPGGXHHULL");
        String[] ans7 = {"+P","+P","G","-H","-M","-X","G","+X","H","+H","U","-G","-X","L","+L"};

        System.out.println("Expected: " + Arrays.toString(ans7));
        System.out.println("Actual:   " + Arrays.toString(res7));
        System.out.println();

        // "+P","+P","G","-H","-M","-X","G","+X","H","+H","U","-G","-X","L","+L"

        //

        //

        String[] res8 = DiffBetweenTwoStrings.diffBetweenTwoStrings("GMMGZGGLUGUH", "HPGPPMGLLUUU");
        String[] ans8 = {"+H","+P","G","-M","+P","+P","M","G","-Z","-G","-G","L","+L","U","-G","U","-H","+U"};

        System.out.println("Expected: " + Arrays.toString(ans8));
        System.out.println("Actual:   " + Arrays.toString(res8));
        System.out.println();

    }


}


