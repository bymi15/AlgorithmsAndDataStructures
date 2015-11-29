// This program finds a quadratic equation that produces the maximum number
// of primes for consecutive values of n.

import java.util.*;
import java.math.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int maxVal = 0;
        int maxA = 0, maxB = 0;
        for(int a = -N; a <= N; a++){
            for(int b = -N; b <= N; b++){
                int count = 0, i = 0;
                while(isPrime(Math.abs(i*i + a*i + b))){
                    count++;
                    i++;
                }
                if(count > maxVal){
                    maxVal = count;
                    maxA = a;
                    maxB = b;
                }
            }
        }
        
        System.out.println(maxA + " " + maxB);
        
    }
    
    private static boolean isPrime(int n){
        if(n == 2) return true;
        if(n % 2 == 0) return false;
        for(int i = 3; i*i <= n; i+=2){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }
}
