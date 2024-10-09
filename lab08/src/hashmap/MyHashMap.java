package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int Capacity;
    private double loadFactor;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        Capacity = 16;
        loadFactor = 0.75;
        buckets = new Collection[Capacity];
        for(Collection<Node> bucket:buckets){
            bucket = createBucket();
        }
    }

    public MyHashMap(int initialCapacity) {
        Capacity = initialCapacity;
        loadFactor = 0.75;
        buckets = new Collection[Capacity];
        for(Collection<Node> bucket:buckets){
            bucket = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        Capacity = initialCapacity;
        this.loadFactor = loadFactor;
        buckets = new Collection[Capacity];
        for(Collection<Node> bucket:buckets){
            bucket = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    private void resize(){}

    @Override
    public void put(K key, V value) {
        if((double) size() /Capacity >= loadFactor){
            resize();
        }

        Node newNode = new Node(key,value);
        int bucketNum = Math.floorMod(key.hashCode(),Capacity);
        if(buckets[bucketNum] == null){
            buckets[bucketNum] = createBucket();
        }
        buckets[bucketNum].add(newNode);
    }

    @Override
    public V get(K key) {
        int bucketNum = Math.floorMod(key.hashCode(),Capacity);
        for(Node n:buckets[bucketNum]){
            if(n.key.equals(key))
                return n.value;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int bucketNum = Math.floorMod(key.hashCode(),Capacity);
        for(Node n:buckets[bucketNum]){
            if(n.key.equals(key))
                return true;
        }
        return false;
    }

    @Override
    public int size() {
        int s = 0;
        for (Collection<Node> bucket : buckets) {
            s += bucket.size();
        }
        return s;
    }

    @Override
    public void clear() {
        buckets = new Collection[16];
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}
