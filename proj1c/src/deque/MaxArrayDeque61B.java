package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> MA_Comparator;

    public MaxArrayDeque61B(Comparator<T> c){
        MA_Comparator = c;
    }

    public T max(){
        if(isEmpty()){
            return null;
        }
        T max_result = get(0);
        for(T i:this){
            int cmp = MA_Comparator.compare(i,max_result);
            if(cmp > 0){
                max_result = i;
            }
        }
        return max_result;
    }

    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        T max_result = get(0);
        for(T i:this){
            int cmp = c.compare(i,max_result);
            if(cmp > 0){
                max_result = i;
            }
        }
        return max_result;
    }

}
