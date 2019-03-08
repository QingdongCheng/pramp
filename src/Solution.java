import java.io.*;
import java.util.*;

public class Solution {

    static String getShortestUniqueSubstring(char[] arr, String str) {
        // your code goes here
//        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
//            return "";
//        }

        Map<Character, Integer> map = new HashMap<>();

        for (char c : arr) {
            map.put(c, 0);
        }

        int size = arr.length;
        int uniqueCounter = 0;
        int headIndex = 0;
        String ans = "";

        for (int tailIndex = 0; tailIndex < str.length(); tailIndex++) {
            char tailChar = str.charAt(tailIndex);
            if (!map.containsKey(tailChar)) {
                continue;
            }
            int tailCount = map.get(tailChar);
            if (tailCount == 0) {
                uniqueCounter++;
            }
            map.put(tailChar, tailCount + 1);

            while(uniqueCounter == size) {
                int tempLength = tailIndex - headIndex + 1;
                if (tempLength == size) {
                    return str.substring(headIndex, tailIndex + 1);
                }

                if (ans.length() == 0 || tempLength < ans.length()) {
                    ans = str.substring(headIndex, tailIndex + 1);
                }

                char headChar = str.charAt(headIndex);
                if (map.containsKey(headChar)) {
                    int headCount = map.get(headChar) - 1;
                    if (headCount == 0) {
                        uniqueCounter--;

                    }
                    map.put(headChar, headCount);
                }
                headIndex++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        char[] arr = {'A'};
        System.out.println(Solution.getShortestUniqueSubstring(arr, "A"));
        System.out.println("test..");
    }

}