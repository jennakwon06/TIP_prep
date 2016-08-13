package Q13_4;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by JennaKwon on 6/12/16.
 *
 *
 * Design a ISBN cache that looks up prices of books based on ISBN number
 * Use LRU for cache eviction
 * O(1) lookup, insert, remove desired
 *
 */
public class LRUCache {

    int cacheSize;
    int numElems; 
    double loadFactor; 

    /**
     * Hashmap with closed addressing
     * Each element in the array is a linked list
     */

//    private HashMap<T, ArrayList<E>> map = new HashMap<T, ArrayList<E>>();

    private LinkedList<String>[] hashMap;

    /**
     * Linked list that keeps track of least recently used elements
     */
    @SuppressWarnings("unchecked")
    public LRUCache() {
        loadFactor = 0.7; 
        cacheSize = 20; 
        hashMap = (LinkedList<String>[]) new LinkedList[cacheSize];
//        for (LinkedList<String> l : hashMap) { // doesn't work
//            l = new LinkedList<String>();
//        }
//
        for (int i = 0; i < hashMap.length; i++) {
            hashMap[i] = new LinkedList<>();
        }
    }

    /**
     * Insert 
     */
    public void insert(Book book) {
        int hashCode = Math.abs(book.getIsbn().hashCode());
        hashMap[hashCode % cacheSize].add(book.getPrice());
        numElems++;

        //resize
        if ((numElems / cacheSize) > loadFactor) {
            resize();
        }

//        updateLRUList();
    }

    /**
     *
     */
    public String lookUp(Book book) {
        int hashCode = Math.abs(book.getIsbn().hashCode());
        for (String str : hashMap[hashCode % cacheSize]) {
            if (book.getPrice().equals(str)) {
                return str;
            }
        }
        return "-1";
    }

    /**
     * Returns the price of the deleted book
     * -1 otherwise
     * @param book
     * @return
     */
    public String delete(Book book) {
        int hashCode = Math.abs(book.getIsbn().hashCode());
        LinkedList<String> list = hashMap[hashCode % cacheSize];
        for (int i = 0; i < list.size(); i++) {
            if (book.getPrice().equals(list.get(i))) {
                list.remove(list.get(i));
                return book.getPrice();
            }
        }
        return "-1";
    }

    public void resize() {

    }
    
    public String toString() {
        return Arrays.toString(hashMap);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache();
        System.out.println(cache);

        Book myBook = new Book("143527355B", "99");

        cache.insert(myBook); //void
        String result1 = cache.lookUp(myBook); //returns "99"
        String result2 = cache.delete(myBook); //returns "99"
        String result3 = cache.lookUp(myBook); //returns "-1"

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }

    private static class Book {
        String isbn;
        String price;


        public Book(String isbn, String price) {
            this.isbn = isbn;
            this.price = price;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getPrice() {
            return price;
        }
    }
}

