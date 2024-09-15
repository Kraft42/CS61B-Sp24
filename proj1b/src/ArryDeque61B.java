import java.util.List;

public class ArryDeque61B<T> implements Deque61B<T> {
    private T[] arr;
    private int size;
    private int contain;
    private int nextFirst,nextLast;

    private void resize(){
        T[] new_arr = (T[]) new Object[size*2];
        for(int i = 0;i < size*2;i++){
            if(i<=nextFirst){
                new_arr[i] = arr[i];
            }
            else{
                i = i+size;
                new_arr[i] = arr[i-size];
            }
        }

        arr = new_arr;
        contain = size;
        nextFirst = nextFirst+size;
        size = size*2;
    }

    public ArryDeque61B(){
        arr = (T[]) new Object[8];
        size = 8;
        contain = size;
        nextFirst = 4;
        nextLast = 5;
    }

    @Override
    public void addFirst(T x) {
        if(contain == 0){
            resize();
        }
        arr[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst-1,size);
        contain--;
    }

    @Override
    public void addLast(T x) {
        if(contain == 0){
            resize();
        }
        arr[nextLast] = x;
        nextLast = Math.floorMod(nextLast+1,size);
        contain--;
    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return contain == size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        if(index<0||index>=size){
            return null;
        }
        for(int i = 0;i<size;i++){

        }
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
