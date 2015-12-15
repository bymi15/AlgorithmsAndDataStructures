//Algorithm that reverses the characters in a string
//Time-complexity -> O(n)
//Space-complexity -> O(n)

import java.util.Scanner;

public class ReverseString{

  //testing
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter a string:");
    String input = scan.nextLine();
    System.out.println(reverse(input));
    
  }
  
  public static String reverse(String str){
    char[] reversed = new char[str.length()];
    char[] original = str.toCharArray();
    int j = 0;
    for(int i = original.length-1; i >= 0; i--){
      reversed[j++] = original[i];
    }
    return new String(reversed);
  }
}
