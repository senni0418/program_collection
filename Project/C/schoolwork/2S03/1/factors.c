#include <stdio.h>

int main(){
    unsigned long x,y;
    unsigned long i,n;
    unsigned long sum = 0;
    unsigned long GCF=1;
    unsigned long LCM;

    printf("Enter two positive integers: ");
    scanf(" %lu %lu", &x, &y);

    if (x>y){
        n = y;
    }
    else{
        n = x;
    }
    printf("Common factors of (%lu,%lu): ",x,y);
    for (i=1;i<=n;i++){
        if ((x%i==0) && (y%i==0)){
            printf("%lu ",i);
            sum += i;
            if (GCF<i){
                GCF = i;
            }
        }
    }

    printf("\n");

    LCM = ((x * y) / GCF);

    sum += (LCM + GCF);

    printf("The greatest common factor(GCF) of (%lu,%lu): %lu\n",x,y,GCF);
    printf("The least common multiple(LCM) of (%lu,%lu): %lu\n",x,y,LCM);
    printf("The sum of all common factors, the GCF, the LCM: %lu\n",sum);

    return 0;
}