import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a, b, n, i;
        a = scan.nextInt();
        b = scan.nextInt();
        n = scan.nextInt();
        
        BigInteger[] lookup = new BigInteger[21];

        lookup[0] = BigInteger.valueOf(a);
        lookup[1] = BigInteger.valueOf(b);

        for(i = 2; i < n; i++){
            lookup[i] = lookup[i-1].pow(2).add(lookup[i-2]);
        }

        System.out.println(lookup[i-1]);
    }
}
