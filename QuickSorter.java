import java.util.Random;


public class QuickSorter {
	// methods used to test the algorithm
	private static void genRndArr(int[] arr) {
		Random rnd = new Random(System.currentTimeMillis());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rnd.nextInt(100);
		}
	}

	private static void printArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print("[" + arr[i] + "]");
		}
		System.out.println("\n");
	}

	public static void main(String[] args) {
		int[] myArray = new int[8];
		genRndArr(myArray);
		System.out.println("Unsorted Array:");
		printArr(myArray);
		
		quickSort(myArray);
		
		System.out.println("Sorted Array:");
		printArr(myArray);
	}
	
	//overloaded method - public interface
	public static void quickSort(int[] arr){
		quickSort(arr, 0, arr.length-1);
	}
	
	private static void quickSort(int[] arr, int left, int right){
		//elements less than pivot are moved to the left of pivot
		//elements greater than pivot are moved the the right of pivot
		int i = partition(arr, left, right);
		
		//recursively call quicksort with left sub array
		if(left < i-1){
			quickSort(arr, left, i-1);
		}
		
		//recursively call quicksort with right sub array
		if(right > i){
			quickSort(arr, i, right);
		}
	}
	
	private static int partition(int[] arr, int left, int right){
		int i = left;
		int j = right;
		
		int pivot = arr[(left+right)/2];
		
		while(i <= j){
			while(arr[i] < pivot){
				i++;
			}
			while(arr[j] > pivot){
				j--;
			}
			if(i <= j){
				swap(arr, i, j);
				i++;
				j--;
			}
		}
		
		return i;
	}
	
	private static void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
