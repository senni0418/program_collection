#include <stdio.h>
#include <string.h>

int is_same_diagonals(int n, int a[]){
    int i,j,k;
    int diagonals = 2*n - 3;
    int count = 0;
    int mark = 0;

    //main diagonal
    for (i=0;i<n-1;i++){
        if (a[i+i*n]==a[(i+1)+(i+1)*n]){
            count++;
        }
    }
    if (count==n-1){
        mark+=1;
    }
    else return 0;

    //lower diagonals
    for (k=2;k<n;k++){
        i=(k-1);
        j=(n-1);
        count = 0;
        while ((i>0)&&(j>((n-1)-k+1))){
            if (a[i+j*n]==a[(i-1)+(j-1)*n]){
                count++;
            }
            i--;
            j--;
        }
        if (count == k-1){
            mark+=1;
        }
        else return 0;
    }

    //upper diagonals
    for (k=n-1;k>=2;k--){
        i=(n-1);
        j=(k-1);
        count = 0;

        while ((i>(n-1-k+1))&&(j>0)){
            if (a[i+j*n]==a[(i-1)+(j-1)*n]){
                count++;
            }
            i--;
            j--;
        }
        if (count == k-1){
            mark+=1;
        }
        else return 0;
    }

    if (mark==diagonals){
        return 1;
    }
    else return 0;
}