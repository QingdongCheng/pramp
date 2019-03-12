import java.io.*;
import java.util.*;

class WordCountEngine {

    static class Word {
        String word;
        int count;
        int index;
        public Word() {

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


    static String[][] wordCountBucketSort(String document) {
        String[] words = document.split(" +");
        Map<String, Integer> map = new HashMap<>();
        int largest = 0;
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
            words[i] = wd;
            if (map.containsKey(wd)) {
                map.put(wd, map.get(wd) + 1);
            } else {
                map.put(wd, 1);
            }
            if (map.get(wd) > largest) {
                largest = map.get(wd);
            }
        }

        System.out.println("largest is "  + largest);
        List<List<String>> wordList = new ArrayList<>(largest);

        for (int i = 0; i < largest; i++) {
            wordList.add(null);
        }

        System.out.println("wordList's size is " + wordList.size());

        for (String s : map.keySet()) {
            int count = map.get(s);

            if (wordList.get(count - 1) == null) {
                List<String> innerList = new ArrayList<>();
                innerList.add(s);
                wordList.set(count - 1, innerList);
            } else {
                List<String> innerList = wordList.get(count - 1);
                innerList.add(s);
            }
        }
        String[][] res = new String[map.size()][2];
        int index = 0;
        for (int i = largest - 1; i >= 0; i--) {
            if (wordList.get(i) == null) {
                continue;
            } else {
                List<String> innerList = wordList.get(i);
                for (String s : innerList) {
                    res[index][0] = s;
                    res[index][1] = String.valueOf(i + 1);
                    index++;
                }
            }
        }
        return res;
    }

    static class WordComparator implements Comparator<Map.Entry<String, Word>> {
        public int compare(Map.Entry<String, Word> e1, Map.Entry<String, Word> e2) {
            Word w1 = e1.getValue();
            Word w2 = e2.getValue();
            int res = ((Integer)w2.count).compareTo(w1.count);
            if (res == 0) {
                res = ((Integer)w1.index).compareTo(w2.index);
            }
            return res;
        }
    }

//    static String[][] wordCountTreeMap(String document) {
//        String[] words = document.split(" +");
//        TreeMap<String, Word> map = new TreeMap<String, Word>(new WordComparator());
//
//        for (int i = 0; i < words.length; i++) {
//            StringBuilder sb = new StringBuilder();
//            String wd = words[i];
//            int j = 0;
//            while (j < wd.length()) {
//                if (Character.isLetterOrDigit(wd.charAt(j))) {
//                    sb.append(wd.charAt(j));
//                }
//                j++;
//            }
//            wd = sb.toString().toLowerCase();
//            words[i] = wd;
//            if (map.containsKey(wd)) {
//                Word word = map.get(wd);
//                word.count++;
//            } else {
//                map.put(wd, new Word(wd,1, i));
//            }
//        }
//
//
//        for (String s : map.keySet()) {
//            Word count = map.get(s);
//
//        }
//        String[][] res = new String[map.size()][2];
//        int index = 0;
//        for(Map.Entry<String,Word> entry : map.entrySet()) {
//            String key = entry.getKey();
//            Word value = entry.getValue();
//            res[index][0] = key;
//            res[index][1] = Integer.toString(value.count);
//        }
//        return res;
//    }


    public static void main(String[] args) {
//        String[][] test = WordCountEngine.wordCountTreeMap("Practice makes perfect. you'll only\n" +
//                "       get Perfect by practice. just practice!");
//        System.out.println(test.length);
//
        TreeMap<String, Integer> map = new TreeMap<>(Collections.reverseOrder());
        map.put("abc", 1);
        map.put("bbc", 1);
        map.put("dbc", 1);
        map.put("abcd", 1);
        map.put("cbc", 1);
        map.put("ebc", 1);
        for (Map.Entry<String, Integer> entry:  map.entrySet()) {
            System.out.println(entry.getKey());
        }

    }
}



