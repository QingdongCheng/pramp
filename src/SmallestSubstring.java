import java.util.*;

public class SmallestSubstring {

    public List<Integer> product(int[] data){
        int count = 1;
        for(int i =0; i < data.length; i++){
            if(data[i] != 0){
                count = count*data[i];
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i =0; i < data.length -1; i++){
            if(data[i] == 0){
                result.add(count);
            }
            result.add(count/data[i]);
        }
        return result;
    }


    public static void main(String[] args) {
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> l3 = new ArrayList<>(Arrays.asList(1,3,2));

        set.add(l1);
        set.add(l2);
        set.add(l3);

        System.out.println(set.size());
    }
}
