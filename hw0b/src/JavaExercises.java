import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaExercises {

    /** Returns an array [1, 2, 3, 4, 5, 6] */
    public static int[] makeDice() {
        // TODO: Fill in this function.
        int[] a = {1,2,3,4,5,6};
        return a;
    }

    /** Returns the order depending on the customer.
     *  If the customer is Ergun, return ["beyti", "pizza", "hamburger", "tea"].
     *  If the customer is Erik, return ["sushi", "pasta", "avocado", "coffee"].
     *  In any other case, return an empty String[] of size 3. */
    public static String[] takeOrder(String customer) {
        // TODO: Fill in this function.
        String[] r = null;
        if(customer.equals("Ergun")){
            String[] result = {"beyti","pizza","hamburger","tea"};
            r = result;
        }
        else if(customer.equals("Erik")){
            String[] result = {"sushi","pasta","avocado","coffee"};
            r =result;
        }
        else {
            String[] result = new String[3];
            r = result;
        }
        return r;
    }

    /** Returns the positive difference between the maximum element and minimum element of the given array.
     *  Assumes array is nonempty. */
    public static int findMinMax(int[] array) {
        // TODO: Fill in this function.
        int min = array[0];
        int max = array[0];
        for(int i : array){
            if(i < min){min = i;}
            else if (i > max){max = i;}
        }
        int result = max - min;
        return result;
    }

    /**
      * Uses recursion to compute the hailstone sequence as a list of integers starting from an input number n.
      * Hailstone sequence is described as:
      *    - Pick a positive integer n as the start
      *        - If n is even, divide n by 2
      *        - If n is odd, multiply n by 3 and add 1
      *    - Continue this process until n is 1
      */
    public static List<Integer> hailstone(int n) {
        return hailstoneHelper(n, new ArrayList<>());
    }

    private static List<Integer> hailstoneHelper(int x, List<Integer> list) {
        // TODO: Fill in this function.
        if(x==1){list.add(x);}
        else if (x % 2 == 0){list.add(x);
            hailstoneHelper(x/2,list);}
        else{list.add(x);
            hailstoneHelper(3*x+1,list);}
        return list;
    }

}
