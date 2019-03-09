import java.io.*;
import java.util.*;

class WordCountEngine {

    static class Word {
        String word;
        int count;
        int index;
        public Word(String word) {
            this.word = word;
        }
        public Word(String word, int count, int index) {
            this.word = word;
            this.count = count;
            this.index = index;
        }

    }
    static String[][] wordCountEngine(String document) {
        // your code goes here
        Map<String, Word> map = new HashMap<>();
        String[] words = document.split(" +");
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();
            String wd = words[i];
            int j = 0;
            while (j < wd.length()) {
                if (Character.isLetterOrDigit(wd.charAt(j))) {
                    sb.append(wd.charAt(j));
                }
                j++;
            }
            wd = sb.toString().toLowerCase();
            if (map.containsKey(wd)) {
                Word w = map.get(wd);
                w.count++;
            } else {
                Word w = new Word(wd, 1, i);
                map.put(wd, w);
            }
        }
        Word[] arr = new Word[map.size()];
        int i = 0;
        for (String s : map.keySet()) {
            arr[i++] = map.get(s);
        }
        Arrays.sort(arr, new Comparator<Word>() {
            public int compare(Word o2, Word o1) {
                // Intentional: Reverse order for this demo
                int res = ((Integer)o1.count).compareTo(o2.count);
                if (res == 0) {
                    return ((Integer)o2.index).compareTo(o1.index);
                }
                return res;
            }});
        String[][] res = new String[map.size()][2];
        int j = 0;
        for (Word w : arr) {
            res[j][0] = w.word;
            res[j][1] = w.count + "";
            j++;
        }
        return res;
    }

    public static void main(String[] args) {
        String[][] test = WordCountEngine.wordCountEngine("Practice makes perfect. you'll only\n" +
                "       get Perfect by practice. just practice!");
        System.out.println(test.length);

        for (int i = 0; i < test.length; i++) {

            System.out.println(test[i][0] + " : " + test[i][1]);
        }
    }
}



