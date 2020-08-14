package queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 47un
 * 
 * The task is to design and implement methods of an LRU cache. 
 * The class has two methods get and set which are defined as follows.
 * get(x)   : Gets the value of the key x if the key exists in the cache otherwise returns -1
 * set(x,y) : inserts the value if the key x is not already present. 
 * If the cache reaches its capacity it should invalidate the least recently used item before inserting the new item.
 * In the constructor of the class the size of the cache should be initialized.
 * 
 * http://www.geeksforgeeks.org/implement-lru-cache/
 * 
 * METHOD 1 : Use a deque to store the keys
 *            A Map holds the cached data (key value pair)
 *            get(data)
 *             Scourge the cache for the data, if found, remove it and add it to the front. Return the mapping for this key in the cache.
 *            set(data, value)
 *             If the capacity has reached, remove the last data
 *             Add the new data to the front and add the mapping to the cache 
 *
 * TIME     : O(n)  // For get()
 * SPACE    : O(1)
 *
 * 
 */

public class LRU_Cache {

    private Deque<Integer> keys;
    private Map<Integer, String> cache;
    private int capacity;

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 3, 1, 2, 4, 5, 6, 7, 6, 7, 6, 7, 6, 7, 7, 3, 1, 6};
        LRU_Cache lruCache = new LRU_Cache(2);
        for(int item :  array) {
            String dataFromCache = lruCache.get(item);
            if(dataFromCache == null)
                lruCache.set(item, generateValue(item));
            else
                System.out.println(dataFromCache);
        }
    }

    public LRU_Cache(int capacity){     
        this.keys     = new ArrayDeque<Integer>(capacity);
        this.cache    = new HashMap<Integer, String>(); 
        this.capacity = capacity;
    }

    public String get(Integer data) {
        for(Integer item : this.keys)
            if(item == data) {
                System.out.print("Hit for " + item + " : ");
                keys.remove(item);
                keys.addFirst(item);
                return cache.get(item);
            }
        System.out.print("Miss for " + data + ". ");
        return null;
    }

    public void set(Integer key, String value) {
        if(keys.size() > capacity)
            keys.removeLast();
        System.out.println("Cached " + key + " " + value);
        keys.addFirst(key);
        cache.put(key, value);
    }

    private static String generateValue(int item) {
        switch(item) {
        case 0  : return "Zero";
        case 1  : return "One";
        case 2  : return "Two";
        case 3  : return "Three";
        case 4  : return "Four";
        case 5  : return "Five";
        case 6  : return "Six";
        case 7  : return "Seven";
        case 8  : return "Eight";
        case 9  : return "Nine";
        case 10 : return "Ten";
        default : return "x";
        }
    }
}