#include "bmp.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*determine the lenth of the nth fibonacci word*/
int fib_size(int n){
    if (n==0)  return 0;
    if ( n==1 || n==2 )  return 1;
    return fib_size(n-1) + fib_size(n-2);
}
/*make the fibonacci string*/
char * fibword(int n){
    int len = fib_size(n);
    char *word = malloc((len+1) * sizeof(char));
    char *tmp = malloc((len+1) * sizeof(char));
    char *Sn_1 = malloc((len+1) * sizeof(char));
    char *Sn = malloc((len+1) * sizeof(char));
    char one[2] = {'1'};
    char zero[2] = {'0'};
    char zero_one[3] = {'0','1'};

    if (n==0) return NULL;
    if (n==1) {
        strcpy(word,one);
        free(tmp);
        free(Sn);
        free(Sn_1);
        return word;
    }
    if (n==2) {
        strcpy(word,zero);
        free(tmp);
        free(Sn);
        free(Sn_1);
        return word;
    }

    if (n==3) {
        strcpy(word,zero_one);
        free(tmp);
        free(Sn);
        free(Sn_1);
        return word;
    }
    strcpy(Sn_1,zero);
    strcpy(Sn,zero_one);
    n-=1;
    int i;
    for (i = 3; i <= n; i++){
        strcpy(tmp,Sn);
        strcat(Sn,Sn_1);
        strcpy(Sn_1, tmp);
    }

    strcpy(word, Sn);
    free(tmp);
    free(Sn);
    free(Sn_1);
    
    return word;
}
/*direction*/
typedef enum {
    up,
    down,
    left,
    right
} direction;
/*draw a segment*/
void draw(int x, int y, direction dir, int step, int w, RGB* im, RGB c){
#define I(i, j) im[(i)*w + j]
    int j;
    if (dir == up)
        for (j = 0; j < step; j++)
            I(x + j, y) = c;
    if (dir == down)
        for (j = 0; j < step; j++)
            I(x - j, y) = c;
    if (dir == right)
        for (j = 0; j < step; j++)
            I(x, y + j) = c;
    if (dir == left)
        for (j = 0; j < step; j++)
            I(x, y - j) = c;
#undef I
}
/*make a turn in direction*/
direction turn(direction dir, direction to_turn90){
    if (to_turn90 == right){
        if (dir == up){
            return right;
        }
        if (dir == right){
            return down;
        }
        if (dir == down){
            return left;
        }
        if (dir == left){
            return up;
        }
    }
    if (to_turn90 == left){
        if (dir == up){
            return left;
        }
        if (dir == left){
            return down;
        }
        if (dir == down){
            return right;
        }
        if (dir == right){
            return up;
        }

    }
}
/*determine if a num is even or not*/
int is_even(int i){
    if ((i%2) == 0){
        return 1;
    }
    if ((i%2) == 1){
        return 0;
    }
}

int fib(int n, int x, int y, int step, RGB b, RGB f, int w, int h, RGB* image){
    char *fn = fibword(n);
    if (!fn)  return 0;
    int i;
    /*set blackground*/
    for (i = 0; i < w * h; i++)
        image[i] = b;

    int len = strlen(fn);
    int dig = 0;
    direction dir = up;
    for (i = 1; i <= len && x < w && y < h; i++){
        dig = fn[i-1] - '0';
        draw(x, y, dir, step, w, image, f);
        switch (dir){
            case (up):
                x += step;
                break;
            case (right):
                y += step;
                break;
            case (down):
                x -= step;
                break;
            case (left):
                y -= step;
                break;
            default:
                break;
        }
        if (dig == 0){
            if (is_even(i)){
                dir = turn(dir, left);
            }
            else{
                dir = turn(dir, right);
            }
        }
        if (dig == 1){
            dir = dir;
        }
    }
    free(fn);
    if ((i-1) != len)
        return 0;
    return len;
}