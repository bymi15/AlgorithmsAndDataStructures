import java.util.*;
import java.math.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println(fibonacci(n));
    }
    
    private static BigInteger fibonacci(int n){
        BigInteger[] fib = new BigInteger[n+1];
        fib[0] = BigInteger.ZERO;
        fib[1] = BigInteger.ONE;
        for(int i = 2; i <= n; i++){
            fib[i] = fib[i-1].add(fib[i-2]);
        }
        return fib[n];
    }
    
}
