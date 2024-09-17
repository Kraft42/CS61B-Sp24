import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] item;
    private int size;
    private int max_size;
    private int nextFirst,nextLast;

    public int getMax_size(){
        return max_size;
    }

    private void resize_up(){
        max_size = max_size*2;
        T[] new_item = (T[]) new Object[max_size];

        int index = Math.floorMod(nextFirst+1,size);
        for(int i = 0;i<size;i++){
            new_item[i] = item[index];
            index = Math.floorMod(index+1,size);
        }

        nextFirst = Math.floorMod(-1,max_size);
        nextLast = Math.floorMod(size,max_size);
        item = new_item;
    }

    private  void resize_down(){
        max_size = max_size/2;
        T[] new_item = (T[]) new Object[max_size];

        int index = Math.floorMod(nextFirst+1,size);
        for(int i = 0;i<size;i++){
            new_item[i] = item[index];
            index = Math.floorMod(index+1,size);
        }

        nextFirst = Math.floorMod(-1,max_size);
        nextLast = Math.floorMod(size,max_size);
        item = new_item;
    }

    public ArrayDeque61B(){
        max_size = 8;
        item = (T[]) new Object[max_size];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    @Override
    public void addFirst(T x) {
        if(size == max_size){
            resize_up();
        }
        item[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst-1,max_size);
        size++;
    }

    @Override
    public void addLast(T x) {
        if(size == max_size){
            resize_up();
        }
        item[nextLast] = x;
        nextLast = Math.floorMod(nextLast+1,max_size);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int index = Math.floorMod(nextFirst+1,max_size);
        for(int i = 0;i<size;i++){
            returnList.add(item[index]);
            index = Math.floorMod(index+1,max_size);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        while(size <= max_size*0.25){
            resize_down();
        }
        nextFirst = Math.floorMod(nextFirst+1,max_size);
        size--;
        return null;
    }

    @Override
    public T removeLast() {
        while (size <= max_size*0.25){
            resize_down();
        }
        nextLast = Math.floorMod(nextLast-1,max_size);
        size--;
        return null;
    }

    @Override
    public T get(int index) {
        if(index<0||index>=size){
            return null;
        }
        return item[Math.floorMod(nextFirst+1+index,max_size)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
