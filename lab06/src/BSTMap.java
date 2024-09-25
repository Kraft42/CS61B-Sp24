import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
    private class BSTNode{
        public K key;
        public V value;
        public BSTNode left = null;
        public BSTNode right = null;

        public BSTNode(K k,V v){
            key=k;
            value=v;
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        if(root == null){
            BSTNode n = new BSTNode(key,value);
            root = n;
            size++;
        }
        else {
            put_helper(root,key,value);
            size++;
        }
    }

    private BSTNode put_helper(BSTNode t,K key,V value){
        if(t == null)
            return new BSTNode(key,value);

        if(key.compareTo(t.key) > 0)
            t.right = put_helper(t.right,key,value);
        else if(key.compareTo(t.key) < 0)
            t.left = put_helper(t.left,key,value);
        else if(key.compareTo(t.key) == 0) {
            t.value = value;
            size--;
        }

        return t;
    }

    @Override
    public V get(K key) {
        return get_helper(root,key);
    }

    private V get_helper(BSTNode t,K key){
        if(t == null)
            return null;

        if(key.compareTo(t.key) == 0)
            return t.value;
        else if(key.compareTo(t.key) > 0)
            return get_helper(t.right,key);
        else if(key.compareTo(t.key) < 0)
            return get_helper(t.left,key);

        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey_helper(root,key);
    }

    public boolean containsKey_helper(BSTNode t,K key){
        if(t == null)
            return false;

        if(key.compareTo(t.key) == 0)
            return true;
        else if(key.compareTo(t.key) > 0)
            return containsKey_helper(t.right,key);
        else
            return containsKey_helper(t.left,key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
//        throw new UnsupportedOperationException();

        Set<K> result = new HashSet<>();
        return result;
    }

    @Override
    public V remove(K key) {
        BSTNode r_n = remove_helper(root,key);

        if(r_n == null)
            return null;
        else {
            if()
        }
    }

    private BSTNode remove_helper(BSTNode t,K key){
        if(t == null)
            return null;

        if(key.compareTo(t.key) == 0)
            return t;
        else if(key.compareTo(t.key) > 0)
            return remove_helper(t.right,key);
        else if(key.compareTo(t.key) < 0)
            return remove_helper(t.left,key);

        return null;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder(){

    }
}
