//LinkedList data structure implemented in Java.
//Created by Brian Min

public class LinkedList<T> {
  //head is empty, contains a pointer to the first element of the linked list
	private Node head;
	private int size;

	public LinkedList(){
		head = new Node();
	}
	
	public int getSize(){
		return size;
	}
	
	//inserts an element at the end of the linked list
	public void add(T data){
		Node current = head;
		while(current.getNext() != null){
			current = current.getNext();
		}
		current.setNext(new Node(data));
		size++;
	}
	
	//inserts an element at the nth position of the linked list
	public void add(T data, int n){
		if(n < 0) return;
		
		int i = 0;
		Node current = head;
		Node newNode = new Node(data);
		
		do{
			if(i++ == n){
				newNode.setNext(current.getNext());
				current.setNext(newNode);
				size++;
			}
			current = current.getNext();
		}while(current.getNext() != null);
		
	}
	
	//replaces the nth element of the linked list
	public void replace(T data, int n) {
		if(n < 0) return;

		int i = 0;
		Node current = head;
		Node newNode = new Node(data);

		do{
			if(i++ == n){
				newNode.setNext(current.getNext().getNext());
				current.setNext(newNode);
			}
			current = current.getNext();
		}while(current.getNext() != null);

	}
	
	//removes the last element from the linked list
	public boolean removeLast(){
		Node current = head;
		if(current.getNext() == null){
			return false;
		}
		while(current.getNext().getNext() != null){
			current = current.getNext();
		}
		current.setNext(null);
		size--;
		return true;
	}
	
	//removes the nth element of the linked list
	public boolean remove(int n){
		if(n < 0) return false;
		
		int i = 0;
		Node current = head;
		do{
			if(i++ == n){
				current.setNext(current.getNext().getNext());
				size--;
				return true;
			}
			current = current.getNext();
		}while(current.getNext() != null);
		
		return false;
	}
	
	public void clear(){
		head.setNext(null);
		//automatic garbage collection
		//unused memory is freed
	}
	
	public String toString(){
		Node current = head;
		String str = "";
		while(current.getNext() != null){
			current = current.getNext();
			str += "[" + current.getData().toString() + "]";
		}
		return str;
	}
	
	public static void main(String[] args){
		LinkedList<Integer> numbers = new LinkedList<Integer>();
		for(int i = 0; i < 10; i++){
			numbers.add(i);
		}
		System.out.println(numbers.toString());
	}
	
	class Node{
		private T data;
		private Node next;
		
		public Node(){
			data = null;
			next = null;
		}
		
		public Node(T data){
			this.data = data;
			next = null;
		}
		
		public Node(T data, Node next){
			this.data = data;
			this.next = next;
		}
		
		public void setData(T data){
			this.data = data;
		}
		
		public void setNext(Node next){
			this.next = next;
		}
		
		public T getData(){
			return data;
		}
		
		public Node getNext(){
			return next;
		}
	}

}
