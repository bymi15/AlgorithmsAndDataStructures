#include <stdio.h>
#include <stdlib.h>
#include <math.h>

/*
int getSumOfDigits(int arr[], int length){
    int theSum = 0;
    int i;
    for(i = 0; i < length; i++){
        theSum += arr[i];
    }
    return theSum;
}
*/

/*
unsigned long long int power(int a, int b){
    if(b <= 0){
        return 1;
    }
    return a * power(a, b-1);
}

*/


/*
// Normal recursive function to count 'n' digit numbers
// with sum of digits as 'sum'.
unsigned long long int countRec(int n, int sum)
{
    // Base case
    if (n == 0){
       return sum == 0;
    }

    // Initialize answer
    unsigned long long int ans = 0;

    // Traverse through every digit and count
    // numbers beginning with it using recursion
    for (int i=0; i<=9; i++){
       if (sum-i >= 0){
          ans += countRec(n-1, sum-i);
       }
    }

    return ans;
}
*/

//Memoization based implementation
//of the recursive function above


// A lookup table used for memoization
unsigned long long int lookup[101][50001];

unsigned long long int countRec(int n, int sum)
{
    // Base case
    if (n == 0){
       return sum == 0;
    }

    // If this subproblem is already evaluated,
    // return the evaluated value
    if (lookup[n][sum] != -1){
       return lookup[n][sum];
    }

    // Initialize answer
    unsigned long long int ans = 0;

    // Traverse through every digit and
    // recursively count numbers beginning
    // with it
    for (int i=0; i<=9; i++){
       if (sum-i >= 0){
          ans += countRec(n-1, sum-i);
       }
    }
    lookup[n][sum] = ans;

    return ans;
}


int main()
{
    int numDigits;
    int sumRequired;

    int n = 10;

    // Initialize all entries of lookup table to -1
    memset(lookup, -1, sizeof lookup);

    while(--n >= 0){
        scanf("%d %d", &numDigits, &sumRequired);
        int totalCount = countRec(numDigits, sumRequired);

        printf("\n%d\n", totalCount);
    }

    return 0;
}
