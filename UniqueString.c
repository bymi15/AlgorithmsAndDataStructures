#include <stdio.h>
#include <stdlib.h>

int main()
{
    char str[100];
    printf("Enter a string (ASCII only): \n");
    scanf("%s", str);

    int chr[256] = {0};
    int i;
    for(i = 0; i < strlen(str); i++){
        int ascii = str[i];
        chr[ascii]++;
    }

    int isUnique = 1; //0=false, 1=true

    for(i = 0; i < 256; i++){
        if(chr[i]>1){
            isUnique = 0;
            break;
        }
    }

    if(isUnique == 1){
        printf("\nThe string: [%s] is unique (no repeated characters)\n\n", str);
    }else{
        printf("\nThe string: [%s] is not unique (has repeated characters)\n\n", str);
    }

    return 0;
}
