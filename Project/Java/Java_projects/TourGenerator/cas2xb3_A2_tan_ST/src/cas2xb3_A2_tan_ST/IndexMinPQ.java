package cas2xb3_A2_tan_ST;

import java.util.Iterator;

public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int N;        // maximum size on PQ
    private int n;           // size of PQ
    private int[] pq;        // binary heap
    private int[] inverse_pq;        // inverse of pq
    private Key[] keys;      // keys, the priority

    // method that check if one element's key is greater than other
    private boolean greater(int i, int j) {
    	int item_i = pq[i];
    	int item_j = pq[j];
    	Key key_i = keys[item_i];
    	Key key_j = keys[item_j];
    	int cmp = key_i.compareTo(key_j);
    	if (cmp > 0)
    		return true;
        return false;
    }

    // method that exchange two elements
    private void exch(int i, int j) {
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
        inverse_pq[pq[i]] = i;
        inverse_pq[pq[j]] = j;
    }
    
    // refer to text book
    private void swim(int i) {
        while (i > 1 && greater(i/2, i)) {
            exch(i, i/2);
            i = i/2;
        }
    }
    
    // refer to text book
    private void sink(int i) {
        while (2*i <= n) {
            int j = 2*i;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(i, j)) break;
            exch(i, j);
            i = j;
        }
    }
    
    // constructor
    public IndexMinPQ(int N) {
        this.N = N;
        n = 0;
        keys = (Key[]) new Comparable[N + 1]; // initialize keys[]
        pq   = new int[N + 1]; // initialize pq[]
        // initialize inverse_pq[]
        inverse_pq   = new int[N + 1]; 
        for (int i = 0; i <= N; i++)
            inverse_pq[i] = -1;
    }
    
    // check if the PQ is empty
    public boolean isEmpty() {
        if (n==0)
        	return true;
        return false;
    }

    // check if the PQ contains the element
    public boolean contains(int i) {
    	if ((i >= 0) && (i < N))
    		return inverse_pq[i] != -1;
    	return false;
    }

    // return the number of elements in the PQ
    public int size() {
        return n;
    }

    // insert an element
    public void insert(int i, Key key) {
        if (contains(i) && ((i < 0) || (i >= N))) 
        	return;
        n++;
        inverse_pq[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    // delete and return the minimum element
    public int delMin() {
        int min = pq[1];
        exch(1, n--);
        sink(1);
        // delete
        inverse_pq[min] = -1; 
        keys[min] = null;    
        return min;
    }

    // change the key of a given element
    public void changeKey(int i, Key key) {
    	if (!contains(i) || ((i < 0) || (i >= N)))
    		return;
        keys[i] = key;
        swim(inverse_pq[i]);
        sink(inverse_pq[i]);
    }

    // delete an element
    public void delete(int i) {
    	if (!contains(i) || ((i < 0) || (i >= N)))
    		return;
        int index = inverse_pq[i];
        exch(index, n);
        swim(index);
        sink(index);
        keys[i] = null;
        inverse_pq[i] = -1;
        n--;
    }
    
    public Iterator<Integer> iterator() { 
    	return new HeapIterator(); 
    }

    // construct a heap
    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private IndexMinPQ<Key> new_pq;

        public HeapIterator() {
        	// add all elements to new_pq
            new_pq = new IndexMinPQ<Key>(pq.length - 1);
            for (int i = 1; i <= n; i++)
                new_pq.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext() {
        	return !new_pq.isEmpty();
        }

        public Integer next() {
            return new_pq.delMin();
        }
    }


}