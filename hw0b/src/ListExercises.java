import java.util.List;
import java.util.ArrayList;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int total = 0;
        for(int elem: L){
            total += elem;
        }
        return total;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> e = new ArrayList<>();
        for(int elem: L){
            if(elem % 2 == 0){e.add((elem));}
        }
        if(!e.isEmpty()){return e;}
        return null;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> N = new ArrayList<>();
        for(int i:L1){
            for(int j:L2){
                if(i == j){N.add(j);}
            }
        }
        return N;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int times = 0;
        for(String w:words){
            for(int i = 0;i < w.length();i++){
                if(c == w.charAt(i)){times++;}
            }
        }
        return times;
    }
}
