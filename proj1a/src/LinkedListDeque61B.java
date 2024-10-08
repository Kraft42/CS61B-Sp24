import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B <T> implements Deque61B<T>{
    private class DequeNode{
        public T item;
        public DequeNode prev;
        public DequeNode next;
    }

    private DequeNode sentinel = new DequeNode();
    private int size;

    public LinkedListDeque61B(){
        sentinel.item = null;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        DequeNode NewNode = new DequeNode();
        NewNode.item = x;
        NewNode.next = sentinel.next;
        NewNode.prev = sentinel;
        sentinel.next.prev = NewNode;
        sentinel.next = NewNode;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        DequeNode NewNode = new DequeNode();
        NewNode.item = x;
        NewNode.prev = sentinel.prev;
        NewNode.next = sentinel;
        sentinel.prev.next = NewNode;
        sentinel.prev = NewNode;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        DequeNode pointer = sentinel.next;
        while (pointer != sentinel){
            returnList.add(pointer.item);
            pointer = pointer.next;
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
        if(size == 0){
            return null;
        }
        DequeNode first_node = sentinel.next;
        first_node.next.prev = sentinel;
        sentinel.next = first_node.next;
        size--;
        return first_node.item;
    }

    @Override
    public T removeLast() {
        if(size == 0){
            return null;
        }
        DequeNode final_node = sentinel.prev;
        final_node.prev.next = sentinel;
        sentinel.prev = final_node.prev;
        size--;
        return final_node.item;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size){
            return null;
        }
        DequeNode pointer = sentinel;
        for(int i = 0;i <= index;i++){
            pointer = pointer.next;
        }
        return pointer.item;
    }

    private T getRecursiveHelper(DequeNode p,int i){
        if(i == 0){
            return p.item;
        }
        else {
            return getRecursiveHelper(p.next,i-1);
        }
    }

    @Override
    public T getRecursive(int index) {
        if(index < 0 || index >= size){
            return null;
        }

        DequeNode ptr = sentinel.next;
        return getRecursiveHelper(ptr,index);
    }
}
