import java.util.Arrays;

public class BinaryMinHeap {
	
	private int capacity = 10;
	
	private int size = 0;
	
	private int[] items = new int[capacity];
	
	public boolean isEmpty() { return size == 0; };
	
	private int getLeftChildIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}
	
	private int getRightChildIndex(int parentIndex) {
		return 2 * parentIndex + 2;
	}

	private int getParentIndex(int childIndex) {
		return (childIndex - 1) / 2; 
	}
	
	private int getLeftChild(int parentIndex) {
		return this.items[getLeftChildIndex(parentIndex)];
	}
	
	private int getRightChild(int parentIndex) {
		return this.items[getRightChildIndex(parentIndex)];
	}
	
	private int getParent(int childIndex) {
		return this.items[getParentIndex(childIndex)];
	}
	
	private boolean hasLeftChild(int index) {
		return getLeftChildIndex(index) < this.size;
	}
	
	private boolean hasRightChild(int index) {
		return getRightChildIndex(index) < this.size;
	}	
	
	private boolean hasParent(int index) {
		return getParentIndex(index) >= 0;
	}
	
	private void swap(int a, int b) {
		int temp = this.items[a];
		this.items[a] = this.items[b];
		this.items[b] = temp;
	}
	
	private void expandCapacityIfNeeded() {
		if(this.size == this.capacity) {
			this.items = Arrays.copyOf(this.items, this.capacity * 2);
			capacity *= 2;
		}
	}
	
	public int peek() {
		if(this.size == 0) throw new IllegalStateException();
		return this.items[0];
	}
	
	public void add(int n) {
		expandCapacityIfNeeded();
		int index = this.size;
		this.items[index] = n;
		this.size++;
		
		//Heapify up
		while(hasParent(index) && this.items[index] < getParent(index)) {
			swap(index, getParentIndex(index));
			index = getParentIndex(index);
		}
	}
	
	public int remove() {
		if(this.size == 0) throw new IllegalStateException();
		int val = this.items[0];
		swap(0, this.size - 1);
		this.size--;
		
		//Heapify down
		int index = 0;
		while(hasLeftChild(index)) {
			int smallerChildIndex = getLeftChildIndex(index);
			if(hasRightChild(index)) {
				int rightChildIndex = getRightChildIndex(index);
				if(this.items[smallerChildIndex] > this.items[rightChildIndex]) {
					smallerChildIndex = rightChildIndex;
				}
			}
			
			if(this.items[index] > this.items[smallerChildIndex]) {
				swap(index, smallerChildIndex);
			}else {
				break;
			}
			index = smallerChildIndex;
		}
		return val;
	}
	

	public static void main(String[] args) {
		int[] arr = {5, 3, 9, 1, 0, 2, 4, 5, 150, 13};
		BinaryMinHeap heap = new BinaryMinHeap();
		for(int i : arr) {
			System.out.print("[" + i + "]");
			heap.add(i);
		}
		
		System.out.println("");
		
		while(!heap.isEmpty()) {
			System.out.print("[" + heap.remove() + "]");
		}
	}

}
