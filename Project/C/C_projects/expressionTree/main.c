#include <stdio.h>
#include "expr.h"
#define N 4
int main ()
{
    Node * a [4];
    a [0] = createNode ("a", .1);
    a [1] = createNode ("b", .2);
    a [2] = createNode ("c", .3);
    a [3] = createNode ("d", .4);
    Node * t1 = binop (mulop , a[0] , a [1]) ; /* a*b */
    Node * t2 = binop (addop , a[2] , t1); /* c+a*b */
    Node * t3 = binop (mulop , a[3] , t2); /* d*(c+a*b) */
    Node * t4 = duplicateTree (t3); /* d*(c+a*b) */
    Node * t5 = binop (subop , t4 , t3);
    Node * f = t5;
    printf (" --- Tree at t4 \n");
    printTree (t4);
    double val = evalTree (f);
    printf ("\n--- Value at root of f %g\n", val );
    printf (" --- Evaluated Tree at f \n");
    printTree (f);
    freeTree (f);
    return 0;
}