#include <stdio.h>

int find_path(int n, int m, int A[], int i, int j, int k, int l, int path[][2]);

int main() {
    int n, m;
    scanf("%d %d", &n, &m);

    int x0, y0, x1, y1;
    scanf("%d %d %d %d", &x0, &y0, &x1, &y1);

    int A[n*m], i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            scanf("%d", A + i * m + j);
        }
    }

    int path[n * m][2];
    int length = find_path(n, m, A, x0, y0, x1, y1, path);
    
    if (length > 0) {
        printf("(%d, %d) is connected to (%d, %d)\n", x0, y0, x1, y1);
        printf("Path:\n");
        for (i = 0; i < length; i++) {
            printf("(%d, %d)\n", path[i][0], path[i][1]);
        }
    } else {
        printf("(%d, %d) is not connected to (%d, %d)\n", x0, y0, x1, y1);
    }

    return 0;
}

