int recursion(int n, int m, int A[], int i, int j, int k, int l, int path[][2]){
    if ((A[i*m+j]==1) || (A[k*m+l==1])){
        return 0;
    }
    if ((i<0) || (i>=n)){
        return 0;
    }
    if ((j<0) || (j>=m)){
        return 0;
    }
    if (A[i*m+j]==9){
        return 0;
    }
    if ((i==k) && (j==l)){
        if ((A[i*m+j]==0) && (A[k*m+l]==0)){
            return 1;
        }
    }

    A[i*m+j]=9;

    if (recursion(n,m,A,i-1,j,k,l,path)){
        return 2;
    }
    else if (recursion(n,m,A,i+1,j,k,l,path)){
        return 2;
    }
    else if (recursion(n,m,A,i,j-1,k,l,path)){
        return 2;
    }
    else if (recursion(n,m,A,i,j+1,k,l,path)){
        return 2;
    }
    else{
        return 0;
    }

}

int find_path(int n, int m, int A[], int i, int j, int k, int l, int path[][2]){
    if ((A[i*m+j]==1) || (A[k*m+l==1])){
        return -1;
    }
    if ((i<0) || (i>=n)){
        return -2;
    }
    if ((j<0) || (j>=m)){
        return -3;
    }
    if ((i==k) && (j==l)){
        if ((A[i*m+j]==0) && (A[k*m+l]==0)){
            return 1;
        }
    }

    int result = recursion(n,m,A,i,j,k,l,path);

    int x,y;
    for (x=0;x<n;x++){
        for (y=0;y<m;y++){
            if (A[x*m+y]==9){
                A[x*m+y]=0;
            }
        }
    }

    return result;
}