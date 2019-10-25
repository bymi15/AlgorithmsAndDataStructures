import java.util.HashMap;

public class LRUCache {
    
    private int capacity;
    private final Node dummyHead = new Node();
    private final Node dummyTail = new Node();
    
    private HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
    	this.dummyHead.next = this.dummyTail;
    	this.dummyTail.prev = this.dummyHead;
        this.capacity = capacity;
        this.map = new HashMap<Integer, Node>(capacity);
    }
    
    public int get(int key) {
        Node node = map.get(key);
        int val = -1;
        if(node != null){
            val = node.val;
            remove(node);
            add(node);
        }
        return val;
    }
    
    public void put(int key, int value) {
    	Node node = map.get(key);
    	if(node != null) {
    		remove(node);
    		node.val = value;
    		add(node);
    	}else {
            //cache is full, remove tail
            if(map.size() == capacity){
                Node nodeToEvict = dummyTail.prev;
                map.remove(nodeToEvict.key);
                remove(nodeToEvict);
            }

            node = new Node();
            node.key = key;
            node.val = value;
            add(node);
            map.put(key, node);
    	}
    }
    
    private void add(Node node){
        Node oldHead = dummyHead.next;
        node.next = oldHead;
        oldHead.prev = node;
        dummyHead.next = node;
        node.prev = dummyHead;
    }
    
    private void remove(Node node){
        Node prev = node.prev;
        Node next = node.next;
        next.prev = prev;
        prev.next = next;
    }
    
    private class Node{
        public int key;
        public int val;
        public Node prev;
        public Node next;
    }
    
    public static void main(String[] args) {
    	LRUCache cache = new LRUCache(2);
    	cache.put(1, 1);
    	cache.put(2, 2);
    	cache.get(1);       // returns 1
    	cache.put(3, 3);    // evicts key 2
    	cache.get(2);       // returns -1 (not found)
    	cache.put(4, 4);    // evicts key 1
    	cache.get(1);       // returns -1 (not found)
    	cache.get(3);       // returns 3
    	cache.get(4);       // returns 4
    	System.out.println("Done");
    }
}