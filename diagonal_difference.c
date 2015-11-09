#include <stdio.h>
#include <stdlib.h>

int main() {

    int i, n, diagSumA = 0, diagSumB = 0;
    scanf("%d", &n);
    int matrix[n*n];

    for(i = 0; i < n*n; i++){
        scanf("%d", &matrix[i]);
    }

    for(i = 0; i < n*n; i+=(n+1)){
        diagSumA += matrix[i];
    }

    for(i = (n-1); i < n*n-1; i+=(n-1)){
        diagSumB += matrix[i];
    }

    int diff = abs(diagSumA-diagSumB);
    printf("%d", diff);


    return 0;
}