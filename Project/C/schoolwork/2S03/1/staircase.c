#include <stdio.h>

int row_staircase();
void last_row(int n);

void main(){
    int n, i;
    int remainder = n%5;
    int rows;

    printf("Enter number of staircases (must be in [0,100]): ");
    scanf(" %d", &n);
    rows = n/5;

    if ((n>=0) && (n<=100)){
        for (i=0; i<rows; i++){
            row_staircase();
        }
        if (remainder !=0){
            last_row(n);
        }
        else return;
    }
    else{
        printf("n=%d, must be in [0,100]\n",n);
    }
    return;
}

int row_staircase(){
    printf("  #   #   #   #   #\n");
    printf(" ##  ##  ##  ##  ##\n");
    printf("### ### ### ### ###\n");
    return 0;
}

void last_row(int n){
    int i, remainder;
    remainder = n%5;
    
    for (i=0; i<remainder; i++){
        printf("  # ");
    }
    printf("\n");

    for (i=0; i<remainder; i++){
        printf(" ## ");
    }
    printf("\n");

    for (i=0; i<remainder; i++){
        printf("### ");
    }
    printf("\n");
}