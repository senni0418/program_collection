#include <stdio.h>

int main(){
    unsigned long int x;
    long int new_num = 0;
    printf("Enter a integer: ");
    scanf(" %ld", &x);
    while (x!=0){
        new_num = new_num * 10;
        new_num += x%10;
        x = x/10;
    }
    printf("%ld\n",new_num);
    return 0;
}