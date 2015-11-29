import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Number of integers you wish to enter: ");
        int n = scan.nextInt();
        
        int[] arr = new int[n];
        for(i = 0; i < n; i++){
            System.out.println("Enter integer " + (i + 1));
            arr[i] = scan.nextInt();
        }
        
        int sortedArray = countingSort(n, arr);
        
        System.out.println("Sorted array:");
        
        for(i = 0; i < n; i++){
            System.out.print(sortedArray[i] + " ");
        }
    }
    
    private int[] countingSort(int n, int[] arr){
        int max = getMax(arr) + 1;
        int[] count = new int[max];
        int[] sorted = new int[n];
        
        for(i = 0; i < n; i++){
            count[arr[i]]++;
        }

        for(i = 1; i < max; i++){
            count[i] += count[i-1];
        }

        for (i = n - 1; i >= 0; i--){
            sorted[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        
        return sorted;
    }
    
    private int getMax(int[] arr){
        int max = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }
}
