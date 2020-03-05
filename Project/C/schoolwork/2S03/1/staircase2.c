#include <stdio.h>

void create_lines(int i, int m);
void row_staircase(int p, int m);


void main(){
    unsigned int n, m, p;
    int i;
    int remainder = n%5;
    int rows;

    printf("Enter number of: staircases, stairs, rows: ");
    scanf(" %d %d %d", &n, &m, &p);

    rows = n/p;
    remainder = n%p;
    if (((n>=0) && (n<=100)) && ((m>=1) && (m<=9)) && ((p>=1) && (p<=100))){
                for (i=0;i<rows;i++){
                    row_staircase(p,m);
                }
                if (remainder != 0){
                    row_staircase(remainder,m);
                }
                else return;
    }
    if (((n<0) || (n>100)) && ((m>=1) && (m<=9)) && ((p>=1) && (p<=100))){
        printf("n=%d, must be in [0,100]\n",n);
    }
    if (((n>=0) && (n<=100)) && ((m<1) || (m>9)) && ((p>=1) && (p<=100))){
        printf("m=%d, must be in [1,9]\n", m);
    }
    if (((n>=0) && (n<=100)) && ((m>=1) && (m<=9)) && ((p<1) || (p>100))){
        printf("p=%d, must be in [1,100]\n", p);
    }
    if (((n<0) || (n>100)) && ((m<1) || (m>9)) && ((p>=1) && (p<=100))){
        printf("n=%d, must be in [0,100]\n",n);
        printf("m=%d, must be in [1,9]\n", m);
    }
    if (((n<0) || (n>100)) && ((m>=1) && (m<=9)) && ((p<1) || (p>100))){
        printf("n=%d, must be in [0,100]\n",n);
        printf("p=%d, must be in [1,100]\n", p);
    }
    if (((n>=0) && (n<=100)) && ((m<1) || (m>9)) && ((p<1) || (p>100))){
        printf("m=%d, must be in [1,9]\n",m);
        printf("p=%d, must be in [1,100]\n", p);
    }
    if (((n<0) || (n>100)) && ((m<1) || (m>9)) && ((p<1) || (p>100))){
        printf("n=%d, must be in [0,100]\n",n);
        printf("m=%d, must be in [1,9]\n", m);
        printf("p=%d, must be in [1,100]\n", p);
    }
    return;
}



void create_lines(int i, int m){ //print i th line for staircase with m stairs
    int j;
    switch (i){
        case 1: 
            for (j=0;j<(m-1);j++){
                printf(" ");
            }
            printf("%d",m);
            printf(" ");
            break;

        case 2:
            for (j=0; j<(m-2);j++){
                printf(" ");
            }
            for (j=0;j<2;j++){
                printf("%d",m);
            }
            printf(" ");
            break;

        case 3:
            for (j=0; j<(m-3);j++){
                printf(" ");
            }
            for (j=0;j<3;j++){
                printf("%d",m);
            }
            printf(" ");
            break;

        case 4:
            for (j=0; j<(m-4);j++){
                printf(" ");
            }
            for (j=0;j<4;j++){
                printf("%d",m);
            }
            printf(" ");
            break;
            
        case 5:
            for (j=0; j<(m-5);j++){
                printf(" ");
            }
            for (j=0;j<5;j++){
                printf("%d",m);
            }
            printf(" ");
            break;

        case 6:
            for (j=0; j<(m-6);j++){
                printf(" ");
            }
            for (j=0;j<6;j++){
                printf("%d",m);
            }
            printf(" ");
            break;

        case 7:
            for (j=0; j<(m-7);j++){
                printf(" ");
            }
            for (j=0;j<7;j++){
                printf("%d",m);
            }
            printf(" ");
            break;

        case 8:
            for (j=0; j<(m-8);j++){
                printf(" ");
            }
            for (j=0;j<8;j++){
                printf("%d",m);
            }
            printf(" ");
            break;

        case 9:
            for (j=0; j<(m-9);j++){
                printf(" ");
            }
            for (j=0;j<9;j++){
                printf("%d",m);
            }
            printf(" ");
            break;
            }
    }

void row_staircase(int p, int m){
    int i,j;
     for (i=1;i<(m+1);i++){
         for (j=0;j<p;j++){
             create_lines(i,m);
         }
         printf("\n");
     }
     return;
}