import java.util.*;
import java.math.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BigInteger sum = BigInteger.ZERO;
        
        int t = Integer.parseInt(scan.nextLine());
        for(int i = 0; i < t; i++){
            String s = scan.nextLine();
            BigInteger n = new BigInteger(s);
            sum = sum.add(n);
        }
        
        System.out.print(sum);
    }
}
