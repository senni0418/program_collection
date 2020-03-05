#include <stdio.h>
#include <string.h>

int main(){
    char x[20];
    int i;
    int lead,trail;

    printf("Enter decimal string: ");
    scanf("%s",x);

    if ((strlen(x)-1)>=20){
        trail = 19;
    }
    else{
        trail = strlen(x)-1;
    }
    
    lead = 0;

    while (x[lead]=='0'){
        lead++;
    }

    while (x[trail]=='0'){
        trail--;
    }

    for (i=trail;i>=lead;i--){
        printf("%c",x[i]);
    }
    printf("\n");

    return 0;
}