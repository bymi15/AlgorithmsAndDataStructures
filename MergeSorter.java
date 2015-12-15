//Mergesort algorithm
//Time-complexity -> best/worst case: O(nlogn)
//Space-complexity -> O(n)

import java.util.Random;

public class MergeSorter {

	static Random rnd = new Random(System.currentTimeMillis());
	//methods used to test the algorithm
	private static void genRndArr(int[] arr){
		for(int i = 0; i < arr.length; i++){
			arr[i] = rnd.nextInt(100); 
		}
	}
	
	private static void printArr(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print("[" + arr[i] + "]");
		}
		System.out.println("\n");
	}
	public static void main(String[] args) {
		int[] myArray = new int[8];
		genRndArr(myArray);
		System.out.println("Unsorted Array:");
		printArr(myArray);
		
		mergeSort(myArray);
		
		System.out.println("Sorted Array:");
		printArr(myArray);
		
	}
	
	//overloaded method - public interface
	public static void mergeSort(int[] arr){
		mergeSort(arr, 0, arr.length-1);
	}
	
	private static void mergeSort(int[] arr, int left, int right){
		//base case when there n arrays of size 1, hence left = right.
		if(left < right){ 
			int center = (left + right)/2;
			mergeSort(arr, left, center);
			mergeSort(arr, center+1, right);
			
			merge(arr, left, center, right);
		}
	}
	
	private static void merge(int[] arr, int left, int center, int right){
		//temporarily store the sorted sub-array
		int temp[] = new int[right - left + 1];
		
		//pointers
		int i, j, k;
		i = left;
		j = center + 1;
		k = 0;
		
		while(i <= center && j <= right){
			if(arr[i] < arr[j]){
				temp[k] = arr[i++];
			}else{
				temp[k] = arr[j++];
			}
			k++;
		}
		
		if(i <= center){
			while(i <= center){
				temp[k++] = arr[i++];
			}
		}else if(j <= right){
			while(j <= right){
				temp[k++] = arr[j++];
			}
		}
		
		for(int n = 0; n < temp.length; n++){
			arr[left + n] = temp[n];
		}
	}

}
